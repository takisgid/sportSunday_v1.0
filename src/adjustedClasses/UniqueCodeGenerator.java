
package adjustedClasses;

import java.io.Serializable;

// Exactly one object of UniqueCodeGenerator class is produced.
// Like a tireless worker, when is called supplies a unique code
// needed for characterizing uniquely every Athlete object.
public class UniqueCodeGenerator implements Serializable {
    
    int currentCode;
    
    public UniqueCodeGenerator(){
        this.currentCode = 1;
    }
    
    public int produceNewUniqueCode(){
        return currentCode++;
    }
}
