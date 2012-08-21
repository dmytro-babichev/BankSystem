package controllers;

import dao.UserDAO;
import handlers.base.ChainOfResponsibilities;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class BankController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        HttpSession session = request.getSession(false);
        ChainOfResponsibilities chOfRes = new ChainOfResponsibilities(logger);
        if (session != null
                && session.getAttribute("user") != null
                && session.getAttribute("user") instanceof User) {
            chOfRes.handleRequst(request, response);
        } else {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
                User user = new UserDAO().getUser(login, password);
                if (user == null) {
                    request.setAttribute("message", "Wrong password or login...");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    session = request.getSession(true);
                    session.setAttribute("user", user);
                    session.setAttribute("name", user.getFirstName() + " " + user.getLastName());
                    chOfRes.handleRequst(request, response);
                }
            } else {
                request.setAttribute("message", "Empty password or login...");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameterMap().isEmpty()) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (request.getParameter("command") == null
                || request.getParameter("account") == null
                || request.getParameter("account").isEmpty()
                || request.getParameter("command").isEmpty()) {
            request.setAttribute("message", "Bad request parameters");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
