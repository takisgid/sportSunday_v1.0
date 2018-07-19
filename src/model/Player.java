
package model;

import enums.Sport;
import adjustedClasses.UniqueCodeGenerator;

// modelizes an athlete of a colegiate sport
public class Player extends Athlete {
    
    // constructors
    public Player(UniqueCodeGenerator uniqueCodeGeneratorObject, String lastName,String firstName,Sport sport) {
        super(uniqueCodeGeneratorObject,lastName,firstName, sport);
    }
    public Player(int playerIdInt, String lastName,String firstName,Sport sport) {
        super(playerIdInt,lastName,firstName, sport);
    }
}

