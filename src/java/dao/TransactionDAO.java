package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Transaction;

/**
 *
 * @author laory
 */
public class TransactionDAO implements dao.interfaces.CRUD<Transaction> {

    @Override
    public int create(Transaction entity) {
        int id = 0;
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "insert into transactions (payer, remittee, date, "
                    + "exchange, amount_of_payment) values (?, ?, ?, ?, ?);";
            AccountDAO acc = new AccountDAO();
            ExchangeDAO exch = new ExchangeDAO();
            int id1 = acc.getId(entity.getPayer());
            int id2 = acc.getId(entity.getRemittee());
            if (id1 != -1 && id2 != -1 && id1 != id2) {
                Object[] params = {
                    id1,
                    id2,
                    entity.getDate(),
                    exch.getId(entity.getExchange()),
                    entity.getAmountOfPayment()
                };
                id = sqlcon.doOperation(query, params);
                if (id == 0) {
                    throw new SQLException("Creating transaction failed, no generated key obtained.");
                }
            } else {
                throw new SQLException("Creating transaction failed, bad accounts.");
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public boolean delete(Transaction entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Transaction read(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Transaction entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Vector<Transaction> getTransactions(Account payer) {
        Vector<Transaction> transactions = new Vector<Transaction>();
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select payer, remittee, date, exchange, amount_of_payment "
                    + "from transactions where payer=? or remittee=?;";
            AccountDAO acc = new AccountDAO();
            int id = acc.getId(payer);
            Object[] params = {id, id};
            ResultSet rs = sqlcon.getInformation(query, params);
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmountOfPayment(rs.getDouble("amount_of_payment"));
                transaction.setDate(rs.getDate("date"));
                transaction.setExchange(new ExchangeDAO().read(rs.getInt("exchange")));
                transaction.setRemittee(acc.read(rs.getInt("remittee")));
                transaction.setPayer(acc.read(rs.getInt("payer")));
                transactions.add(transaction);
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
}