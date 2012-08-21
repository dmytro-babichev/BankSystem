package handlers.base;

import handlers.AccountsHandler;
import handlers.ActivationHandler;
import handlers.AdminFinderHandler;
import handlers.FinderHandler;
import handlers.OperationsHandler;
import handlers.PaymentHandler;
import handlers.PreHandler;
import handlers.RechargeHandler;
import handlers.TransactionsHandler;
import handlers.base.RequestHandler;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author laory
 */
public class ChainOfResponsibilities {

    RequestHandler root = null;
    RequestHandler last = null;
    Logger log = null;

    public ChainOfResponsibilities(Logger logger) {
        log = logger;
        createRing(new AccountsHandler(logger));
        createRing(new OperationsHandler(logger));
        createRing(new ActivationHandler(logger));
        createRing(new FinderHandler(logger));
        createRing(new PaymentHandler(logger));
        createRing(new RechargeHandler(logger));
        createRing(new TransactionsHandler(logger));
        createRing(new AdminFinderHandler(logger));
        createRing(new PreHandler(logger));
    }

    private void createRing(RequestHandler ring) {
        if (root == null) {
            last = root = ring;
        } else {
            last.setNextHandler(ring);
            last = last.getNextHandler();
        }
    }

    public void handleRequst(HttpServletRequest request, HttpServletResponse response) {
        RequestHandler temp = root;
        do {
            if (temp.checkCommand(request.getParameter("command").toString())) {
                temp.handleRequest(request, response);
                return;
            } else {
                temp = temp.getNextHandler();
            }
        } while (temp != null);
        RequestDispatcher view = request.getRequestDispatcher("404.jsp");
        try {
            view.forward(request, response);
        } catch (ServletException ex) {
            log.error(ex);
        } catch (IOException ex) {
            log.error(ex);
        }
    }
}