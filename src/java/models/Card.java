package models;

/**
 *
 * @author laory
 */
public class Card {
    
    private Account account;
    private String serialNumber;
    private int cardPassword;
    
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSerialNumber(String sn) {
       serialNumber = sn;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setCardPassword(int pas) {
       cardPassword = pas;
    }
    
    public int getCardPassword() {
        return cardPassword;
    }

    public String getSerialNumber() {
       return serialNumber;
    }
    
}
