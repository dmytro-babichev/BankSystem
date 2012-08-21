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
import models.Card;
import models.User;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class AccountsHandler extends RequestHandler {

    public AccountsHandler(Logger logger) {
        command = "get_accounts";
        this.logger = logger;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        RequestDispatcher view;
        if (user.getAdminFlag()) {
            view = request.getRequestDispatcher("admin_room.jsp");
        } else {
            Vector<Card> cards = new CardDAO().getCards(user);
            user.setCards(cards);
            session.setAttribute("cards", cards);
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