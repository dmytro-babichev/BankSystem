/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Vector;

/**
 *
 * @author laory
 */

public class User {
    
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Vector<Card> cards = new Vector<Card>();
    private boolean adminFlag;
    
    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public void setLastName(String name) {
        this.lastName = name;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setCards(Vector<Card> cards) {
        this.cards = cards;
    }
    
    public Vector<Card> getCards() {
        return cards;
    }
    
    public void setAdminFlag(boolean flag) {
        adminFlag = flag;
    }
    
    public boolean getAdminFlag() {
        return adminFlag;
    }
    
}