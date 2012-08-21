package handlers;

import handlers.base.RequestHandler;
import dao.CardDAO;
import java.io.IOException;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Card;
import models.User;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class OperationsHandler extends RequestHandler {

    public OperationsHandler(Logger logger) {
        command = "open_account";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String account_id = request.getParameter("account");
        Account account = null;
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Vector<Card> cards = new CardDAO().getCards(user);
        for (Card i : cards) {
            if (i.getAccount().getId().equals(account_id)) {
                account = i.getAccount();
                break;
            }
        }
        RequestDispatcher view;
        if (account != null) {
            session.setAttribute("account", account);
            view = request.getRequestDispatcher("account.jsp");
        } else {
            view = request.getRequestDispatcher("oper_room.jsp");
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