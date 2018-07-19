
package lotteryClasses;

import java.io.Serializable;
import java.util.Random;

// this class modelizes a lottery ball which has 2 indexes. Each index points
// to a competitor at the relevant Treeset.
// While, the lottery proceeds to a specific phase or game week and some games
// have already set, some couples of active competitors are unavailable
// for this stage. Later, an unavailable couple could be available again.
public class CoupleOfCompetitorsIndexLotteryBall implements Serializable {
    
    private int homeCompetitorIndex;
    private int awayCompetitorIndex;
    
    private boolean isAvailable;
    
    // constructor
    public CoupleOfCompetitorsIndexLotteryBall(int homeCompetitorIndex, int awayCompetitorIndex){
        this.homeCompetitorIndex = homeCompetitorIndex;
        this.awayCompetitorIndex = awayCompetitorIndex;
        this.isAvailable = true;
    }
    
    // setters
    public void setHomeCompetitorIndex(int homeCompetitorIndex){
        this.homeCompetitorIndex = homeCompetitorIndex;
    }
    public void setAwayCompetitorIndex(int awayCompetitorIndex){
        this.awayCompetitorIndex = awayCompetitorIndex;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    public void setItOff(){
        this.isAvailable = false;
    }
    public void setItOn(){
        this.isAvailable = true;
    }
    
    // gettters
    public int getHomeTeamIndex(){
        return this.homeCompetitorIndex;
    }
    public int getAwayTeamIndex(){
        return this.awayCompetitorIndex;
    }
    public boolean getIsAvailable(){
        return this.isAvailable;
    }
    
    @Override
    public boolean equals(Object otherObject){
        
        CoupleOfCompetitorsIndexLotteryBall otherCoupleOfCompetitorsIndexLotteryBall =  
                                                        (CoupleOfCompetitorsIndexLotteryBall) otherObject;
        
        if (this.homeCompetitorIndex == otherCoupleOfCompetitorsIndexLotteryBall.homeCompetitorIndex &&
                  this.awayCompetitorIndex == otherCoupleOfCompetitorsIndexLotteryBall.awayCompetitorIndex)
            return true;
        
        return false;
    }
    
    // Because of this method, it is enough that it will be created just
    // the comp(1)-comp(2) lottery ball. No need for com(2)-comp(1) lottery ball.
    // It is decided which team will play home by a random numbers generator
    // with posiibility of 50%-50% for the two events.
    public void haveAChanceOfSwap(){
        
        Random randomGenerator = new Random();
        int tempInt;
        
        if (randomGenerator.nextInt(2) == 0){
            tempInt = this.homeCompetitorIndex;
            this.homeCompetitorIndex = this.awayCompetitorIndex;
            this.awayCompetitorIndex = tempInt;
        }  
    }
    
    @Override
    public String toString(){
        
        Integer homeInteger = this.homeCompetitorIndex;
        Integer awayInteger = this.awayCompetitorIndex;
        
        return homeInteger.toString() + " - " + awayInteger.toString() + "_" + this.isAvailable;
    }
}
