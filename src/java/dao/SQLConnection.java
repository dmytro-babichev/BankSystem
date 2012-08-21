package dao;

import java.sql.*;
import pool.ConnectionPool;

/**
 *
 * @author laory
 */

public class SQLConnection {

    private Connection con;
    private PreparedStatement statement;
    private ResultSet generatedKeys;

    public SQLConnection() throws SQLException {
        con = ConnectionPool.getInstance().getConnection();
    }

    private void makeStatement(String query, Object[] params) throws SQLException {
        statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    public int doOperation(String query, Object[] params) throws SQLException {
        makeStatement(query, params);
        int result = statement.executeUpdate();
        generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            result = generatedKeys.getInt(1);
        }
        return result;
    }

    public ResultSet getInformation(String query, Object[] params) throws SQLException {
        makeStatement(query, params);
        return statement.executeQuery();
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (generatedKeys != null) {
            generatedKeys.close();
        }
    }
}