
package controller;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.AmateurSports;
import model.Competitor;
import model.Team;
import view.TeamsParticipationsInternalFrame;

public class TeamsParticipationsInternalFrameController extends AbstractParticipationsInternalFrameController {
    
    private TeamsParticipationsInternalFrame internalFrameObject;
    
    // constructor
    public TeamsParticipationsInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                               TeamsParticipationsInternalFrame internalFrameObject,AmateurSports amateurSportsObject,
                                                                CommonMethodsController commonMethodsControllerObject){
                                                                                
        super(mainContainerFrameControllerObject,amateurSportsObject, commonMethodsControllerObject);
        this.internalFrameObject = internalFrameObject;
        internalFrameObject.setCompetitorsParticipationsInternalFrameControllerObject(this);
    }
    
    public void updateAvailableCompetitorsListModel(DefaultListModel<String> teamsDefaultListModelObject){
        
        this.commonMethodsControllerObject.updateCompetitorsListModel(teamsDefaultListModelObject, 
                this.championshipObject.getSport().toString(),this.championshipObject.getCompetitorsTreeSet(),
                                                                                                    true,true);
    }
    
    public void updateInCompetitorsListModel(DefaultListModel<String> teamsDefaultListModelObject){
        
        this.commonMethodsControllerObject.updateCompetitorsListModel(teamsDefaultListModelObject, 
                this.championshipObject.getSport().toString(),this.championshipObject.getCompetitorsTreeSet(),
                                                                                                    false, true);
    }
    
     public Competitor getSelectedCompetitor(String name){
        return this.commonMethodsControllerObject.getSelectedTeam(name, 
                                                           this.championshipObject.getSport().toString());
    }
     
    public void addCompetitorToChampionship(Competitor teamObject){
       super.addCompetitorToChampionship(teamObject);
    }
    
    public void removeCompetitorFromChampionship(int indexOfTeam){
        super.removeCompetitorFromChampionship(indexOfTeam);
    }
    
    public void updateTeamsComboBoxModel(DefaultComboBoxModel<String> teamsComboBoxModelObject){
        
        teamsComboBoxModelObject.removeAllElements();
        
        for (Competitor currentTeam: this.championshipObject.getCompetitorsTreeSet())
            teamsComboBoxModelObject.addElement(((Team)currentTeam).toString());
    }
    
    public void updateAvailableRosterListModel(DefaultListModel<String> availableRosterDefaultListModelObject,
                                                                                        int selectedTeamIndex){
        if (this.championshipObject.getCompetitorsTreeSet().isEmpty())
            return;
        
        Team selectedTeam = (Team) this.championshipObject.getCompetitorsTreeSet().getElementAt(selectedTeamIndex);
        this.commonMethodsControllerObject.updateRosterListModel(availableRosterDefaultListModelObject,
                   selectedTeam, this.championshipObject.getInRosterTreesetForTeam(selectedTeam), true);
    }
    
    public void updateInRosterListModel(DefaultListModel<String> inRosterDefaultListModelObject,
                                                                                        int selectedTeamIndex){
        
        if (this.championshipObject.getCompetitorsTreeSet().isEmpty())
            return;
        
        Team selectedTeam = (Team) this.championshipObject.getCompetitorsTreeSet().getElementAt(selectedTeamIndex);
        
        this.commonMethodsControllerObject.updateRosterListModel(inRosterDefaultListModelObject,
                   selectedTeam, this.championshipObject.getInRosterTreesetForTeam(selectedTeam), false);
    }
    
    /*
    public int getNumberOfInRoster(int teamIndex){
        return this.championshipObject.getNumberOfInRoster(teamIndex);
    }
    */
    
    public void addPlayerToInRoster(int teamIndex, String selectedPlayerToString){
        this.championshipObject.addPlayerToInRosterForTeamWithIndex(teamIndex, selectedPlayerToString);
    }
    
    public void removePlayerFromInRoster(int teamIndex, int selectedPlayerIndex){
        this.championshipObject.removePlayerFromInRosterForTeamWithIndex(teamIndex, selectedPlayerIndex);
    }
}
