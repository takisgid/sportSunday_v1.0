
package controller;

import adjustedClasses.StaticShower;
import initialization.InitializeClass;
import model.AmateurSports;
import view.AboutInternalFrame;

public class AboutInternalFrameController extends AbstractInternalFrameController {
    
    private AboutInternalFrame internalFrameObject;
    
    // constructor
    public AboutInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                               AboutInternalFrame internalFrameObject,AmateurSports amateurSportsObject,
                                                            CommonMethodsController commonMethodsControllerObject){
                                                                                
        super(mainContainerFrameControllerObject,amateurSportsObject,commonMethodsControllerObject);
        this.internalFrameObject = internalFrameObject;
        internalFrameObject.setAboutInternalFrameControllerObject(this);
    }
    
    public void InitializeAmateursports(){
        InitializeClass.InitializeAmateursports(this.mainContainerFrameControllerObject.getAmateurSportsObject());
        this.mainContainerFrameControllerObject.refreshChampionshipsInternaFrame();
        this.mainContainerFrameControllerObject.refreshCompetitorsFrame();
    }
    
    public void informAboutUnavailability(){
        StaticShower.showMessage("Sorry, unavailable in version 1." + System.lineSeparator() +
                                             "Wait for version 2!!!" + System.lineSeparator() +
                "Until then, just erase \"greekSportsObject.ser\" file at" + System.lineSeparator() +
                "\"C:\\serializationFolder\" and restart the program!");
    }
}
