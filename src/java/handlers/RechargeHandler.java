package handlers;

import handlers.base.RequestHandler;
import dao.AccountDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class RechargeHandler extends RequestHandler {

    public RechargeHandler(Logger logger) {
        command = "recharge";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        Account account = updateAccount(session, acDAO, "account");
        Double recharge = Double.parseDouble(request.getParameter("recharge"));
        RequestDispatcher view;
        if (account.getStatus()) {
            if (recharge > 0) {
                account.setCash(account.getCash() + recharge);
                acDAO.update(account);
                session.setAttribute("account", account);
                view = request.getRequestDispatcher("account.jsp");
            } else {
                request.setAttribute("message", "You've entered bad value");
                view = request.getRequestDispatcher("recharge.jsp");
            }
        } else {
            request.setAttribute("message", "Your account is deactivated");
            view = request.getRequestDispatcher("account.jsp");
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