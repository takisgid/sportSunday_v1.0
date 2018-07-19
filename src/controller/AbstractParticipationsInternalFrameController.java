
package controller;

import javax.swing.DefaultListModel;
import model.AmateurSports;
import model.Championship;
import model.ChampionshipCup;
import model.ChampionshipLeague;
import model.Competitor;

public abstract class AbstractParticipationsInternalFrameController extends AbstractInternalFrameController {
    
    protected Championship championshipObject;
    
    public AbstractParticipationsInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject,
                             AmateurSports amateurSportsObject, CommonMethodsController commonMethodsControllerObject) {
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
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
    
    public boolean isChampionshipObjectFinalized(){
        return this.championshipObject.getFinalized();
    }
    
    public abstract void updateAvailableCompetitorsListModel(DefaultListModel<String> teamsDefaultListModelObject);
    
    public abstract void updateInCompetitorsListModel(DefaultListModel<String> teamsDefaultListModelObject);
    
    public abstract Competitor getSelectedCompetitor(String name);
    
    public void addCompetitorToChampionship(Competitor competitorObject){
       if (this.championshipObject instanceof ChampionshipLeague)
           ((ChampionshipLeague)this.championshipObject).addCompetitor(competitorObject);
       else
           ((ChampionshipCup)this.championshipObject).addCompetitor(competitorObject);
    }
    
    public void removeCompetitorFromChampionship(int indexOfCompetitor){
        
        this.championshipObject.removeCompetitor(indexOfCompetitor);
      
    }
    
    public String getParticipationsGuideString(){
        return this.championshipObject.getParticipationsGuideString();
    }  
}
