package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author laory
 */
public class UserDAO implements dao.interfaces.CRUD<User> {
    
    @Override
    public int create(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public User read(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean update(User entity) {
        boolean res = false;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "update users set password=? where login=?;";
            Object[] params = {
                entity.getPassword(),
                entity.getLogin()
            };
            if (sqlcon.doOperation(query, params) != 0) {
                res = true;
            } else {
                throw new SQLException("Updating user failed.");
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public User getUser(String login, String password) {
        User user = new User();
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select id, first_name, last_name from users where login=? && password=?;";
            Object[] params = {login, password};
            ResultSet rs = sqlcon.getInformation(query, params);
            if (rs.next()) {
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setLogin(login);
                user.setPassword(password);
                user.setAdminFlag(1 == rs.getInt("id"));
            } else {
                user = null;
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public int getId(User user) {
        int id = -1;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select id from users where login=?;";
            Object[] params = {user.getLogin()};
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