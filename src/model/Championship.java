
package model;

import comparators.ByScoreAgainstComparator;
import comparators.ByScoreDifferenceComparator;
import comparators.ByScoreForComparator;
import lotteryClasses.CoupleOfCompetitorsIndexLotteryBall;
import adjustedClasses.TreeSetWithGetIndex;
import enums.Sport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import adjustedClasses.UsefulStaticStuff;
import static enums.Sport.BASKETBALL;
import enums.TableSortEnum;
import interfaces.Nameable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// abstraction that includes every type of sport tournament
public abstract class Championship implements Comparable,Nameable,Serializable {
    
    private Sport sportObject;
    private String name;
    private String bigTrophyString; // describes the first prize
    
    private int numberOfCompetitorsBigO; // limit-target number of competitors
    protected int numberOfCompetitors; // applied participants so far
    
    private int minRoster; // Number of athletes playing at the moment (without substitutes).
                           // 1 always for non colegiate.
    private int maxRoster; // Number of athletes playing at the moment plus substitutes
    
    protected boolean finalizable; // Express the status that a championship can start
    protected boolean finalized; // Express the status that a championship has started
    
    // treeset that contains the competitors of the championship
    // they are sorted alphabetically
    protected TreeSetWithGetIndex<Competitor> competitorsTreeSet;
    
    // HashMap that contains the competitors of the championship as keys.
    // For every championship is matched as value the card of this
    // exact championship
    protected HashMap<Competitor,CardChampionship> competitorsHashMap;
    
    // ArrayList that contains an internal arraylist which contains all the games
    // of a specific game week or championship phase
    protected ArrayList<ArrayList<Game>> sportDaysList;
    
    // list that contains the competitors sorted by their success
    // at this championshp in decreasing way.
    protected List<Competitor> tableList;
    
    // variable that marks the game week or championship phase where
    // the tournament has reached.
    // It is given the value of the bigger index of sportDaysList
    // where a game with valid score has been registered
    protected Integer currentSportDay;
    
    // Constructor
    public Championship(Sport sportObject, String name, int numberOfCompetitorsBigO, String trophyString, 
                                                                            int minRoster,int maxRoster){
        this.sportObject = sportObject;
        this.name = name;
        this.numberOfCompetitorsBigO = numberOfCompetitorsBigO;
        this.bigTrophyString = trophyString;
        this.minRoster = minRoster;
        this.maxRoster = maxRoster;
        
        competitorsTreeSet = new TreeSetWithGetIndex<>();
        competitorsHashMap = new HashMap<>();
        this.numberOfCompetitors = competitorsTreeSet.size();
        
        this.finalizable = false;
        this.finalized = false;
        
        this.sportDaysList = new ArrayList<>();
        
        currentSportDay = 0;
    }
    
    // getters
    public Sport getSport(){ 
        return this.sportObject;
    }
    public String getName(){ // required method of Nameable interface
        return this.name;
    }
    public String getBigTrophyString(){
        return this.bigTrophyString;
    }
    public Integer getNumberOfCompetitorsBigO (){
        return this.numberOfCompetitorsBigO;
    }
    public Integer getNumberOfCompetitors(){
        return this.numberOfCompetitors;
    }
    public Integer getMinRoster(){
        return this.minRoster;
    }
    public Integer getMaxRoster(){
        return this.maxRoster;
    }
    public CardChampionship getCardOfCompetitor(Competitor competitorObject){
        return this.competitorsHashMap.get(competitorObject);
    }
    public TreeSetWithGetIndex<Competitor> getCompetitorsTreeSet(){
        return this.competitorsTreeSet;
    }
    public boolean getFinalizable(){
        return this.finalizable;
    }
    public boolean getFinalized(){
        return this.finalized;
    }
    public ArrayList<Game> getsportDayAtIndex(Integer phaseNumber){
        return this.sportDaysList.get(phaseNumber);
    }
    public int getSizeOfSportDaysList(){
        return this.sportDaysList.size();
    }        
    public List<Competitor>  getTableList(){
        return this.tableList;
    }
    public Integer  getCurrentSportDay(){
        return this.currentSportDay;
    }
    public TreeSetWithGetIndex<Player> getInRosterTreesetForTeam(Team currentTeam){
        
        return this.competitorsHashMap.get(currentTeam).getInRosterTreeSet();
    }
    public ArrayList<ArrayList<Game>> getsportDaysList(){
        return sportDaysList;
            }
    
    // method that returns the max possible games for a participant of this
    // championship.
    public abstract int getNumberOfGamesForEachCompetitor();
    
    // method that returns the description of the type of this championship
    public abstract String getChampionshipTypeString();
    
    // method that returns verbal description of current sportDay
    public String getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber
                                                                    (Integer phaseNumber){
        return "Fixtures are not arranged, yet";
    }
    
    // method that returns verbal guidance for the user when he is in Report screen                                                             
    public String getReportGuideString(){
        return "Number of competetors is not complete,yet." + System.lineSeparator() +
                                                         "Go to Participations, please.";
    }
    
    // method that returns verbal guidance for the user when he is in Participations screen
    public String getParticipationsGuideString(){
        
        if (!this.finalizable)
            return "You should add competitors" + System.lineSeparator() 
                    + "until reaching the limit.";
        else if (!finalized)
            return "You have reached the limit" + System.lineSeparator() 
                    + "but you may make changes.";
        else 
            return "This championship has already started." + System.lineSeparator() 
                    + "Changes in competitors are not allowed.";
        
    }
    // method that returns the description of the status of this championship
    public String getStatusOfChampionship(){
        if (!this.finalizable)
            return "Unready";
        else if(!finalized)
            return "Ready";
        else
            return "Started";
    }                                                                
                                                                    
                                                                    
    //setters
    public void setSport(Sport sportObject){
        this.sportObject = sportObject;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNumberOfCompetitorsBigO(int numberOfCompetitorsBigO){
        this.numberOfCompetitorsBigO = numberOfCompetitorsBigO;
    }
    public void setBigTrophyString(String bigTrophyString){
        this.bigTrophyString = bigTrophyString;
    }
    public void setMinRoster(int minRoster){
        this.minRoster = minRoster;
    }
    public void setMaxRoster(int maxRoster){
        this.maxRoster = maxRoster;
    }
    
    // Sorting alphabetically (ignoring case):
    // --- 1st criterion: Sport toString()
    // --- 2nd criterion: Championship name
   @Override
    public int compareTo(Object otherObject){
        
        Championship otherChampionship = (Championship) otherObject;
        
        if (this.sportObject.toString().compareToIgnoreCase(otherChampionship.sportObject.toString()) != 0)
                return (this.sportObject.toString().compareToIgnoreCase(otherChampionship.sportObject.toString()));
        
        return (UsefulStaticStuff.compareAlphabeticalOrder(this, otherChampionship)); 
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    //some boolean
    public abstract boolean isLotteriable();
    
    public boolean isTieAllowed(){
        return this.sportObject.isTieAllowed();
    }
    
    public abstract boolean isPointBased();
    
    
    // updaters
    public void updateNumberOfCompetitors(){
        this.numberOfCompetitors = this.competitorsTreeSet.size();
    }
    public void updateFinalizable(){
        
        if (this.finalized == true)
            return;
        
        if (this.numberOfCompetitors >= this.numberOfCompetitorsBigO)
            this.finalizable = true;
        else
            this.finalizable = false;
    }
    
    // updates tableList according to the basic (for this type of championship) way 
    public abstract void basicUpdateTableList();
    
    // updates tableList according to the selected by the user way
    public void updateTableList(TableSortEnum tableSortEnumObject ){
        
        Comparator comparatorObject = null;
        
        switch (tableSortEnumObject) {
            
            case BYSCOREDIFFERENCE:
                    comparatorObject = new ByScoreDifferenceComparator(this);
                    break;
            case BYSCOREFOR:
                    comparatorObject = new ByScoreForComparator(this);
                    break;
            case BYSCOREAGAINST:
                    comparatorObject = new ByScoreAgainstComparator(this);
                    break;
            default:
        }
       
        List<Competitor> tableByPointslist = new ArrayList<>(this.competitorsTreeSet);
        Collections.sort(tableByPointslist, comparatorObject);
        
        this.tableList = tableByPointslist;    
    }
    
    // updates score of a specific game
    public void updateScore(int sportDayIndex,int gameIndex,int homeScoreInt, int awayScoreInt){
        
        Game currentGame = this.sportDaysList.get(sportDayIndex).get(gameIndex);
        currentGame.setHomeScore(homeScoreInt);
        currentGame.setAwayScore(awayScoreInt);
        
        // updates records of the competetors which are involved in the current game
        giveOrTakeBackPrizeOfGame(currentGame,true);
        
        // updates currentSportday after updating the score of current game
        if (sportDayIndex > this.currentSportDay)
            this.currentSportDay = sportDayIndex;   
    }
    
    // updates currentSportDay after erasing a game of this Championship object
    public void updateCurrentSportDayAfterErase(Integer sportDayIndexOfErasedGame){
        
        if (this.currentSportDay > sportDayIndexOfErasedGame)
            return;
        
        int counter;
        
        outerLoop:
        for (counter=sportDayIndexOfErasedGame;counter>=0;counter--)
            for(Game currentGame:this.sportDaysList.get(counter))
                if (currentGame.getHomeScore() != -1 && currentGame.getAwayScore()!= -1) 
                    break outerLoop;
        
        if (counter == -1)
            counter = 0;
        
        this.currentSportDay = counter;       
    }   
    
    // erase score of a specific game
    public void eraseScore (int sportDayIndex,int gameIndex){
        
        Game currentGame = this.sportDaysList.get(sportDayIndex).get(gameIndex);
        
        if (currentGame.getHomeScore() == -1 || currentGame.getAwayScore() == -1)
            return;
        
        // updates records of the competetors which were involved in the current game
        giveOrTakeBackPrizeOfGame(currentGame,false);
        
        currentGame.setHomeScore(-1);
        currentGame.setAwayScore(-1);
        
        // updates currentSportday after updating the score of current game
        updateCurrentSportDayAfterErase(sportDayIndex);   
    }
    
    //method for adding competitors to the Championship
    public void addCompetitor(Competitor competitorObject, CardChampionship cardChampionshipObject){
        
        // check for number Of Competitors limit number
        if (this.numberOfCompetitors < this.numberOfCompetitorsBigO){
            
            this.competitorsTreeSet.add(competitorObject);
            this.competitorsHashMap.put(competitorObject, cardChampionshipObject);
            competitorObject.addChampionship(this);
            updateNumberOfCompetitors();
            this.updateFinalizable();
        }
    }
    
    //method for removing competitors from the Championship
    public void removeCompetitor(int indexOfCompetitor){
        
        Competitor selectedCompetitor = this.competitorsTreeSet.getElementAt(indexOfCompetitor);
        
        this.competitorsTreeSet.removeElementAt(indexOfCompetitor);
        this.competitorsHashMap.remove(selectedCompetitor);
        selectedCompetitor.removeChampionship(this);
        updateNumberOfCompetitors();
        this.updateFinalizable();
    }
    
    // get size of roster for a specific team and championship
    public Integer getNumberOfInRoster(int teamIndex){
        
        Competitor selectedTeam = this.competitorsTreeSet.getElementAt(teamIndex);
        
        return this.competitorsHashMap.get(selectedTeam).getInRosterTreeSet().size();
    }
    
    // add player to declared roster of this championship
    public void addPlayerToInRosterForTeamWithIndex(int teamIndex, String selectedPlayerToString){
        
        Competitor selectedTeam = this.competitorsTreeSet.getElementAt(teamIndex);
         
        if (this.competitorsHashMap.get(selectedTeam).getInRosterTreeSet().size() < this.maxRoster)
            this.competitorsHashMap.get(selectedTeam).addPlayerToInRosterTreeset
                                             (((Team)selectedTeam).getPlayer(selectedPlayerToString));
    }
    
    // remove player from declared roster of this championship
    public void removePlayerFromInRosterForTeamWithIndex(int teamIndex, int selectedPlayerIndex){
        
        Competitor selectedTeam = this.competitorsTreeSet.getElementAt(teamIndex);
         
        this.competitorsHashMap.get(selectedTeam).removePlayerFromRosterTreeset(selectedPlayerIndex);
    }
    
    // make lottery for producing the schedule of games for this championship
    public abstract void makeLottery();
    
    // create new sportDay to sportDaysList
    public void addSportDay(){
        this.sportDaysList.add(new ArrayList<Game>());
    }
    
    // erase every sportday of sportDaysList
    public void clearSportDay(){
        this.sportDaysList.clear();
    }
    
    // method which adds a game (as it occured from the lottery) to the appropriate sportDaysList place
    public void addGameToSportDay(CoupleOfCompetitorsIndexLotteryBall CoupleOfCompetitorsIndexLotteryBall,
                                                                                         int sportDayNumber){
        Game gameObject = null;
        
        switch (this.sportObject) {
            case BASKETBALL:
                gameObject = new BasketballGame((Team)this.competitorsTreeSet.getElementAt
                                                          (CoupleOfCompetitorsIndexLotteryBall.getHomeTeamIndex()),
                                              (Team)this.competitorsTreeSet.getElementAt
                                                            (CoupleOfCompetitorsIndexLotteryBall.getAwayTeamIndex()));
                     break;
            case FOOTBALL:
                gameObject = new FootballGame((Team)this.competitorsTreeSet.getElementAt
                                                          (CoupleOfCompetitorsIndexLotteryBall.getHomeTeamIndex()),
                                              (Team)this.competitorsTreeSet.getElementAt
                                                            (CoupleOfCompetitorsIndexLotteryBall.getAwayTeamIndex()));
                     break;
            case PINGPONG:
                     break;
            case TENNIS:
                     break;
            case VOLLEYBALL:
                     break;
            default:
        }
        
        this.sportDaysList.get(sportDayNumber).add(gameObject);
    }
    
    // erases all the games that belongs to the specific sportDayNumber of sportDaysList
    public void removeGamesOfSportDay(int sportDayNumber){
        
        this.sportDaysList.get(sportDayNumber).clear();
    }
    
    // method that gives or take back the prize of a game at the cards of the 2 involved competitors
    // e.g if the game is: compA - compB: 3-1 and championship's type is league
    // *** if isForGiving == true
    //     -------------------- it will add the winning points of compA,
    //     -------------------- it will add 3 from "score for" of compA
    //     -------------------- it will add 1 from "score against" of compA
    //     -------------------- it will add 3 from "score against" of compB
    //     -------------------- it will add 1 from "score for" of compB
    // *** if isForGiving == false
    //     -------------------- it will subtract the winning points of compA,
    //     -------------------- it will subtract 3 from "score for" of compA
    //     -------------------- it will subtract 1 from "score against" of compA
    //     -------------------- it will subtract 3 from "score against" of compB
    //     -------------------- it will subtract 1 from "score for" of compB
    public void giveOrTakeBackPrizeOfGame(Game currentGame, boolean isForGiving){
        
        boolean isForRecording = isForGiving;
        
        Competitor homeCompetitor = currentGame.getHomeCompetitor();
        Competitor awayCompetitor = currentGame.getAwayCompetitor();
        
        CardChampionship homeCardChampionshipObject = this.competitorsHashMap.get(homeCompetitor);
        CardChampionship awayCardChampionshipObject = this.competitorsHashMap.get(awayCompetitor);
        
        homeCardChampionshipObject.recordOrEraseResult(currentGame,true,isForRecording);
        awayCardChampionshipObject.recordOrEraseResult(currentGame,false,isForRecording);
    }
}
