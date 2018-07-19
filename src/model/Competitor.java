
package model;

import enums.Sport;
import java.io.Serializable;
import interfaces.Nameable;
import adjustedClasses.TreeSetWithGetIndex;
import adjustedClasses.UsefulStaticStuff;

// The meaning of the class refers to teams or athletes that participate
// in a championship. Specifically, this class is an abstraction of the
// above entities.
public abstract class Competitor implements Comparable,Nameable,Serializable {
    
    // It is considered that every competitor is related to a specific sport.
    // If a person or a team is related to two or more sports then the user
    // should register him for every sport distinctly.
    Sport sport;
    
    // Treeset which includes the Championships in which this competitorObject 
    // participates
    TreeSetWithGetIndex<Championship> championshipTreeSet;
    
    //Constructor
    public Competitor(Sport sport){
        this.sport = sport;
        championshipTreeSet = new TreeSetWithGetIndex<>();
    }
    
    public Sport getSport(){
        return this.sport;
    }
    
    public void addChampionship(Championship championshipObject){
        championshipTreeSet.add(championshipObject);
    }
    public void removeChampionship(Championship championshipObject){
        championshipTreeSet.remove(championshipObject);
    }
    
    // sorting is based on alphabetically order (ignoring case) according the String that is 
    // returned by the getName() method
    @Override
    public int compareTo(Object otherObject){
        
        Competitor otherCompetitor = (Competitor) otherObject;
        
        return (UsefulStaticStuff.compareAlphabeticalOrder(this, otherCompetitor));
    }
    
    // i have to see that againa.
    // it compares memory adresses. Not the best way.
    @Override
    public boolean equals (Object otherObject){
        if (this.compareTo(otherObject) == 0) 
            return true;
        return false;
    }
}
