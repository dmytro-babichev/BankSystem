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
public class FinderHandler extends RequestHandler {

    public FinderHandler(Logger logger) {
        command = "find_remittee";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        AccountDAO acDAO = new AccountDAO();
        String account_id = request.getParameter("remittee");
        Account remittee = acDAO.getAccount(account_id);
        Account account = updateAccount(session, acDAO, "account");
        RequestDispatcher view;
        if (account.getStatus()) {
            session.setAttribute("account", account);
            if (remittee != null
                    && remittee.getStatus()
                    && !remittee.getId().equals(account.getId())) {
                session.setAttribute("remittee", remittee);
                view = request.getRequestDispatcher("payment.jsp");
            } else {
                request.setAttribute("message", "You've entered bad account");
                view = request.getRequestDispatcher("remittee.jsp");
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