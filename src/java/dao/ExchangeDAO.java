package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Exchange;

/**
 *
 * @author laory
 */

public class ExchangeDAO implements dao.interfaces.CRUD<Exchange> {

    @Override
    public int create(Exchange entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Exchange entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Exchange read(int id) {
        Exchange exchange = null;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select symbol from exchanges where id=?;";
            Object[] params = {id};
            ResultSet rs = sqlcon.getInformation(query, params);
            if (rs.next()) {
                exchange = Exchange.valueOf(rs.getString(1));
            } 
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exchange;
    }

    @Override
    public boolean update(Exchange entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getId(Exchange entity) {
        int id = -1;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select id from exchanges where symbol=?;";
            Object[] params = {entity.toString()};
            ResultSet rs = sqlcon.getInformation(query, params);
            if (rs.next()) {
                id = rs.getInt(1);
            } 
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
}