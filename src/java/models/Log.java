/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

public class Log {
    
    private Date date;
    private Account account;
    private String description;
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDescription(String descr) {
        description = descr;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }
    
}
