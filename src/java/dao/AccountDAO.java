package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;

/**
 *
 * @author laory
 */

public class AccountDAO implements dao.interfaces.CRUD<Account> {

    @Override
    public int create(Account entity) {
        int id = -1;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "insert into accounts (account_id, status, cash, "
                    + "exchange) values (?, ?, ?, ?);";
            Object[] params = {
                entity.getId(),
                entity.getStatus(),
                entity.getCash(),
                new ExchangeDAO().getId(entity.getExchange())
            };
            id = sqlcon.doOperation(query, params);
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public boolean delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Account read(int id) {
        Account account = new Account();
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select account_id, cash, status, exchange from "
                    + "accounts where id=?;";
            Object[] params = {id};
            ResultSet rs = sqlcon.getInformation(query, params);
            if (rs.next()) {
                account.setId(rs.getString("account_id"));
                account.setStatus(rs.getBoolean("status"));
                account.setCash(rs.getDouble("cash"));
                account.setExchange(new ExchangeDAO().read(rs.getInt("exchange")));
            } else {
                account = null;
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    @Override
    public boolean update(Account entity) {
        boolean res = false;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "update accounts set cash=?, status=? where account_id=?;";
            Object[] params = {
                entity.getCash(),
                entity.getStatus(),
                entity.getId()
            };
            if (sqlcon.doOperation(query, params) != 0) {
                res = true;
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public int getId(Account entity) {
        int id = -1;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select id from accounts where account_id=?;";
            Object[] params = {entity.getId()};
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
    
    public Account getAccount(String id) {
        Account account = new Account();
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select cash, status, exchange from "
                    + "accounts where account_id=?;";
            Object[] params = {id};
            ResultSet rs = sqlcon.getInformation(query, params);
            if (rs.next()) {
                account.setId(id);
                account.setStatus(rs.getBoolean("status"));
                account.setCash(rs.getDouble("cash"));
                account.setExchange(new ExchangeDAO().read(rs.getInt("exchange")));
            } else {
                account = null;
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }
    
}