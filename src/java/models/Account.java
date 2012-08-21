package models;

/**
 *
 * @author laory
 */
public class Account {
    
    private String id;
    private boolean status;
    private double cash;
    private Exchange exchange;
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public void setCash(double cash) {
        this.cash = cash;
    }
    
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public double getCash() {
        return cash;
    }
    
    public Exchange getExchange() {
        return exchange;
    }
    
}