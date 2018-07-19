
package model;

import comparators.ByPointsLeagueComparator;
import lotteryClasses.LotteryBallsHashSet;
import enums.Sport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// In version 1, this type of championship contains strictly one round of games,
// in which each competitor plays exactly one game with every possible opponent
// In version 2, it will be converted to more flexible (the user will be able to choose
// multiple rounds)
public class ChampionshipLeague extends Championship {
    
    // Constructor
    public ChampionshipLeague(Sport sportObject, String championshipLeagueName, int numberOfCompetitorsBigO,
                                            String trophyString, int minRoster, int maxRoster){
        
        super(sportObject,championshipLeagueName,numberOfCompetitorsBigO,trophyString, minRoster, maxRoster);
        //changeTableSortEnumObjectToLeagueBasic();
    }
    
    // getters
    @Override
    public int getNumberOfGamesForEachCompetitor(){
        
        //return (Math.max(((this.numberOfCompetitors-1)*2),0));
        
        //temporary
        return 1;
    }
    @Override
    public String getChampionshipTypeString(){
        return "League";
    }
    
    // method that returns verbal description of current sportDay
    @Override
    public String getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber (Integer phaseNumber){
        
        if (!this.finalized)
            return super.getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber(phaseNumber);
        else{
            Integer gameWeekInteger = phaseNumber+1;
            return "Game week " + (gameWeekInteger);
        }
    }
    
    // method that returns verbal guidance for the user when he sees the Report screen
    public String getReportGuideString(){
        
        if (!this.finalizable)
            return super.getReportGuideString();
        
        if (this.isLotteriable())
            return "You may proceed to the lottery!";
        
        int numberOfGameWeeks = this.sportDaysList.size();
        int currentSportDayFixed = this.currentSportDay + 1;
        
        
        if (this.finalized){
            if (currentSportDayFixed <= numberOfGameWeeks/5)
                return "You should fill the results." + System.lineSeparator() +
                        "A good start is half the battle!";
            else if (currentSportDayFixed != numberOfGameWeeks &&
                                            currentSportDayFixed >= (4*numberOfGameWeeks)/5)
                return "You should fill the results." + System.lineSeparator() +
                        "Heartbeat for the last turn!";
            else if (currentSportDayFixed == numberOfGameWeeks)
                return "Time for Champion!!!";
            else
                return "You should fill the results.";
        }
        return "mmm";
    }
    
    
    // boolean
    public  boolean isLotteriable(){
        if (this.finalizable && !this.finalized)
            return true;
        else
            return false;
    }
    public boolean isTieAllowed(){
        return super.isTieAllowed();
    }
    public boolean isPointBased(){
        return true;
    }
    
    // updaters
    public void basicUpdateTableList(){
        updateTableListByPoints();
    }
    
    public void updateTableListByPoints(){
        
        List<Competitor> tableByPointslist = new ArrayList<>(this.competitorsTreeSet);
        Collections.sort(tableByPointslist, new ByPointsLeagueComparator(this));
        
        this.tableList = tableByPointslist;
        
    }
    
    // override just for giving the appropriate CardChampionship object
    public void addCompetitor(Competitor competitorObject){
        super.addCompetitor(competitorObject, new CardChampionshipLeague());
    }
    
    @Override
    public void makeLottery(){
        
        boolean completed;
        int counter = 0;
        
        if (!finalizable || finalized)
            return;
            
        finalized = true;
        
        LotteryBallsHashSet lotteryBallsHashSetObject = new LotteryBallsHashSet(this);
        
        do{
            ++counter;
            
            lotteryBallsHashSetObject.createLotteryBalls();
            completed = lotteryBallsHashSetObject.makeLotteryForLeague();
        }while(!completed);
        
        //System.out.println("makeLottery() - counter: " + counter);
    } 
}
