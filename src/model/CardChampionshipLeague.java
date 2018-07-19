
package model;

import adjustedClasses.UsefulStaticStuff;
import enums.TypeOfResult;

public class CardChampionshipLeague extends CardChampionship{
    
    // league type is about gathering points
    int points;
    
    //Constructor
    public CardChampionshipLeague(){
        
        super();
        
        this.points = 0;
    }
    
    public Integer getPoints(){
        return this.points;
    }
    
    // method that records all the statistical impact of a game at the cards of the 
    // 2 involved competitors
    // e.g if the game is: compA - compB: 3-1 and the championship type is league
    // This method will be called twice for each game (1st:playsHome=true, 2nd:playsHome=false )
    // *** if isForGiving == true
    //     -------------------- it will add the winning points of compA,
    //     -------------------- it will edit all the basics statistics too
    // *** if isForGiving == false
    //     -------------------- it will subtract the winning points of compA,
    //     -------------------- it will edit all the basics statistics too
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
                    this.points =
                    UsefulStaticStuff.addOrSubtract(this.points, currentGame.givePointsForWin(), isForRecord);
                    break;
                case TIE:
                    this.points =
                    UsefulStaticStuff.addOrSubtract(this.points, currentGame.givePointsForTie(), isForRecord);
                    break;
                case DEFEAT:
                    this.points =
                    UsefulStaticStuff.addOrSubtract(this.points, currentGame.givePointsForDefeat(), isForRecord);
                    break;
                default:
        }
    }
}
