
package enums;

import java.io.Serializable;

// Enum Sport: Includes all supportive sports.
public enum Sport implements Serializable{
    BASKETBALL(0, true, false,true),FOOTBALL(1, true, true,true),PINGPONG(2, false, false,false),
    TENNIS(3, false, false,false),VOLLEYBALL(4, true,false,false);
    
    // order of sports
    int orderNumber;
    
    // boolean variable that expresses if this sport is colegiate.
    boolean colegiate;
    
    // boolean variable that expresses if tie is a valid result in this sport.
    boolean tieAllowed;
    
    // boolean variable that expresses if report of Championship is available at current version.
    boolean reportAvailability;
    
    // i will erase it, i guess.
    int byCategOryorderNumber;
    
    // Constructor
    Sport(int orderNumber, boolean colegiate, boolean tieAllowed,boolean reportAvailability) {
        this.orderNumber = orderNumber;
        this.colegiate = colegiate;
        this.tieAllowed = tieAllowed;
        this.reportAvailability = reportAvailability;
    }
    
    // getters
    public int getOrderNumber(){
        return orderNumber;
    }
    public boolean isColegiate(){
        return this.colegiate;
    }
    public boolean isTieAllowed(){
        return this.tieAllowed;
    }
    public boolean isReportAvailable(){
        return this.reportAvailability;
    }
    
    // 2 special getters
    // static method that returns the sport value of the given index
    public static Sport byGivenOrderNumber(int orderNumber) {
        for (Sport currentSport : Sport.values()) {
            if (currentSport.orderNumber == orderNumber) 
                return currentSport;
        }
        return null;
    }
    
    // static method that returns the sport value which is described (by toString()) 
    // with the given String represantation
    public static Sport byGivenString(String toStringObject) {
        for (Sport currentSport : Sport.values()) {
            if (currentSport.toString().equals(toStringObject)) 
                return currentSport;
        }
        return null;
    }
    
    // editing toString() so as the first letter will be upper case and
    // the other will be lower case.
    @Override
    public String toString(){
        return super.toString().substring(0,1) + super.toString().substring(1).toLowerCase();
    }
}