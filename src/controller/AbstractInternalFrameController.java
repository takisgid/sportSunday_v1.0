
package controller;

import enums.InternalFrameName;
import java.io.Serializable;
import model.AmateurSports;

public abstract class AbstractInternalFrameController implements Serializable {
    
    protected MainContainerFrameController mainContainerFrameControllerObject;
    protected AmateurSports amateurSportsObject;
    protected CommonMethodsController commonMethodsControllerObject;
    
    // constructor
    public AbstractInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject,
                  AmateurSports amateurSportsObject,CommonMethodsController commonMethodsControllerObject){
        
        this.mainContainerFrameControllerObject = mainContainerFrameControllerObject;
        this.amateurSportsObject = amateurSportsObject;
        this.commonMethodsControllerObject = commonMethodsControllerObject;
    }
    
    public AbstractInternalFrameController(MainContainerFrameController mainContainerFrameControllerObject){
        
        this.mainContainerFrameControllerObject = mainContainerFrameControllerObject;
    }
    
    public void showInternalFrameObject(InternalFrameName internalFrameNameObject){
        this.mainContainerFrameControllerObject.showInternalFrameObject(internalFrameNameObject);
    }
}
