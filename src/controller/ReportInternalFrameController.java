
package controller;

import adjustedClasses.StaticChecker;
import enums.TableSortEnum;
import exceptions.InvalidNonNegativeIntegerInputException;
import exceptions.InvalidResultTieException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.AmateurSports;
import model.CardChampionship;
import model.Championship;
import model.ChampionshipLeague;
import model.Competitor;
import model.Game;
import view.ReportInternalFrame;

public class ReportInternalFrameController extends AbstractInternalFrameController {
    
    private ReportInternalFrame internalFrameObject;
    private Championship championshipObject;
    
    // constructor
    public ReportInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                               ReportInternalFrame internalFrameObject,AmateurSports amateurSportsObject,
                               CommonMethodsController commonMethodsControllerObject){
                                                                                
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
        this.internalFrameObject = internalFrameObject;
        internalFrameObject.setReportInternalFrameControllerObject(this);
    }
    
    public void serialize(){
        this.commonMethodsControllerObject.serialize();
    }
    
    public void setChampionshipObject(Championship championshipObject){
        this.championshipObject = championshipObject;
    }
    
    public Championship getChampionshipObject(){
        return this.championshipObject;
    }
    
    public void initializeInternalFrameObjectComponents(){
        
        // initialize compnents of championshipsInternalFrameObject
        this.internalFrameObject.updatetypeOfSortForTableComboBox();
    }
    
    public void updateTypeOfSortForTableComboBoxModel(DefaultComboBoxModel<String> 
                                                typeOfSortForTableComboBoxModelObject){
        
        for (TableSortEnum currentTableSortEnumObject: TableSortEnum.values())
                typeOfSortForTableComboBoxModelObject.addElement(currentTableSortEnumObject.toString());
            
    
    }
    
    public void updateGamesTableModel(DefaultTableModel gamesTableModelObject,Integer phaseNumber){
        
        if (!this.championshipObject.getFinalized())
            return;
        
        for (Game currentGame: this.championshipObject.getsportDayAtIndex(phaseNumber)){
            
            Integer homeScoreInt = currentGame.getHomeScore();
            Integer awayScoreInt = currentGame.getAwayScore();
            
            String homeScoreString;
            String awayScoreString;
            
            if (homeScoreInt !=-1){
                homeScoreString = homeScoreInt.toString();
                awayScoreString = awayScoreInt.toString();
            }
            else{
                homeScoreString = "-";
                awayScoreString = "-";
            }
                
            gamesTableModelObject.addRow(new Object[]{
                                        currentGame.toString(),homeScoreString,awayScoreString 
                }
            );
        }
        
    }
    
    public void updateTableTableModel(DefaultTableModel tableTableModelObject,
                                                    TableSortEnum jTableSortEnumObject){
        
        if (jTableSortEnumObject == TableSortEnum.BASIC)
            this.championshipObject.basicUpdateTableList();
        else
            this.championshipObject.updateTableList(jTableSortEnumObject);
        
        for (Competitor currentCompetitor:this.championshipObject.getTableList()){
            
            CardChampionship currentCompetitorCard = this.championshipObject.getCardOfCompetitor(currentCompetitor);
            
            tableTableModelObject.addRow(new Object[]{
                                     currentCompetitor.toString(),
                                     currentCompetitorCard.getPoints(),
                                     currentCompetitorCard.getNumberOfGamesPlayed(),
                                     currentCompetitorCard.getNumberOfWins(),
                                     currentCompetitorCard.getNumberOfTies(),
                                     currentCompetitorCard.getNumberOfDefeats(),
                                     currentCompetitorCard.getScoreFor(),
                                     currentCompetitorCard.getScoreAgainst(),
                                     currentCompetitorCard.getScoreDifference()
                });
        }
    }
    
    public void makeLottery(){
        this.championshipObject.makeLottery();
    }
    
    public int getMaxOfIndexForSportdays(){
        return this.championshipObject.getSizeOfSportDaysList() - 1;
    }
    
    /*
    public String getTitleOfSportDay(){
        
    }
    */
    
    public void updateScores(int rowModelIndex, int phaseNumber, 
                                              DefaultTableModel gamesTableModelObject ) throws InvalidResultTieException, InvalidNonNegativeIntegerInputException{
                       
        String homeScoreString;
        String awayScoreString;
        int homeScoreInt;
        int awayScoreInt;
        
        this.championshipObject.eraseScore(phaseNumber, rowModelIndex);
        
        homeScoreString = (String)gamesTableModelObject.getValueAt(rowModelIndex, 1);
        awayScoreString = (String)gamesTableModelObject.getValueAt(rowModelIndex, 2);
        
        if (!this.championshipObject.isTieAllowed() && homeScoreString.equals(awayScoreString)){
            
            this.internalFrameObject.eraseCellValueOfGamesTable(rowModelIndex, 1);
            this.internalFrameObject.eraseCellValueOfGamesTable(rowModelIndex, 2);
            //homeScoreString ="-";
            //awayScoreString ="-";
            
            throw new InvalidResultTieException();
        }
        
        if (StaticChecker.checkForInvalidNonNegativeIntegerInputException(homeScoreString)
            || StaticChecker.checkForInvalidNonNegativeIntegerInputException(awayScoreString))
            throw new InvalidNonNegativeIntegerInputException();
            
        homeScoreInt = Integer.parseInt(homeScoreString);
        awayScoreInt = Integer.parseInt(awayScoreString);
        
        this.championshipObject.updateScore(phaseNumber, rowModelIndex, homeScoreInt, awayScoreInt);
    }
    
    public void updatePhaseNumberOfInternalFrameOBject(){
        this.internalFrameObject.setPhaseNumber(this.championshipObject.getCurrentSportDay());
    }
    
    
    
    public void updateCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber(){
        
        String CurrentSportDayDescriptionString = this.championshipObject.
                                                    getCurrentSportDayDescriptionStringByGivingSpecificPhaseNumber
                                                    (this.internalFrameObject.getPhaseNumber());
        
        this.internalFrameObject.setCurrentSportDayDescriptionString(CurrentSportDayDescriptionString); 
    }
    
    public boolean getVisibilityOfLotteryButton(){
        return this.championshipObject.isLotteriable();
    }
    
    public boolean isGamesTableEditable(){
        if (this.championshipObject instanceof ChampionshipLeague)
            return true;
        else if(this.internalFrameObject.getPhaseNumber() == this.championshipObject.getSizeOfSportDaysList()-1)
            return true;
        else 
            return false;
    }
    
    public boolean isPointBased(){
        return this.championshipObject.isPointBased();
    }
    
    public boolean isTieAllowed(){
        return this.championshipObject.isTieAllowed();
    }
    
    public void normalizeInputOfCell(int rowModelIndex, DefaultTableModel gamesTableModelObject){
        
        String homeScoreString = (String)gamesTableModelObject.getValueAt(rowModelIndex, 1);
        String awayScoreString = (String)gamesTableModelObject.getValueAt(rowModelIndex, 2);
       
        if(StaticChecker.checkForInvalidNonNegativeIntegerInputException(homeScoreString))
            this.internalFrameObject.eraseCellValueOfGamesTable(rowModelIndex, 1);
      
        if(StaticChecker.checkForInvalidNonNegativeIntegerInputException(awayScoreString))
            this.internalFrameObject.eraseCellValueOfGamesTable(rowModelIndex, 2);
    }
    
    public String getReportGuideString(){
        return this.championshipObject.getReportGuideString();
    }
}
