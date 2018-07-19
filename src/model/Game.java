
package model;

import enums.TypeOfResult;
import java.io.Serializable;

// abstraction that includes every type of sport game
public abstract class Game implements Serializable{
    
    protected Competitor homeCompetitor;
    protected Competitor awayCompetitor;
    protected int homeScore;
    protected int awayScore;
    
    // constructor
    public Game(Competitor homeCompetitor, Competitor awayCompetitor){
        this.homeCompetitor = homeCompetitor;
        this.awayCompetitor = awayCompetitor;
        
        // score is initialized as (-1)-(-1)
        // -1 means that current score hasn't been registered
        this.homeScore = -1;
        this.awayScore = -1;
    }
    
    // getters
    public int getHomeScore(){
        return this.homeScore;
    }
    public int getAwayScore(){
        return this.awayScore;
    }
    public Competitor getHomeCompetitor(){
        return this.homeCompetitor;
    }
    public Competitor getAwayCompetitor(){
        return this.awayCompetitor;
    }
    
    public TypeOfResult getResultforHomeCompetitor(){
        
        if(this.homeScore > this.awayScore)
            return TypeOfResult.WIN;
        else if (this.homeScore == this.awayScore)
            return TypeOfResult.TIE;
        else
            return TypeOfResult.DEFEAT;
    
    }
    
    public TypeOfResult getResultforAwayCompetitor(){
        
        TypeOfResult oppositeTypeOfResult = getResultforHomeCompetitor();
        
        switch (oppositeTypeOfResult) {
            case WIN:
                    return TypeOfResult.DEFEAT;
            case TIE:
                    return TypeOfResult.TIE;
            case DEFEAT:
                     return TypeOfResult.WIN;
            default:
                return TypeOfResult.NULL;
        }
    }
    
    
    //setters
    public void setHomeScore(int homeScore){
        this.homeScore = homeScore;
    }
    public void setAwayScore(int awayScore){
        this.awayScore = awayScore;
    }
    
    @Override
    public String toString(){
        return this.homeCompetitor + " - " + this.awayCompetitor;
    }
    
    public abstract int givePointsForWin();
    public abstract int givePointsForTie();
    public abstract int givePointsForDefeat(); 
}
