
package controller;

import adjustedClasses.StaticShower;
import view.StartMenuInternalFrame;

public class StartMenuInternalFrameController extends AbstractInternalFrameController {
    
    private StartMenuInternalFrame InternalFrameObject;
    
    // constructor
    public StartMenuInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject, 
                                                        StartMenuInternalFrame InternalFrameObject){
        super(mainContainerFrameControllerObject);
        this.InternalFrameObject = InternalFrameObject;
        InternalFrameObject.setStartMenuInternalFrameControllerObject(this);
    }
    
    public void informAboutUnavailability(){
        StaticShower.showMessage("Sorry, unavailable in version 1." + System.lineSeparator() +
                                             "Wait for version 2!!!");
    }
}
