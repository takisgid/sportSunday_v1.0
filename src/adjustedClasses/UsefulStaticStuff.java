package adjustedClasses;

import interfaces.Nameable;
import java.util.Collection;

// Class which contains some useful (at least for me) static methods.
public class UsefulStaticStuff {
    
    // static method which compares  alphabetically the names  of two objects that
    // implement the Nameable interface ignoring case.
    public static int compareAlphabeticalOrder(Nameable nameableObject1,Nameable nameableObject2){
        return (nameableObject1.getName().compareToIgnoreCase(nameableObject2.getName()));
    }
    
    // static method which add or subtract two integers in relation to the value of the
    // given boolean variable "isForAdding".
    public static Integer addOrSubtract(Integer int1, Integer int2, boolean isForAdding){
        
        if (isForAdding)
            return (int1 + int2);
        else
            return (int1 - int2);
    }
    
    // static method which traverses the given Collection object and returns
    // the object at the given index.
    public static Object getElementAt(Collection collectionObject ,int index){
        
        int counter = 0;
        
        for (Object currentObject: collectionObject)
            if (counter++ == index)
                return currentObject;
        
        return null;
    }
}