/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author janne
 */
public class VisitorSwipeWithEnums extends SwipeWithEnums  implements Serializable, Comparable <SwipeWithEnums>{
    
    static final char EOLN='\n';       
    static final String QUOTE="\"";

    protected String visitorName;
    protected String visitorCompany;
    
    public VisitorSwipeWithEnums() {
        super();
        this.visitorName = "Unknown";
        this.visitorCompany = "Unknown";        
    }

    public VisitorSwipeWithEnums(String cardId, String room, SwipeType swipeType, String visitorName, String visitorCompany) {
        super(cardId, room, swipeType);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    public VisitorSwipeWithEnums(int swipeId, String cardId, String room, SwipeType swipeType ,Calendar swipeDateTime, String visitorName, String visitorCompany) {
        super(swipeId, cardId, room,swipeType ,swipeDateTime);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }
    
    public String getVisitorName(){
        return this.visitorName;
    }
    
    public String getVisitorCompany(){
        return this.visitorCompany;
    }
    
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }
    
    public void setVisitorCompany(String visitorCompany) {
        this.visitorCompany = visitorCompany;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nName: " + this.visitorName +  " - Company: " + this.visitorCompany;
    }
    
}
