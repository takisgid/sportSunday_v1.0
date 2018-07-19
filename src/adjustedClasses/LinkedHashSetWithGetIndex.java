
package adjustedClasses;

import java.io.Serializable;
import java.util.LinkedHashSet;

// Adjusted LinkedHashSet so as a getter by index has been added.

public class LinkedHashSetWithGetIndex <T> extends LinkedHashSet<T> implements Serializable{
    
    // just a sexy getter.
    public T getElementAt(int index){
        
        Object currentObject = UsefulStaticStuff.getElementAt(this, index);
        
        if (currentObject == null)
            return null;
       
        return (T)currentObject;
    }
}
