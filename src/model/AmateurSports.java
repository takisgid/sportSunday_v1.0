package model;

import enums.Sport;
import adjustedClasses.UniqueCodeGenerator;
import adjustedClasses.TreeSetWithGetIndex;
import java.io.Serializable;

// The central class of the program. 
// It has references to every data that should be peristantly written.
// So, it is enough for the serializer just to write the object of this class and magically
// all the objects that are refered from here will be written too.

// This class is like some registry in real world. This is the start to find any
// useful information.
public class AmateurSports implements Serializable {
   
    // Data Structures 
    // TreeSet of championships
    private TreeSetWithGetIndex<Championship> championshipTreeSet;
    // TreeSet that contains athletes of non colegiate sports
    private TreeSetWithGetIndex<Solist> solistsTreeSet;
    // TreeSet of teams
    private TreeSetWithGetIndex<Team> teamsTreeSet;
    
    // Object that produces unique codes
    private UniqueCodeGenerator uniqueCodeGeneratorObject;
    
    //Constructor
    public AmateurSports(){
        
        this.championshipTreeSet = new TreeSetWithGetIndex<>();
        this.solistsTreeSet = new TreeSetWithGetIndex<>();
        this.teamsTreeSet = new TreeSetWithGetIndex<>();
        
        this.uniqueCodeGeneratorObject = new UniqueCodeGenerator();
    }
    
    // Methods about championships
    public TreeSetWithGetIndex<Championship> getChampionshipTreeSet(){
        return championshipTreeSet;
    }
    public void addChampionship(Championship championshipObject){
        this.championshipTreeSet.add(championshipObject);
    }
    public Championship getChampionshipAt(int index){
        return this.championshipTreeSet.getElementAt(index);
    }
    public void removeChampionshipAt(int index){
        this.championshipTreeSet.removeElementAt(index);
    }
    public void createChampionship(boolean leagueFlag, boolean cupFlag,Sport sportObject, String name,
                                                          int numberOfCompetiorsBigO, String bigTrophyString,
                                                                                int minRoster,int maxRoster){
        Championship championshipObject = null;
        // Creation of League Object
        if (leagueFlag)
            championshipObject = new ChampionshipLeague(sportObject,name,numberOfCompetiorsBigO,bigTrophyString,
                                                                                             minRoster,maxRoster);
        // Creation of Cup Object
        else if (cupFlag){
            championshipObject = new ChampionshipCup (sportObject,name,numberOfCompetiorsBigO,bigTrophyString,
                                                                                            minRoster,maxRoster);
        }  
        // adding to the treeSet of championships
        addChampionship(championshipObject);
    }
    
    // Methods about solists
    public TreeSetWithGetIndex<Solist> getSolistsTreeSet(){
        return solistsTreeSet;
    }
    public void addSolist(Solist solistObject){
        this.solistsTreeSet.add(solistObject);
    }
    public void removeSolist(String toStringRepresantationString){
        this.solistsTreeSet.removeElementByToStringRepresantation(toStringRepresantationString);
    }
    public Solist getSolist(int index){
        return this.solistsTreeSet.getElementAt(index);
    }
    public Solist getSolist(String toStringRepresantationString){
        return this.solistsTreeSet.getElementByToStringRepresantation(toStringRepresantationString);
    }
    public void createSolist(String lastName,String firstName,Sport sport){
        
        addSolist(new Solist(uniqueCodeGeneratorObject, lastName,firstName,sport)); 
    }
    public void createSolist(int solistId, String lastName,String firstName,Sport sport){
        
        addSolist(new Solist(solistId, lastName,firstName,sport));
    }
    
    // Methods about teams
    public TreeSetWithGetIndex<Team> getTeamsTreeSet(){
        return teamsTreeSet;
    }
    public void addTeam(Team teamObject){
        this.teamsTreeSet.add(teamObject);
    }
    public void removeTeam(Team targetTeam){
        this.teamsTreeSet.removeElementByMirrorObject(targetTeam);
    }
    public Team getTeam(int index){
        return this.teamsTreeSet.getElementAt(index);
    }
    public Team getTeam(Team targetTeam){
        return this.teamsTreeSet.getElementByMirrorObject(targetTeam);
    }
    public void createTeam( String name, Sport sport){
        
        addTeam(new Team(name,sport)); 
    }
    public void createTeam(String name,Sport sport, TreeSetWithGetIndex<Player> rosterTreeSetWithGetIndex){
        
        addTeam(new Team(name,sport,rosterTreeSetWithGetIndex)); 
    }
    
    // Methods for producing unique code 
    public int produceNewUniqueCode(){
        return this.uniqueCodeGeneratorObject.produceNewUniqueCode();
    }
    public UniqueCodeGenerator getUniqueCodeGeneratorObject(){
        return this.uniqueCodeGeneratorObject;
    }
}
