package handlers;

import handlers.base.RequestHandler;
import dao.AccountDAO;
import dao.TransactionDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Card;
import models.Transaction;
import models.User;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class PaymentHandler extends RequestHandler {

    public PaymentHandler(Logger logger) {
        command = "pay";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        Account payer = updateAccount(session, acDAO, "account");
        Account remittee = updateAccount(session, acDAO, "remittee");
        Double aop = Double.parseDouble(request.getParameter("aop"));
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");
        RequestDispatcher view = request.getRequestDispatcher("account.jsp");
        if (payer.getStatus()) {
            if (remittee.getStatus()) {
                if (password.equals(user.getPassword())) {
                    if (aop <= 0) {
                        request.setAttribute("message", "You've entered bad value");
                        view = request.getRequestDispatcher("payment.jsp");
                    } else if (aop < payer.getCash()) {
                        Transaction transaction = new Transaction();
                        transaction.setAmountOfPayment(aop);
                        transaction.setPayer(payer);
                        transaction.setRemittee(remittee);
                        transaction.setExchange(payer.getExchange());
                        transaction.setDate(new Date(new java.util.Date().getTime()));
                        TransactionDAO trdao = new TransactionDAO();
                        trdao.create(transaction);
                        payer.setCash(payer.getCash() - aop);
                        remittee.setCash(remittee.getCash() + aop);
                        acDAO.update(payer);
                        acDAO.update(remittee);
                        request.setAttribute("transactions", trdao.getTransactions(payer));
                        request.setAttribute("message", "Transaction complete");
                        view = request.getRequestDispatcher("transaction.jsp");
                    } else {
                        request.setAttribute("message", "You've not enough money");
                        view = request.getRequestDispatcher("payment.jsp");
                    }
                } else {
                    request.setAttribute("message", "Wrong password");
                    view = request.getRequestDispatcher("payment.jsp");
                }
            } else {
                request.setAttribute("message", "The transaction can't be complete");
            }
        } else {
            request.setAttribute("message", "Your account is deactivated");
        }
        try {
            view.forward(request, response);
        } catch (ServletException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}