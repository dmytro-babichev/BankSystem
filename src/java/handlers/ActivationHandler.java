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
import models.User;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class ActivationHandler extends RequestHandler {

    public ActivationHandler(Logger logger) {
        command = "act_deact";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        Account account = updateAccount(session, acDAO, "account");
        RequestDispatcher view;
        User user = (User) session.getAttribute("user");
        if (user.getAdminFlag()) {
            account.setStatus(!account.getStatus());
            view = request.getRequestDispatcher("act_deact.jsp");
        } else {
            if (account.getStatus()) {
                account.setStatus(false);
            } else {
                request.setAttribute("message", "Your account is deactivated");
            }
            view = request.getRequestDispatcher("account.jsp");
        }
        acDAO.update(account);
        session.setAttribute("account", account);
        try {
            view.forward(request, response);
        } catch (ServletException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}