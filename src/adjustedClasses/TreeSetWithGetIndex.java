
package adjustedClasses;

import java.io.Serializable;
import java.util.TreeSet;

// Adjusted TreeSet so as:
// --- a getter by index has been added.
// --- a method which returns the index of given object(if exists) has been added.
// --- a method which returns the object that is described (toString())
//        by the given String. has been added.
// --- a method which removes element at the given index has been added.
// --- a getter by index has been added.

// Needed for functionality of GUI.
public class TreeSetWithGetIndex<T> extends TreeSet<T> implements Serializable{
    
    // sexy getter by index.
    public T getElementAt(int index){
        
        Object currentObject = UsefulStaticStuff.getElementAt(this, index);
        
        if (currentObject == null)
            return null;
       
        return (T)currentObject;
    }
    
    // Well, no need for this because contains(Object) method already exists.
    // I will erase it.
    public T getElementByMirrorObject(T targetObject){
        
        for (T currentTObject: this)
            if (currentTObject.equals(targetObject))
                return currentTObject;
            
        return null;
    }
    
    // Method which returns the index of given object(if exists in TreeSet)
    public int getIndexByMirrorObject(T targetObject){
        
        int counter = 0;
        
        for (T currentTObject: this){
            if (currentTObject.equals(targetObject))
                return counter;
            counter++;
        }
        return -1;
    }
    
    // Method which returns the object that is described (from toString()) by the given String.
    public T getElementByToStringRepresantation(String toStringRepresantationString){
        
        for (T currentTObject: this)
            if (currentTObject.toString().equals(toStringRepresantationString))
                return currentTObject;
            
        return null;
    }
    
    // sexy removal. Method which removes element at the given index.
    public void removeElementAt(int index){
        this.remove(getElementAt(index));
    }
    
    // Well, no need for this because remove(Object) method already exists.
    // I will erase it.
    public void removeElementByMirrorObject(T targetObject){
        this.remove(getElementByMirrorObject(targetObject));
    }
    
    // Method which removes the object that is described (from toString()) by the given String.
    public void removeElementByToStringRepresantation(String toStringRepresantationString){  
        this.remove(getElementByToStringRepresantation(toStringRepresantationString));  
    }
}
