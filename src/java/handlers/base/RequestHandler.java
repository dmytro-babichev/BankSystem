package handlers.base;

import dao.AccountDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public abstract class RequestHandler {

    protected String command;
    protected RequestHandler nextHandler;
    protected Logger logger;
    
    public void setNextHandler(RequestHandler nh) {
        nextHandler = nh;
    }
    
    public boolean hasNext() {
        return nextHandler != null;
    }
    
    public boolean checkCommand(String command) {
        return this.command.equals(command);
    }
    
    public RequestHandler getNextHandler() {
        return nextHandler;
    }

    public abstract void handleRequest(HttpServletRequest request,
            HttpServletResponse response);
    
    public Account updateAccount(HttpSession session, AccountDAO acDAO, String param) {
        Account account = (Account) session.getAttribute(param);
        account = acDAO.getAccount(account.getId());
        session.setAttribute(param, account);
        return account;
    }
}