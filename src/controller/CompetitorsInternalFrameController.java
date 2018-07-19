
package controller;

import adjustedClasses.TreeSetWithGetIndex;
import adjustedClasses.StaticChecker;
import model.AmateurSports;
import enums.Sport;
import exceptions.InvalidTextException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.Player;
import model.Solist;
import model.Team;
import view.CompetitorsInternalFrame;

public class CompetitorsInternalFrameController extends AbstractInternalFrameController {
    
    private CompetitorsInternalFrame internalFrameObject;
    
    // constructor
    public CompetitorsInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                                                        CompetitorsInternalFrame InternalFrameObject,
                       AmateurSports amateurSportsObject,CommonMethodsController commonMethodsControllerObject){
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
        this.internalFrameObject = InternalFrameObject;
        InternalFrameObject.setCompetitorsInternalFrameControllerObject(this);
    }
    
    public void updateSportComboBoxModel(DefaultComboBoxModel<String> DefaultComboBoxModelObject,
                                                                 boolean colegiate, boolean nonColegiate){
        this.commonMethodsControllerObject.updateSportComboBoxModel(DefaultComboBoxModelObject, colegiate,
                                                                                                 nonColegiate);
    }
    
    public void updateTeamsListModel(DefaultListModel<String> teamsDefaultListModelObject,
                         String collegiateComboBoxSelectedSportString){
        
        this.commonMethodsControllerObject.updateCompetitorsListModel(teamsDefaultListModelObject, 
                collegiateComboBoxSelectedSportString,null,true,true);
    }
    
    public void updateSolistsListModel(DefaultListModel<String> solistsDefaultListModelObject,
                   String nonCollegiateComboBoxSelectedSportString){
        
       this.commonMethodsControllerObject.updateCompetitorsListModel(solistsDefaultListModelObject, 
                                                     nonCollegiateComboBoxSelectedSportString, null,true,false);
    }
    
    public void updateRosterListModel(DefaultListModel<String> rosterDefaultListModelObject,Team selectedTeam){
        
        this.commonMethodsControllerObject.updateRosterListModel(rosterDefaultListModelObject, selectedTeam,
                                                                                                 null,false);
        
        this.internalFrameObject.setShowNoOfPlayersLabelText(selectedTeam.getNumberOfPlayers());
    }
    public Team getSelectedTeam(String name,String collegiateSportToStringString){
        return this.commonMethodsControllerObject.getSelectedTeam(name, collegiateSportToStringString);
    }
    
    public void checkCreateTeam(String teamNameString)throws InvalidTextException{
        //checks
        if (StaticChecker.checkForInvalidTextException(teamNameString)){
            this.internalFrameObject.clearTeamNameTextField();
            throw new InvalidTextException("\"Team name\"");
        }
    }
    
    public void createTeam(String name, String collegiateSportToStringString){
        this.amateurSportsObject.createTeam(name,Sport.byGivenString(collegiateSportToStringString));
    }
    public void createTeam(String name, String collegiateSportToStringString, 
                                                            TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex){
        this.amateurSportsObject.createTeam(name,Sport.byGivenString(collegiateSportToStringString), 
                                                                                        rosterTreeSetWithGetIndex);
    }
    public void removeTeam(Team targetTeam){
        this.amateurSportsObject.removeTeam(targetTeam);
    }
    public void checkCreatePlayer(String lastName, String firstName)throws InvalidTextException{
        //checks
        if (StaticChecker.checkForInvalidTextException(lastName)){
            this.internalFrameObject.clearPlayerLastNameTextField();
            throw new InvalidTextException("\"Last name\"");
        }
        if (StaticChecker.checkForInvalidTextException(firstName)){
            this.internalFrameObject.clearPlayerFirstNameTextField();
            throw new InvalidTextException("\"First name\"");
        }
    }
    public void createPlayer (Team currentTeam, String lastName, String firstName){
        currentTeam.createPlayer(this.amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                          lastName, firstName, currentTeam.getSport());
    }
    public void createPlayer (Team currentTeam, String lastName, String firstName,int playerIdInt){
        currentTeam.createPlayer(playerIdInt,lastName, firstName, currentTeam.getSport());
    }
    public void removePlayer (Team currentTeam, int selectedIndex){
        currentTeam.removePlayerAt(selectedIndex);
    }
    
    public void checkCreateSolist(String lastName, String firstName)throws InvalidTextException{
        //checks
        if (StaticChecker.checkForInvalidTextException(lastName)){
            this.internalFrameObject.clearSolistLastNameTextField();
            throw new InvalidTextException("\"Last name\"");
        }
        if (StaticChecker.checkForInvalidTextException(firstName)){
            this.internalFrameObject.clearSolistFirstNameTextField();
            throw new InvalidTextException("\"First name\"");
        }
    }
    
    public void createSolist (String lastName, String firstName, String sportString){
        this.amateurSportsObject.createSolist(lastName, firstName, Sport.byGivenString(sportString));
    }
    public void createSolist (int solistId, String lastName, String firstName, String sportString){
        this.amateurSportsObject.createSolist(solistId, lastName, firstName, Sport.byGivenString(sportString));
    }
    public void removeSolist(String toStringRepresantationString){
        this.amateurSportsObject.removeSolist(toStringRepresantationString);
    }
    public Solist getSelectedSolist(String toStringRepresantationString){
        return this.commonMethodsControllerObject.getSelectedSolist(toStringRepresantationString);
    }
    public void serialize(){
        this.commonMethodsControllerObject.serialize();
    }
    
    public void initializeInternalFrameObjectComponents(){
        this.internalFrameObject.updateCollegiateSportComboBox();
        this.internalFrameObject.updateNonCollegiateSportComboBox();
        this.internalFrameObject.updateTeamsList();
        this.internalFrameObject.updateRosterList();
        this.internalFrameObject.updateSolistsList();
    }
}
