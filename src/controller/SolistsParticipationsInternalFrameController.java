
package controller;

import javax.swing.DefaultListModel;
import model.AmateurSports;
import model.Competitor;
import view.SolistParticipationInternalFrame;

public class SolistsParticipationsInternalFrameController extends AbstractParticipationsInternalFrameController {
    
    private SolistParticipationInternalFrame internalFrameObject;
    
    // constructor
    public SolistsParticipationsInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                               SolistParticipationInternalFrame internalFrameObject,AmateurSports amateurSportsObject,
                                                                    CommonMethodsController commonMethodsControllerObject){
                                                                                
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
        this.internalFrameObject = internalFrameObject;
        internalFrameObject.setCompetitorsParticipationsInternalFrameControllerObject(this);
    }
    
    public void updateAvailableCompetitorsListModel(DefaultListModel<String> solistsDefaultListModelObject){
        
        this.commonMethodsControllerObject.updateCompetitorsListModel(solistsDefaultListModelObject, 
                this.championshipObject.getSport().toString(),this.championshipObject.getCompetitorsTreeSet(),
                                                                                                 true, false);
    }
    
    public void updateInCompetitorsListModel(DefaultListModel<String> solistsDefaultListModelObject){
        
        this.commonMethodsControllerObject.updateCompetitorsListModel(solistsDefaultListModelObject, 
                this.championshipObject.getSport().toString(),this.championshipObject.getCompetitorsTreeSet(),
                                                                                                false, false);
    }
    
     public Competitor getSelectedCompetitor(String name){
        return this.commonMethodsControllerObject.getSelectedSolist(name);                                      
    }
     
    public void addCompetitorToChampionship(Competitor solistObject){
       super.addCompetitorToChampionship(solistObject);
    }
    
    public void removeCompetitorFromChampionship(int indexOfSolist){
        super.removeCompetitorFromChampionship(indexOfSolist);
    }
}
