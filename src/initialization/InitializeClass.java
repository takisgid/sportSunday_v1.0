
package initialization;

import model.ChampionshipLeague;
import model.AmateurSports;
import model.ChampionshipCup;
import model.Solist;
import enums.Sport;
import model.Team;
import adjustedClasses.UniqueCodeGenerator;
import adjustedClasses.StaticShower;

// Class that contains static methods for executing some initialization of
// competitors,players and championships in order that the user could test 
// the features of the program.
// I hope he will be impresed so as he 'll get me the job.

public class InitializeClass {
    
    // Central method of initializing. It calls more specialized methods
    public static void  InitializeAmateursports(AmateurSports amateurSportsObject){
        
        InitializeCompetitors(amateurSportsObject);
        InitializePlayers(amateurSportsObject);
        InitializeChampionships(amateurSportsObject);
        
        StaticShower.showInitializationMessage();
        System.out.println("Initialization done");
    }
    
    // Just what its name reveals
    public static void  InitializeCompetitors(AmateurSports amateurSportsObject){
        
        amateurSportsObject.addTeam(new Team("Chicago Bulls",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Phoenix Suns",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Los Angeles Lakers",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Seatle Supersonics",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("New York Knicks",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Orlado Magic",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Real Madrid",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Limoz",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Makampi Tel Aviv",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Benetton Trevizo",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Iraklis",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Sporting",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Apollon Patrwn",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Houston Rockets",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Boston Celtics",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Golden State Warriors",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Milwakee Bucks",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Detroi Pistons",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Paok",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Kilkisiakos",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Panachaiki",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Panathinaikos",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Paniliakos",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Doxa Dramas",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Ialisos Rodou",Sport.FOOTBALL));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Sampras","Pete",Sport.TENNIS));
        amateurSportsObject.addTeam(new Team("Aek",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Paok",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Iraklis",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Olimpiakos",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Panathinaikos",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Panellinios",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Aek",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Gas Kilkis",Sport.VOLLEYBALL));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                            "Mitsou","Theofilaktos",Sport.PINGPONG));
        amateurSportsObject.addTeam(new Team("Paok",Sport.BASKETBALL));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Agassi","Andre",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                            "Papagiannis","Thanasis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                            "Giorgadakis","Georgios",Sport.PINGPONG));
        amateurSportsObject.addTeam(new Team("Olympiakos",Sport.FOOTBALL));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Woods","Tiger",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Lendl","Ivan",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Edberg","Stefan",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Becker","Borris",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                      "McEnroe","John",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Rafter","Patrick",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Kuerten","Gustavo",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                         "Korda","Petr",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                          "Henman","Tim",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Federer","Roger",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                         "Nadal","Rafael",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Djokovic","Novak",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                           "Murray","Andy",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                       "Soderling","Robin",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                             "Oikonomidis","Konstantinos",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Tsitsipas","Stefanos",Sport.TENNIS));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Seagal","Steven",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Maradona","Diego",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                             "Pitt","Brad",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Einstein","Albert",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Papikos","Ioannis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Papikos","Ioannis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Papikos","Ioannis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Tsalioumadakis","Sifis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                 "Xatzipapapetrou","Sotiris",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                            "Handman","John",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                    "Soriropoulos","Valantis",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                        "Sougias","Xaralampos",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                     "Spiridoulidis","Nikolaos",Sport.PINGPONG));
        amateurSportsObject.addSolist(new Solist(amateurSportsObject.getUniqueCodeGeneratorObject(),
                                                                                  "Right","Ian",Sport.PINGPONG));
        amateurSportsObject.addTeam(new Team("Panathinaikos",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Aek",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Atromitos",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Aris",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Olympiakos",Sport.BASKETBALL));
        amateurSportsObject.addTeam(new Team("Aris",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Orestiada",Sport.VOLLEYBALL));
        amateurSportsObject.addTeam(new Team("Aris",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Panionios",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Iraklis",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Asteras Tripolis",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Pas Giannina",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Larisa",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Ksanthi",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Ofi",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Levadiakos",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Apollwn Smirnis",Sport.FOOTBALL));
        amateurSportsObject.addTeam(new Team("Panaitolikos",Sport.FOOTBALL));
    } 
    
    // Just what its name reveals
    public static void  InitializePlayers(AmateurSports amateurSportsObject){
        
        UniqueCodeGenerator uniqueCodeGeneratorObject = amateurSportsObject.getUniqueCodeGeneratorObject();
        
        Team currentTeam = amateurSportsObject.getTeam(new Team("Aek", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Patavoukas", "Kostas", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Galakteros", "Nasos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"White", "Tony", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"King", "Steven", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Gkekos", "Mhnas", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vik", "Alexander", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Chatzis", "Nikos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Zisis", "Nikos", Sport.BASKETBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Aris", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Galis", "Nikos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Giannakis", "Panagiotis", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Aggelidis", "Ntinos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Lupiridis", "Mixalis", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Iwannou", "Memos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Anderson", "Jei-Jei", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Tarpley", "Roy", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Misounov", "Mikael", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Gasparis", "Giorgos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vourtzoumis", "Vaggelis", Sport.BASKETBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Chicago Bulls", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Jordan", "Michael", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Pippen", "Scottie", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Rondman", "Dennis", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Longley", "Luke", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kukoc", "Tony", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Harper", "Ron", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kerr", "Steve", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Wennigton", "Bill", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Haley", "Jack", Sport.BASKETBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Panathinaikos", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Wilkins", "Dominique", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Alvertis", "Fragkiskos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Oikonomou", "Nikos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vrankovic", "Stojan", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Myriounis", "Christos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Koch", "Mickael", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Radja", "Dino", Sport.BASKETBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Olympiakos", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Tomic", "Milan", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Tarlac", "Dragan", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Paspali", "Zarko", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kampouris", "Argyrhs", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Sigalas", "Giorgos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Berry", "Walter", Sport.BASKETBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Paok", Sport.BASKETBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Fasoulas", "Panagiotis", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Levingston", "Cliff", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Barlow", "Ken", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Korfas", "John", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Tsekos", "Xristos", Sport.BASKETBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mpountouris", "Tasos", Sport.BASKETBALL);

        currentTeam = amateurSportsObject.getTeam(new Team("Aek", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Livaja", "Marko", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Simoes", "Andre", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Christodoulopoulos", "Lazaros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Galanopoulos", "Kostas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mpakakis", "Mixalis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Araujo", "Sergio", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Cosic", "Uros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Chygrynskiy", "Dmytro", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mantalos", "Petros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mparkas", "Vasileios", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Masoud", "Sodjaei", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Atromitos", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Warda", "Amre", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kivrakidis", "Kuriakos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Umbidez", "Havier", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vasilakakis", "Theodoros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Risvanis", "Spiros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mantson", "Enrike", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Gianniotis", "Andreas", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Olympiakos", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mirallas", "Kevin", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Fortounis", "Kostas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Marin", "Marko", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Proto", "Silvio", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Odjidja Ofoe", "Vandis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Romao", "Aleksi", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Cisse", "Pap Abou", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Ansarifard", "Karim", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Koutris", "Leonardo", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Panathinaikos", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Dioudis", "Socratis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kourmpelis", "Dimitris", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kulibali", "Usman", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kolovetsios", "Dimitris", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mounie", "Antoni", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Chatzigiovanis", "Anastasios", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Oikonomou", "Vangelis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Pispas", "Sotiris", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Paok", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Matos", "Leo", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vieirinha", "Adelino", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mak", "Robert", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Varela", "Fernando", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Prijovic", "Aleksandar", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Pelkas", "Dimitris", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Pasxalakis", "Aleksandros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Kampos", "Djalma", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Limnios", "Stelios", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mauricio", "Jose", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Aris", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Liolidis", "Apostolos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Karkamanis", "Christos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Loumpoutis", "Kostas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Charisteas", "Aggelos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Lontsar", "Zoran", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Ivan", "Santos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Koke", "Sergio", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Panionios", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mantzios", "Akis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Tziolis", "Aleksandros", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mantzios", "Vaggelis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Estoyanoff", "Fabian", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Recoba", "Alvaro", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Sapountzis", "Antonis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Nalitzis", "Dimitrios", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Maniatis", "Kostas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Siopis", "Manolis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Vokolos", "Leonidas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Strakosia", "Fotis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Fyssas", "Takis", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Iraklis", Sport.FOOTBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Amparis", "Kostas", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Katsampis", "Tasos", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Sempoue", "Kelvin", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Konstantinou", "Mixalis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Lagos", "Panagiotis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Hagan", "Ebenizer", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Pardo", "Hugo", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Epale", "Joel", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Markez", "Fernado", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mietsel", "Martsin", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Epstain", "Denis", Sport.FOOTBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Die", "Serze", Sport.FOOTBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Aris", Sport.VOLLEYBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Fwteinos", "Theofylaktos", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Skoteinos", "Anaksimandros", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Fwtakelis", "Stauros", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Lapthras", "Sevos", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Papagiannhs", "Takis", Sport.VOLLEYBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Orestiada", Sport.VOLLEYBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Gkinhs", "Swthrios", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Peristeropoulos", "Eukleidis", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Giorgos", "Papadopoulos", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Giorgos", "Papadopoulos", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Giorgos", "Papadopoulos", Sport.VOLLEYBALL);
        
        currentTeam = amateurSportsObject.getTeam(new Team("Paok", Sport.VOLLEYBALL));
        
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Sotou", "Sotis", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Sotou", "Sotis", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mpekios", "Parmeniwn", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Karpathios", "Telis", Sport.VOLLEYBALL);
        currentTeam.createPlayer(uniqueCodeGeneratorObject,"Mpekouras", "Ioannis", Sport.VOLLEYBALL);
    }
    
    // Just what its name reveals
    public static void  InitializeChampionships(AmateurSports amateurSportsObject){
        
        amateurSportsObject.addChampionship(new ChampionshipLeague
                             (Sport.TENNIS,"Winners League",16,"2000€",1,1));
        amateurSportsObject.addChampionship(new ChampionshipLeague
                             (Sport.PINGPONG,"Workers Ping Pong Battle",8,"Seven day trip to Everest",1,1));
        amateurSportsObject.addChampionship(new ChampionshipLeague
                              (Sport.PINGPONG,"Nerds League",4,"Just the excitement of sports",1,1));
        amateurSportsObject.addChampionship(new ChampionshipCup
                              (Sport.TENNIS,"Toughest Grass Tennis Cup",16,"Ski equipment",1,1));
        
        ChampionshipLeague tempChampionshipLeagueObject = new ChampionshipLeague
                                (Sport.FOOTBALL,"Premier Amateur League",18,"Best Sport Equipment",11,18);
        amateurSportsObject.addChampionship(tempChampionshipLeagueObject);
        
        tempChampionshipLeagueObject = new ChampionshipLeague
                                (Sport.FOOTBALL,"Super league Yoko Choco",16,"Authentic gold mendals",7,10);
        amateurSportsObject.addChampionship(tempChampionshipLeagueObject);
        
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Aek", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                       (new Team("Atromitos", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                      (new Team("Olympiakos", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Paok", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                    (new Team("Panathinaikos", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                             (new Team("Aris", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                        (new Team("Panionios", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                          (new Team("Iraklis", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                 (new Team("Asteras Tripolis", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                      (new Team("Pas Giannina", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Larisa", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Ksanthi", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Ofi", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                         (new Team("Levadiakos", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                    (new Team("Apollwn Smirnis", Sport.FOOTBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                        (new Team("Panaitolikos", Sport.FOOTBALL)));
        
        tempChampionshipLeagueObject = new ChampionshipLeague
                          (Sport.BASKETBALL,"A1 Topino",8,"Three day Vacation at Mykonos",5,10);
        amateurSportsObject.addChampionship(tempChampionshipLeagueObject);
        
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Aek", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Aris", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Paok", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                        (new Team("Panathinaikos", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Olympiakos", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Iraklis", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Limoz", Sport.BASKETBALL)));
        tempChampionshipLeagueObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Apollon Patrwn", Sport.BASKETBALL)));
        
        
        ChampionshipCup tempChampionshipCupObject = new ChampionshipCup
                                (Sport.FOOTBALL,"Glory Cup",16,"Just Love",8,11);
        amateurSportsObject.addChampionship(tempChampionshipCupObject);
        
        
        tempChampionshipCupObject = new ChampionshipCup
                                (Sport.FOOTBALL,"Football Bloody Hell",8,"Seven honda motorbikes",5,7);
        amateurSportsObject.addChampionship(tempChampionshipCupObject);
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Aek", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Paok", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Olympiakos", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Panathinaikos", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Atromitos", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Aris", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Panionios", Sport.FOOTBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Iraklis", Sport.FOOTBALL)));
        
        
        tempChampionshipCupObject = new ChampionshipCup(Sport.BASKETBALL,"Magic tournament",16,"1500€",3,3);
        amateurSportsObject.addChampionship(tempChampionshipCupObject);
        
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Aek", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Aris", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Paok", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                             (new Team("Panathinaikos", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                  (new Team("Olympiakos", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                      (new Team("Iraklis", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                         (new Team("Limoz", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Apollon Patrwn", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Boston Celtics", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Chicago Bulls", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                            (new Team("Golden State Warriors", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Houston Rockets", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                   (new Team("Los Angeles Lakers", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                    (new Team("Milwakee Bucks", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("New York Knicks", Sport.BASKETBALL)));
        tempChampionshipCupObject.addCompetitor(amateurSportsObject.getTeam
                                                (new Team("Orlado Magic", Sport.BASKETBALL)));
    }
    
    // future move
    public static void  InitializeChampionshipTeamsIn(AmateurSports amateurSportsObject){
        
    }
    
    // future move
    public static void  InitializeChampionshipCards(AmateurSports amateurSportsObject){
        
    }
}