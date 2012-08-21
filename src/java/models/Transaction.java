package models;

import java.sql.Date;

/**
 *
 * @author laory
 */
public class Transaction {
    
    private Account payer;
    private Account remittee;
    private double amountOfPayment;
    private Exchange exchange;
    private Date date;
    
    public void setPayer(Account account) {
        payer = account;
    }
    
    public void setRemittee(Account account) {
        remittee = account;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setAmountOfPayment(double sum) {
        amountOfPayment = sum;
    }
    
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
    
    public Account getPayer() {
        return payer;
    }
    
    public Account getRemittee() {
        return remittee;
    }
    
    public double getAmountOfPayment() {
        return amountOfPayment;
    }
    
    public Exchange getExchange() {
        return exchange;
    }
    
    public Date getDate() {
        return date;
    }
    
}