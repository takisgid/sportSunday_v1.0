
package pseudoMain;

import controller.MainContainerFrameController;
import initialization.InitializeClass;
import model.AmateurSports;
import serialization.Serializer;
import view.MainContainerFrame;

public class PseudoMainClass {
    
    // Static method which being called by the real main method of MainControllerFrame object.
    // It produces the "central" object of the program (amateurSportsObject),
    // the Serializer object and calls the proper methods for creating and 
    // initializing the GUI and the main domain objects of the program.
    
    public static void pseudoMain(MainContainerFrame mainContainerFrameObject){
                
        AmateurSports amateurSportsObject = new AmateurSports();
        
        // initializing
        //InitializeClass.InitializeAmateursports(amateurSportsObject);
        MainContainerFrameController MainContainerFrameControllerObject = new MainContainerFrameController
                                                                                          (amateurSportsObject);
        //serializing - deserializing
        Serializer serializerObject = new Serializer(MainContainerFrameControllerObject);
        
        //serializator.serialize();
        serializerObject.deserialize();
       
        MainContainerFrameControllerObject.setMainContainerFrameObject(mainContainerFrameObject);
        MainContainerFrameControllerObject.setSerializatorObject(serializerObject);
                
        MainContainerFrameControllerObject.createInternalFramesAndControllers();
        MainContainerFrameControllerObject.initializeInternalFrames();
    }
    
    
    
    
    
}
