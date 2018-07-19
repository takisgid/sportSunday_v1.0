
package model;

import enums.TypeOfResult;

public class CardChampionshipCup extends CardChampionship {
    
    // league type is about winning so as a competitor continues staying alive-active
    boolean active;
    
    public CardChampionshipCup(){
        
        super();
        
        this.active = true;
    }
    
    public boolean isActive(){
        return this.active;
    }
    
    public void deactivate(){
        this.active = false;
    }
    
    public  Integer getPoints(){
        return -1;
    }

    // method that records all the statistical impact of a game at the cards of the 
    // 2 involved competitors
    // e.g if the game is: compA - compB: 3-1 and the championship type is cup
    // This method will be called twice for each game (1st:playsHome=true, 2nd:playsHome=false )
    // *** if isForGiving == true
    //     -------------------- it will set the status of compB as not active,
    //     -------------------- it will edit all the basics statistics too
    // *** if isForGiving == false
    //     -------------------- it will set the status of compB as active,
    //     -------------------- it will edit all the basics statistics too
    @Override
    public void recordOrEraseResult(Game currentGame, boolean playsHome,boolean isForRecord){
        
        TypeOfResult typeOfResultObject;
        int valueForScoreFor;
        int valueForScoreAgainst;
                
        if (playsHome){
            typeOfResultObject = currentGame.getResultforHomeCompetitor();
            valueForScoreFor = currentGame.getHomeScore();
            valueForScoreAgainst = currentGame.getAwayScore();
        }
        else{
            typeOfResultObject = currentGame.getResultforAwayCompetitor();
            valueForScoreFor = currentGame.getAwayScore();
            valueForScoreAgainst = currentGame.getHomeScore();
        }
        
        super.basicRecordOrEraseResult(typeOfResultObject, valueForScoreFor, valueForScoreAgainst,isForRecord);
        
        
        switch (typeOfResultObject) {
                case WIN:
                    
                    break;
                case TIE:
                    // throw exception
                    break;
                case DEFEAT:
                    if (isForRecord)
                        this.active = false;
                    else
                        this.active = true;
                    break;
                default:
            }
    }
}
