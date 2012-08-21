package handlers;

import dao.AccountDAO;
import dao.TransactionDAO;
import handlers.base.RequestHandler;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Transaction;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class TransactionsHandler extends RequestHandler {

    public TransactionsHandler(Logger logger) {
        command = "get_transactions";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        Account account = updateAccount(session, acDAO, "account");
        Vector<Transaction> trans = new TransactionDAO().getTransactions(account);
        if (!trans.isEmpty()) {
            request.setAttribute("transactions", trans);
        } else {
            request.setAttribute("message", "You have not any transaction");
        }
        RequestDispatcher view = request.getRequestDispatcher("transaction.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}