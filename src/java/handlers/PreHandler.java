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
public class PreHandler extends RequestHandler {

    public PreHandler(Logger log) {
        command = "update_account";
        logger = log;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        Account account = updateAccount(session, acDAO, "account");
        RequestDispatcher view = request.getRequestDispatcher(request.getParameter("page"));
        if (!account.getStatus()) {
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