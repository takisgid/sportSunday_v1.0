
package model;

import enums.Sport;
import adjustedClasses.UniqueCodeGenerator;

// abstraction that includes solists and players
public class Athlete extends Competitor {
    
    int athleteId; // necessary for discerning athletes with same first and last name.
    String lastName;
    String firstName;
    
    // constructors
    public Athlete(UniqueCodeGenerator uniqueCodeGeneratorObject, String lastName,String firstName,Sport sport) {
        super(sport);
        this.athleteId = uniqueCodeGeneratorObject.produceNewUniqueCode();
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public Athlete(int athleteId, String lastName,String firstName,Sport sport) {
        super(sport);
        this.athleteId = athleteId;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    
    // Getters
    @Override
    public String getName() { // required method of Nameable interface
        return getLastName();
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public Sport getSport() {
        return this.sport;
    }
    public Integer getAthleteID() {
        return this.athleteId;
    }
    
    @Override
    public String toString(){
        return getName() + " " + getFirstName() + " (ID: " + getAthleteID().toString() + ")";
    }
    
    // Sorting alphabetically (ignoring case):
    // --- 1st criterion: Last name
    // --- 2nd criterion: First name
    // --- 3rd criterion: Id number
    @Override
    public int compareTo(Object otherObject){
        
        // compareTo based on LastNames
        int compareLastNameInteger = super.compareTo(otherObject);
        // If their LastNames are not equal
        if (compareLastNameInteger != 0)
            return compareLastNameInteger;
        
        Athlete otherAthlete = (Athlete) otherObject;
        // compareTo() based on FirstNames
        int compareFirstNameInteger = this.firstName.compareToIgnoreCase(otherAthlete.firstName);
        // If their LastNames are equal and FirstNames not equal compare their first names
        if (compareFirstNameInteger != 0)
            return compareFirstNameInteger;
        
        //compareTo() based on athleteId
        return ((Integer)this.athleteId).compareTo((Integer)otherAthlete.athleteId);
    }
}

