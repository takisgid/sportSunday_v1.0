
package model;

import comparators.ByWinsChampionsipComparator;
import lotteryClasses.CoupleOfCompetitorsIndexLotteryBall;
import lotteryClasses.LotteryBallsHashSet;
import adjustedClasses.TreeSetWithGetIndex;
import enums.Sport;
import enums.TypeOfResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// In version 1, this type of championship contains strictly single knockout games
// In version 2, it will be converted to more flexible (phases with double games etc)
public class ChampionshipCup extends Championship {
    
    // boolean that expresses that all games of latest phsase have been registered.
    // Therefore, the championship is ready for lottery of next phase
    private boolean readyForLottery; 
    
    // the meaning is clear
    private boolean finalAlreadySet;
    
    // Treeset that contains the competitors (winners so far) that continue
    // at the tournament
    protected TreeSetWithGetIndex<Competitor> activeCompetitorsTreeSet;
    
    // constructor
    public ChampionshipCup(Sport sportObject, String championshipCupName, int numberOfCompetitorsBigO,
                                            String trophyString, int minRoster, int maxRoster){
       
        super(sportObject,championshipCupName,numberOfCompetitorsBigO,trophyString,minRoster, maxRoster);
        
        this.readyForLottery = true;
        this.finalAlreadySet = false;
        activeCompetitorsTreeSet = new TreeSetWithGetIndex<>();
    }
    
    // getters
    public int getNumberOfActiveCompetitors(){
       
        return this.activeCompetitorsTreeSet.size();
    }
    @Override
    public int getNumberOfGamesForEachCompetitor(){
        return 2;
    }
    @Override
    public String getChampionshipTypeString(){
        return "Cup";
    }
    
    // method that returns verbal description of current sportDay
    @Override
    public String getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber (Integer phaseNumber){
        
        if (!this.finalized)
            return super.getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber(phaseNumber);
        else{
            int sportDayOfCurrentPhaseNumberSize = this.sportDaysList.get(phaseNumber).size() * 2;
            
            if (sportDayOfCurrentPhaseNumberSize > 8)
                return "Round of " + sportDayOfCurrentPhaseNumberSize;
            else if (sportDayOfCurrentPhaseNumberSize == 8)
                return "Quarter Finals";
            else if (sportDayOfCurrentPhaseNumberSize == 4)
                return "Semi Finals";
            else if (sportDayOfCurrentPhaseNumberSize == 2)
                return "Final";
            
            return "kati";
        }
    }
    
    // method that returns verbal guidance for the user when he sees the Report screen
    @Override
    public String getReportGuideString(){
        
        if (!this.finalizable)
            return super.getReportGuideString();
        
        if (!this.isThereAnotherRound())
            return "After all, time for final!!!";
        
        if (this.finalized && !this.isLotteriable())
            return "You should fill the results" + System.lineSeparator() +
                    "of current phase!";
        
        if (this.isLotteriable())
            return "You may proceed to the lottery!";
                
        return "kati";
    }
    
    // boolean
    public boolean isThereAnotherRound(){
        int numberOfActiveCompetitors = getNumberOfActiveCompetitors();
        
        if (numberOfActiveCompetitors > 2)
            return true;
        else if (numberOfActiveCompetitors < 2 )
            return false;
        else if (!this.finalAlreadySet)
            return true;
        
        return false;
    }
    
    public boolean isLastSportDayFull(){
        
        for(Game currentGame:this.sportDaysList.get(super.sportDaysList.size()-1))
            if (currentGame.getHomeScore() == -1 || currentGame.getAwayScore()== -1)
                return false;
        
        return true;
    }
    
    @Override
    public  boolean isLotteriable(){
        
        if (this.finalizable && !this.finalized)
            return true;
        else if (finalized && this.readyForLottery)
            return true;
            
        return false;
    }
     
    @Override
    public boolean isTieAllowed(){
        return false;
    }
    @Override
    public boolean isPointBased(){
        return false;
    }
    
    
    // updaters
    @Override
    public void updateScore(int sportDayIndex,int gameIndex,int homeScoreInt, int awayScoreInt){
        
        super.updateScore(sportDayIndex, gameIndex, homeScoreInt, awayScoreInt);
        
        updateReadyForLottery(); 
    }
    
    @Override
    public void basicUpdateTableList(){
        updateTableListByWins();
    }
    
    public void updateTableListByWins(){
        
        List<Competitor> tableByPointslist = new ArrayList<>(this.competitorsTreeSet);
        Collections.sort(tableByPointslist, new ByWinsChampionsipComparator(this));
        
        this.tableList = tableByPointslist;
    }
    
    public void updateReadyForLottery(){
        
        if (this.isLastSportDayFull() && this.isThereAnotherRound())
            this.readyForLottery = true;
        else
            this.readyForLottery = false; 
    }
    
    // override just for giving the appropriate CardChampionship object
    // and adding competitor to the activeCompetitorsTreeSet
    public void addCompetitor(Competitor competitorObject){
        super.addCompetitor(competitorObject, new CardChampionshipCup());
        activeCompetitorsTreeSet.add(competitorObject);
    }
    
    // override just for removing competitor from the activeCompetitorsTreeSet
    public void removeCompetitor(int indexOfCompetitor){
        
        Competitor selectedCompetitor = this.competitorsTreeSet.getElementAt(indexOfCompetitor);
        super.removeCompetitor(indexOfCompetitor);
        
        this.activeCompetitorsTreeSet.remove(selectedCompetitor);
    }
    
    @Override
    public void eraseScore (int sportDayIndex,int gameIndex){
        
        super.eraseScore(sportDayIndex, gameIndex);
        
        updateReadyForLottery();
    } 
     
    // method that gives or take back the prize of a game at the cards of the 2 involved competitors
    // e.g if the game is: compA - compB: 3-1 and championship's type is cup
    // *** if isForGiving == true
    //     -------------------- it will remove the compB from the activeCompetitorsTreeset,
    //     -------------------- it will add 3 from "score for" of compA
    //     -------------------- it will add 1 from "score against" of compA
    //     -------------------- it will add 3 from "score against" of compB
    //     -------------------- it will add 1 from "score for" of compB
    // *** if isForGiving == false
    //     -------------------- it will add the compB to the activeCompetitorsTreeset,
    //     -------------------- it will subtract 3 from "score for" of compA
    //     -------------------- it will subtract 1 from "score against" of compA
    //     -------------------- it will subtract 3 from "score against" of compB
    //     -------------------- it will subtract 1 from "score for" of compB
    @Override
    public void giveOrTakeBackPrizeOfGame(Game currentGame, boolean isForGiving){
        
        super.giveOrTakeBackPrizeOfGame(currentGame, isForGiving);
        
        TypeOfResult homeTypeOfResultObject = currentGame.getResultforHomeCompetitor();
        Competitor homeCompetitor = currentGame.getHomeCompetitor();
        Competitor awayCompetitor = currentGame.getAwayCompetitor();
            
        if(isForGiving){    
            switch (homeTypeOfResultObject) {
                case WIN:
                    this.activeCompetitorsTreeSet.remove(awayCompetitor);
                    break;
                case TIE:
                    // throw exception
                    break;
                case DEFEAT:
                    this.activeCompetitorsTreeSet.remove(homeCompetitor);
                    break;
                default:
            } 
        }
        else{
             switch (homeTypeOfResultObject) {
                case WIN:
                    this.activeCompetitorsTreeSet.add(awayCompetitor);
                    break;
                case TIE:
                    // throw exception
                    break;
                case DEFEAT:
                    this.activeCompetitorsTreeSet.add(homeCompetitor);
                    break;
                default:
            }
        }  
    }
    
    @Override
    public void makeLottery(){
        
        if (!finalizable || !this.readyForLottery)
            return;
            
        this.finalized = true;
        this.readyForLottery = false;
        
        if(getNumberOfActiveCompetitors()==2)
            this.finalAlreadySet = true;
        
        // call the appropriate methods for lottering this cup
        LotteryBallsHashSet lotteryBallsHashSetObject = new LotteryBallsHashSet(this);
        lotteryBallsHashSetObject.createLotteryBalls();
        lotteryBallsHashSetObject.makeLotteryForCup();
    }
    
    // method that is given a CoupleOfCompetitorsIndexLotteryBall object which 
    // refers to indexes of activeCompetitorsTreeSet. It transforms to  the authentic
    // indexes of the 2 competitors at the competitorsTreeSet (and returns a 
    // CoupleOfCompetitorsIndexLotteryBall object).
    public CoupleOfCompetitorsIndexLotteryBall getLotteryBallWithUpdatedIndexes
        (CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject){
        
        Competitor homeCompetitor = this.activeCompetitorsTreeSet.
                                  getElementAt(coupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex());
        int homeIndex = (super.competitorsTreeSet.getIndexByMirrorObject(homeCompetitor));
        
        Competitor awayCompetitor = this.activeCompetitorsTreeSet.
                                  getElementAt(coupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex());
        int awayIndex = (super.competitorsTreeSet.getIndexByMirrorObject(awayCompetitor));
        
        return new CoupleOfCompetitorsIndexLotteryBall(homeIndex,awayIndex);
    }
}
