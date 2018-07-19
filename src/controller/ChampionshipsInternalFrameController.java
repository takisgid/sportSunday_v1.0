
package controller;

import adjustedClasses.StaticChecker;
import enums.Sport;
import exceptions.InvalidAShouldBeSmallerEqualThanBInputException;
import exceptions.InvalidTextException;
import exceptions.InvalidPositiveIntegerInputException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.AmateurSports;
import model.Championship;
import view.ChampionshipsInternalFrame;

public class ChampionshipsInternalFrameController extends AbstractInternalFrameController{
    
    private ChampionshipsInternalFrame internalFrameObject;
    
    // constructor
    public ChampionshipsInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                               ChampionshipsInternalFrame internalFrameObject,AmateurSports amateurSportsObject,
                                                            CommonMethodsController commonMethodsControllerObject){
                                                                                
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
        this.internalFrameObject = internalFrameObject;
        internalFrameObject.setChampionshipsInternalFrameControllerObject(this);
    }
    
    public void updateSportComboBoxModel(DefaultComboBoxModel<String> DefaultComboBoxModelObject,
                                                                 boolean colegiate, boolean nonColegiate){
        this.commonMethodsControllerObject.updateSportComboBoxModel(DefaultComboBoxModelObject, colegiate,
                                                                                                 nonColegiate);
    }
    
    public void updateNumberOfCompetitorsColumn(DefaultTableModel championshipsTableModelObject){
        
        for(int counter=0; counter<this.amateurSportsObject.getChampionshipTreeSet().size(); counter++)
            championshipsTableModelObject.setValueAt(
                    (Integer)this.amateurSportsObject.getChampionshipAt(counter).getNumberOfCompetitors(),
                    counter, 5);
    }
    
    public void updateStatusColumn(DefaultTableModel championshipsTableModelObject){
        
        for(int counter=0; counter<this.amateurSportsObject.getChampionshipTreeSet().size(); counter++)
            championshipsTableModelObject.setValueAt(
                    (String)this.amateurSportsObject.getChampionshipAt(counter).getStatusOfChampionship(),
                    counter, 9);
    }
    
    public void updateChampionshipsTableModel(DefaultTableModel championshipsTableModelObject){
        
        // Διατρεξη του championshipsTreeSet  και προσθήκη των στοιχειων των championships στο model
        for (Championship currentChampionship: this.amateurSportsObject.getChampionshipTreeSet()) {
            
            championshipsTableModelObject.addRow(new Object[]{currentChampionship.getSport().toString(),
                            currentChampionship.getChampionshipTypeString(),
                      (Integer)currentChampionship.getMinRoster() + " vS " + (Integer)currentChampionship.getMinRoster(),
                            currentChampionship.getName(),(Integer)currentChampionship.getMaxRoster(),
                            (Integer)currentChampionship.getNumberOfCompetitors(),
                            (Integer)currentChampionship.getNumberOfCompetitorsBigO(),
                            (Integer)currentChampionship.getNumberOfGamesForEachCompetitor(),
                            currentChampionship.getBigTrophyString(),
                            currentChampionship.getStatusOfChampionship()
            });
        }
    }
    
    // Method which returns το επιλεγμένο από τον πίνακα πρωτάθλημα
    public Championship getChampionshipAt(int index){
        
        return this.amateurSportsObject.getChampionshipAt(index);
        
    }
    
    public void checkCreateChampionship(String championshipNameString,
                                   String numberOfCompetitorsBigOString, String bigTrophyString,
                                   String minRosterString,String maxRosterString) 
                                                                throws InvalidPositiveIntegerInputException, InvalidAShouldBeSmallerEqualThanBInputException, InvalidTextException{
     
        //checks
        if (StaticChecker.checkForInvalidPositiveIntegerInputException(minRosterString)){
            this.internalFrameObject.clearMinRosterTextField();
            throw new InvalidPositiveIntegerInputException("\"No of players\"");
        }
        if (StaticChecker.checkForInvalidPositiveIntegerInputException(maxRosterString)){
            this.internalFrameObject.clearMaxRosterTextField();
            throw new InvalidPositiveIntegerInputException("\"No of Roster\"");
        }
        if (StaticChecker.checkForInvalidPositiveIntegerInputException(numberOfCompetitorsBigOString)){
            this.internalFrameObject.clearNumberOfCompetitorsTextField();
            throw new InvalidPositiveIntegerInputException("\"Limit of competitors\"");
        }
        if (StaticChecker.checkForInvalidAShouldBeSmallerEqualThanBInputException
                                                   (minRosterString,maxRosterString)){
            this.internalFrameObject.clearMinRosterTextField();
            this.internalFrameObject.clearMaxRosterTextField();
            throw new InvalidAShouldBeSmallerEqualThanBInputException("\"No of players\"",
                                                                      "\"No of Roster\"");
        }
        if (StaticChecker.checkForInvalidTextException(championshipNameString)){
                        this.internalFrameObject.clearChampionshipNameTextField();
            throw new InvalidTextException("\"Championship Name\"");
        }
        if (StaticChecker.checkForInvalidTextException(bigTrophyString)){
                        this.internalFrameObject.clearBigTrophyTextField();
            throw new InvalidTextException("\"Big Trophy\"");
        }
    }
    
    public void createChampionship(boolean leagueFlag, boolean cupFlag,Sport sportObject, String name,
                                   String numberOfCompetitorsBigOString, String bigTrophyString,
                                   String minRosterString,String maxRosterString){ 
                                                                       
        this.amateurSportsObject.createChampionship(leagueFlag,cupFlag, sportObject,name, 
                                    Integer.parseInt(numberOfCompetitorsBigOString),bigTrophyString, 
                                    Integer.parseInt(minRosterString),Integer.parseInt(maxRosterString));
    }
    
    public void removeChampionshipAt(int index){
        this.amateurSportsObject.removeChampionshipAt(index);
    }
    
    public void serialize(){
        this.commonMethodsControllerObject.serialize();
    }
    
    public void initializeInternalFrameObjectComponents(){
        
        // initialize compnents of championshipsInternalFrameObject
        this.internalFrameObject.updateSportComboBox();
        this.internalFrameObject.updateChampionshipsTable();
    }
    
    public void setChampionshipObjectToteamsParticipationsInternalFrameControllerObject 
                                                            (Championship championshipObject) {
        
        this.mainContainerFrameControllerObject.
                            setChampionshipObjectToteamsParticipationsInternalFrameControllerObject
                                                                                (championshipObject);
    }
                                                           
                                                            
    public void setChampionshipObjectTosolistsParticipationsInternalFrameControllerObject 
                                                            (Championship championshipObject) {
        
        this.mainContainerFrameControllerObject
                            .setChampionshipObjectTosolistssParticipationsInternalFrameControllerObject
                                                                                (championshipObject);
    }
    
    public void setChampionshipObjectToReportInternalFrameControllerObject 
                                                            (Championship championshipObject) {
        
        this.mainContainerFrameControllerObject.
                            setChampionshipObjectToReportInternalFrameControllerObject
                                                                                (championshipObject);
    }
           
    public boolean checkIfCollegiate(String sportString){
        Sport sportObject = Sport.byGivenString(sportString);
        
        return sportObject.isColegiate();
    }                                                       
}
