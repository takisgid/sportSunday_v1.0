
package model;

import enums.Sport;
import adjustedClasses.UniqueCodeGenerator;
import adjustedClasses.TreeSetWithGetIndex;

public class Team extends Competitor {
    
    private String name;
    // this treeset contains all the players that belong to team's roster.
    private TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex;
    // number of players that belong to this team's roster
    private int numberOfPlayers;
    
    // constructors
    public Team(String name,Sport sport){
        super(sport);
        this.name = name;
        this.rosterTreeSetWithGetIndex = new TreeSetWithGetIndex<>();
        this.numberOfPlayers = 0;
    }
    public Team(String name,Sport sport, TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex){
        super(sport);
        this.name = name;
        this.rosterTreeSetWithGetIndex = rosterTreeSetWithGetIndex;
        this.numberOfPlayers = this.rosterTreeSetWithGetIndex.size();
    }
    
    // Getters
    public String getName(){ // required method of Nameable interface
        return this.name;
    }
    public Integer getNumberOfPlayers(){
        return this.numberOfPlayers;
    }
    public TreeSetWithGetIndex<Player> getRosterTreeSetWithGetIndex(){
        return this.rosterTreeSetWithGetIndex;
    }
    
    @Override
    public String toString(){
        return getName();
    }
    
    // Sorting alphabetically (ignoring case):
    // --- 1st criterion: Teams name
    // --- 2nd criterion: Sport toString() represantation
    @Override
    public int compareTo(Object otherObject){
        
        int compareInteger = super.compareTo(otherObject);
        
        Team otherTeam = (Team) otherObject;
        
        if (compareInteger == 0)
            return (this.sport.toString().compareToIgnoreCase(otherTeam.sport.toString()));
        
        return compareInteger;
    }
    
    // Methods about roster
    public TreeSetWithGetIndex<Player> getPlayerTreeSet(){
        return rosterTreeSetWithGetIndex;
    }
    public void addPlayer(Player playerObject){
        rosterTreeSetWithGetIndex.add(playerObject);
        ++this.numberOfPlayers;
    }
    public Player getPlayerAt(int index){
        return rosterTreeSetWithGetIndex.getElementAt(index);
    }
    public Player getPlayer(String toStringRepresantationString){
        return rosterTreeSetWithGetIndex.getElementByToStringRepresantation(toStringRepresantationString);
    }
    public void removePlayerAt(int index){
        rosterTreeSetWithGetIndex.removeElementAt(index);
        --this.numberOfPlayers;
    }
    public void createPlayer(UniqueCodeGenerator uniqueCodeGeneratorObject, String lastName,String firstName,Sport sport){
        addPlayer(new Player(uniqueCodeGeneratorObject, lastName, firstName, sport));
    }
    public void createPlayer(int playerIdInt, String lastName,String firstName,Sport sport){
        addPlayer(new Player(playerIdInt, lastName, firstName, sport));
    } 
}
