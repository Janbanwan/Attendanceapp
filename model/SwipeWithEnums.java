/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import repositories.RepositoryObject;

/**
 *
 * @author janne
 */
public class SwipeWithEnums extends RepositoryObject implements Serializable, Comparable<SwipeWithEnums> {

    protected int id;
    protected String cardId;
    protected String room;
    protected Calendar swipeDateTime;
    protected SwipeType swipeType;
    
    protected static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    

    public SwipeWithEnums() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeType = swipeType.STUDENTSWIPE;
        this.swipeDateTime = getNow();
    }
    
    public SwipeWithEnums(String cardId, String room, SwipeType swipeType) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeType = swipeType;
        this.swipeDateTime = getNow();
    }    
    
    public SwipeWithEnums(int swipeId, String cardId, String room, SwipeType swipeType, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeType = swipeType;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    public int getId() {
        return this.id;
    }
    
    public static void setLastIdUsed(int lastSwipeIdUsed){
        SwipeWithEnums.lastSwipeIdUsed = lastSwipeIdUsed;
    }
    public String getCardId(){
        return this.cardId;
    }
    
    public String getRoom(){
        return this.room;
    }
    
    public Calendar getSwipeDateTime() {
        return this.swipeDateTime;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    public SwipeType getSwipeType()
    {
        return this.swipeType;
    }

    public void setSwipeType(SwipeType swipeType)
    {
        this.swipeType = swipeType;
    }     
    
    @Override
    public int hashCode() {
        return getId() * 31 + getCardId().hashCode() * 31 +  getRoom().hashCode() * 31;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof SwipeWithEnums) {
            SwipeWithEnums e = (SwipeWithEnums)o;
            return  e.getId() == getId() &&
                    e.getCardId().equals(getCardId()) &&
                    e.getRoom().equals(getRoom()) &&
                    e.getSwipeType().equals(getSwipeType()) &&
                    e.getSwipeDateTime().equals(getSwipeDateTime());
        } else {
            return false;
        }
    }
    
    public static Comparator<SwipeWithEnums> SwipeDateTimeComparator = (SwipeWithEnums sw1, SwipeWithEnums sw2) -> {
        Calendar swipeDate1 = sw1.getSwipeDateTime();
        Calendar swipeDate2 = sw2.getSwipeDateTime();

        return swipeDate2.compareTo(swipeDate1);
    };  
    
    @Override
    public int compareTo(SwipeWithEnums compareSwipe) {
	
		int swipeId = ((SwipeWithEnums) compareSwipe).getId(); 
		
		return this.id - swipeId;
	
    }    

    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    

    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swipe Type: " + this.swipeType + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    
}
