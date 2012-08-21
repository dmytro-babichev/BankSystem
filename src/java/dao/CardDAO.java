package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Card;
import models.User;

/**
 *
 * @author laory
 */
public class CardDAO implements dao.interfaces.CRUD<Card> {

    @Override
    public int create(Card entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Card entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Card read(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Card entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Vector<Card> getCards(User user) {
        Vector<Card> cards = new Vector<Card>();
        try {
            SQLConnection sqlcon = new SQLConnection();
            String query = "select account, card_password, serial_number "
                    + "from cards where user=?;";
            Object[] params = {new UserDAO().getId(user)};
            ResultSet rs = sqlcon.getInformation(query, params);
            while (rs.next()) {
                Card card = new Card();
                card.setAccount(new AccountDAO().read(rs.getInt("account")));
                card.setCardPassword(rs.getInt("card_password"));
                card.setSerialNumber(rs.getString("serial_number"));
                cards.add(card);
            }
            sqlcon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cards;
    }
}