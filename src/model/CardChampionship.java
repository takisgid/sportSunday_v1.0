
package model;

import adjustedClasses.TreeSetWithGetIndex;
import adjustedClasses.UsefulStaticStuff;
import enums.TypeOfResult;
import java.io.Serializable;

// Card: Contains all relevant data about the involvement of a specific
// competitor to a specific championship.
// Every competitor has got a specific card for every specific championship that 
// he participates
public abstract class CardChampionship implements Serializable {
    
    //Statistics
    private int scoreFor;
    private int scoreAgainst;
    private int scoreDifference;
    private int numberOfGamesPlayed;
    private int numberOfWins;
    private int numberOfDefeats;
    private int numberOfTies;
    
    // TreeSet that contains the declaration of the roster of a team for
    // a specific championship
    private TreeSetWithGetIndex<Player> inRosterTreeSet;
    
    // Constructor
    public CardChampionship(){
        
        this.scoreFor = 0;
        this.scoreAgainst = 0;
        this.scoreDifference = 0;
        this.numberOfGamesPlayed = 0;
        this.numberOfWins = 0;
        this.numberOfDefeats = 0;
        this.numberOfTies = 0;
        
        this.inRosterTreeSet = new TreeSetWithGetIndex<Player>();
    }
    
    // getters
    public int getNumberOfGamesPlayed(){
        return this.numberOfGamesPlayed;
    }
    public Integer getScoreFor(){
        return this.scoreFor;
    }
    public Integer getScoreAgainst(){
        return this.scoreAgainst;
    }
    public Integer getScoreDifference(){
        return this.scoreDifference;
    }
    public Integer getNumberOfWins(){
        return this.numberOfWins;
    }
    public Integer getNumberOfDefeats(){
        return this.numberOfDefeats;
    }
    public Integer getNumberOfTies(){
        return this.numberOfTies;
    }
    public abstract Integer getPoints();
    public TreeSetWithGetIndex<Player> getInRosterTreeSet(){
        return this.inRosterTreeSet;
    }
    
    public void addPlayerToInRosterTreeset(Player playerObject){
        this.inRosterTreeSet.add(playerObject); 
    }
    
    public void removePlayerFromRosterTreeset (int selectedPlayerIndex){
        this.inRosterTreeSet.removeElementAt(selectedPlayerIndex);
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
    public abstract void recordOrEraseResult(Game currentGame,boolean playsHome,boolean isForRecord);
    
    // method that records the basic statistical impact of a game at the cards of the 
    // 2 involved competitors
    // e.g if the game is: compA - compB: 3-1 
    // This method will be called twice for each game (1st:playsHome=true, 2nd:playsHome=false )
    // *** if isForGiving == true
    //     -------------------- it will add 3 from "score for" of compA
    //     -------------------- it will add 1 from "score against" of compA
    //     -------------------- it will add 3 from "score against" of compB
    //     -------------------- it will add 1 from "score for" of compB
    // *** if isForGiving == false
    //     -------------------- it will subtract 3 from "score for" of compA
    //     -------------------- it will subtract 1 from "score against" of compA
    //     -------------------- it will subtract 3 from "score against" of compB
    //     -------------------- it will subtract 1 from "score for" of compB
    public void basicRecordOrEraseResult(TypeOfResult typeOfResultObject,int valueForScoreFor, 
                                        int valueForScoreAgainst, boolean isForRecord){
        
        this.numberOfGamesPlayed =
        UsefulStaticStuff.addOrSubtract(this.numberOfGamesPlayed, 1, isForRecord);
        
        switch (typeOfResultObject) {
            case WIN:
                    this.numberOfWins =
                    UsefulStaticStuff.addOrSubtract(this.numberOfWins, 1, isForRecord);
                    break;
            case TIE:
                    this.numberOfTies =
                    UsefulStaticStuff.addOrSubtract(this.numberOfTies, 1, isForRecord);
                    break;
            case DEFEAT:
                    this.numberOfDefeats =
                    UsefulStaticStuff.addOrSubtract(this.numberOfDefeats, 1, isForRecord);
                    break;
            default:
        }
        
        this.scoreFor =
        UsefulStaticStuff.addOrSubtract(this.scoreFor, valueForScoreFor, isForRecord);
        
        this.scoreAgainst =
        UsefulStaticStuff.addOrSubtract(this.scoreAgainst, valueForScoreAgainst, isForRecord);
        
        this.scoreDifference =
        UsefulStaticStuff.addOrSubtract(this.scoreDifference, valueForScoreFor, isForRecord)
        +
        UsefulStaticStuff.addOrSubtract(0, valueForScoreAgainst, !isForRecord);
    }
}
