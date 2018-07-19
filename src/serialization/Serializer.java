
package serialization;

import adjustedClasses.StaticShower;
import controller.MainContainerFrameController;
import model.AmateurSports;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainContainerFrame;

// About serialization (From www.tutorialspoint.com)

// Java provides a mechanism, called object serialization where an object can be
// represented as a sequence of bytes that includes the object's data as well as
// information about the object's type and the types of data stored in the object.
// After a serialized object has been written into a file, it can be read from 
// the file and deserialized that is, the type information and bytes that represent
// the object and its data can be used to recreate the object in memory.

// I chose for having persistent storage of the data, the serialization mechanism
// over some database because:
// --- data of this program are not too complex and serialization is more efficient
//     than some database, at this case.
// --- i had already done some projects with database and using serialization 
//     sounded elegant and intriguing.

// Exactly one object of Serializer class is produced. It does all the saving and
// all the loading.

// For using this you should first manualy create an empty folder
// with the name "serializationFolder" at C:\
// Every time that program begins, it will load your data from
// "greekSportsObject.ser" file which is in folder: C:\serializationFolder
// If this file does not exist, then the program will be empty from data.
// So if you want to clear everything, just delete manually the "greekSportsObject.ser"
// file before you start the program.
// I hope that from the next version, the "clear everything"
// will be less primitive.
public class Serializer implements Serializable  {
    
    MainContainerFrameController MainContainerFrameControllerObject;
    
    public Serializer (MainContainerFrameController MainContainerFrameControllerObject){
        this.MainContainerFrameControllerObject = MainContainerFrameControllerObject;
    }
    
    // method for writing data
    public void serialize(){
        
        FileOutputStream fileOutputStreamObject = null;
        ObjectOutputStream objectOutputStreamObject = null; 
        
        try {
            File path = new File("C:\\serializationFolder");
            File fileObject = new File(path,"greekSportsObject.ser");
        
            fileOutputStreamObject = new FileOutputStream(fileObject);
            objectOutputStreamObject = new ObjectOutputStream(fileOutputStreamObject);
            
            objectOutputStreamObject.writeObject(this.MainContainerFrameControllerObject.getAmateurSportsObject());
            StaticShower.showSavedMessage();
            System.out.println("Serialization done!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                objectOutputStreamObject.close();
                fileOutputStreamObject.close();
            } catch (IOException ex) {
                Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // method for reading data
    public void deserialize(){
        
        FileInputStream fileInputStreamObject = null;
        ObjectInputStream objectInputStreamObject = null; 
        
        File path = new File("C:\\serializationFolder");
        File fileObject = new File(path,"greekSportsObject.ser");
            
        if (fileObject.exists()){
            try {
                fileInputStreamObject = new FileInputStream(fileObject);
                objectInputStreamObject = new ObjectInputStream(fileInputStreamObject);
                this.MainContainerFrameControllerObject.setAmateurSportsObject
                                                            ((AmateurSports)objectInputStreamObject.readObject());
                System.out.println("Deserialization done");
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MainContainerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
