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
public class AdminFinderHandler extends RequestHandler {

    public AdminFinderHandler(Logger logger) {
        command = "find_account";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String accountId = request.getParameter("found_account");
        Account foundAccount = new AccountDAO().getAccount(accountId);
        HttpSession session = request.getSession(false);
        RequestDispatcher view = null;
        if (foundAccount != null) {
            session.setAttribute("account", foundAccount);
            view = request.getRequestDispatcher("act_deact.jsp");
        } else {
            request.setAttribute("message", "You've entered bad account");
            view = request.getRequestDispatcher("admin_room.jsp");
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