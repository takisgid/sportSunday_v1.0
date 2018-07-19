
package model;

import enums.Sport;
import adjustedClasses.UniqueCodeGenerator;

// modelizes an athlete of a non colegiate sport
public class Solist extends Athlete {
    
    // constructors
    public Solist(UniqueCodeGenerator uniqueCodeGeneratorObject, String lastName,String firstName,Sport sport) {
        super(uniqueCodeGeneratorObject,lastName,firstName, sport);
    }
    public Solist(int solistID, String lastName,String firstName,Sport sport) {
        super(solistID,lastName,firstName, sport); 
    }
}
