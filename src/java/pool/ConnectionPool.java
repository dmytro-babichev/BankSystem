package pool;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author laory
 */
public class ConnectionPool {
    
    private static DataSource datasource;

    private ConnectionPool() {
    }

    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                datasource = (DataSource) envCtx.lookup("jdbc/bank_system");
            } catch (Exception ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datasource;
    }
}