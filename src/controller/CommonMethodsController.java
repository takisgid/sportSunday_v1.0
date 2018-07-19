
package controller;

import adjustedClasses.TreeSetWithGetIndex;
import enums.Sport;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.AmateurSports;
import model.Competitor;
import model.Player;
import model.Solist;
import model.Team;
import serialization.Serializer;

public class CommonMethodsController {
    
    private AmateurSports amateurSportsObject;
    private Serializer serializerObject;
    
    public CommonMethodsController(AmateurSports amateurSportsObject,Serializer serializatorObject){
        this.amateurSportsObject = amateurSportsObject;
        this.serializerObject = serializatorObject;
    }
    
    public void serialize(){
        this.serializerObject.serialize();
    }
    
    // Mέθοδος που δέχεται DefaultComboBoxModel<String> object και
    // περναει σε αυτό ολα,μόνο τα ομαδικα η μονο τα ατομικα αθλήματα
    // Αν colegiate:true περνιουνται τα ομαδικα
    // Αν colegiate:true περνιουνται τα ατομικά
    public void updateSportComboBoxModel(DefaultComboBoxModel<String> DefaultComboBoxModelObject,
                                                                 boolean colegiate, boolean nonColegiate){
        
        // Διατρεξη του enum Sport και adding των toString
        for (Sport currentSportEnumObject: Sport.values())
            if (currentSportEnumObject.isColegiate() && colegiate)
                DefaultComboBoxModelObject.addElement(currentSportEnumObject.toString());
            else if (!currentSportEnumObject.isColegiate() && nonColegiate)
                DefaultComboBoxModelObject.addElement(currentSportEnumObject.toString());
    }
             
    public void updateCompetitorsListModel(DefaultListModel<String> competitorsDefaultListModelObject,
                 String sportToStringRepresantation, TreeSetWithGetIndex<Competitor> competitorsTreeSet,
                                          boolean isCompetitorsInternalFrameOrAvailableCompetitorsListOfCompetitorsPartition,
                                                                                                  boolean isTeam){
        // Διατρεξη του teamsTreeSet  και προσθήκη των  names των teams στο model που
        // σχετιζονται με το επιλεγμένο στο comboBox αθλημα
        
        Class currentClass;
        
        if (isTeam)
            currentClass = Team.class;
        else
            currentClass = Solist.class;
        
        if (isCompetitorsInternalFrameOrAvailableCompetitorsListOfCompetitorsPartition && isTeam) {
            for (Team currentTeam: this.amateurSportsObject.getTeamsTreeSet())
                if( (currentTeam.getSport().toString().equals(sportToStringRepresantation) &&
                    ((competitorsTreeSet== null)||(competitorsTreeSet!= null && !competitorsTreeSet.contains(currentTeam)))))
   
                      competitorsDefaultListModelObject.addElement(currentTeam.toString());
        }
        else if (isCompetitorsInternalFrameOrAvailableCompetitorsListOfCompetitorsPartition && !isTeam) {
            for (Solist currentSolist: this.amateurSportsObject.getSolistsTreeSet())
                if( (currentSolist.getSport().toString().equals(sportToStringRepresantation) &&
                    ((competitorsTreeSet== null)||(competitorsTreeSet!= null && !competitorsTreeSet.contains(currentSolist)))))
   
                    competitorsDefaultListModelObject.addElement(currentSolist.toString());
        }
        else{
            for (Competitor currentCompetitor: competitorsTreeSet)
                competitorsDefaultListModelObject.addElement((currentClass.cast(currentCompetitor)).toString());
        }
        
    }
    
    public void updateRosterListModel(DefaultListModel<String> rosterDefaultListModelObject,Team selectedTeam,
                                                                  TreeSetWithGetIndex<Player> inRosterTreeSet,
                                                                  boolean isAvailable){
        
        TreeSetWithGetIndex<Player> teamsTreeSet = selectedTeam.getPlayerTreeSet();
        
        if (inRosterTreeSet == null)
            for (Player currentPlayer: teamsTreeSet)
                rosterDefaultListModelObject.addElement(currentPlayer.toString()); 
        else if (isAvailable){
            for (Player currentPlayer: teamsTreeSet)
                if (!inRosterTreeSet.contains(currentPlayer))
                    rosterDefaultListModelObject.addElement(currentPlayer.toString());
        }
        else
            for (Player currentPlayer: inRosterTreeSet)
                    rosterDefaultListModelObject.addElement(currentPlayer.toString());
        
    }
    
    
    public Team getSelectedTeam(String name,String collegiateSportToStringString){
        
        Team targetTeam =new Team(name, Sport.byGivenString(collegiateSportToStringString));
        return(this.amateurSportsObject.getTeam(targetTeam));
    }
    
    public Solist getSelectedSolist(String toStringRepresantationString){
        return(this.amateurSportsObject.getSolist(toStringRepresantationString));
    }
}
