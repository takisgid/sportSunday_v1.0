
package model;

import java.io.Serializable;

public class FootballGame extends Game implements Serializable {
    
    // constructor
    public FootballGame(Team homeTeam, Team awayTeam){
        super(homeTeam, awayTeam);
    }
    
    
    @Override
    public int givePointsForWin(){
        return 3;
    }
    @Override
    public int givePointsForTie(){
        return 1;
    }
    @Override
    public int givePointsForDefeat(){
        return 0;
    }   
}
