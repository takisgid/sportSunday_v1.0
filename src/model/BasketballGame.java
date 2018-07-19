
package model;

import java.io.Serializable;

public class BasketballGame extends Game implements Serializable {
    
    // constructor
    public BasketballGame(Team homeTeam, Team awayTeam){
        super(homeTeam, awayTeam);
    }
    
    
    @Override
    public int givePointsForWin(){
        return 2;
    }
    @Override
    public int givePointsForTie(){
        return -1000;
    }
    @Override
    public int givePointsForDefeat(){
        return 1;
    }  
}
