
package lotteryClasses;

import adjustedClasses.LinkedHashSetWithGetIndex;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import model.Championship;
import model.ChampionshipCup;
import model.ChampionshipLeague;

// This class modelizes a raffle box.
// Each lottery ball represents a possible game.
public class LotteryBallsHashSet extends LinkedHashSetWithGetIndex<CoupleOfCompetitorsIndexLotteryBall> 
                                                                                implements Serializable {
    
    private Championship championshipObject; 
    private int numberOfCompetitors;
   
    private int numberOfGamesPerSportDay;
    private int numberOfSportDays;
    
    // constructor
    public LotteryBallsHashSet(Championship championshipObject){
        
        this.championshipObject = championshipObject;
        
        // if the lottery is about a league
        if (championshipObject instanceof ChampionshipLeague){
            this.numberOfCompetitors = championshipObject.getNumberOfCompetitors();
            this.numberOfSportDays = this.numberOfCompetitors -1;  
        }
        // if the lottery is about a phase of a cup
        else{
            this.numberOfCompetitors = ((ChampionshipCup)championshipObject).getNumberOfActiveCompetitors();
            this.numberOfSportDays =-1;  
        }
        this.numberOfGamesPerSportDay = this.numberOfCompetitors /2;
        //createLotteryBalls();
    }
    
    // method that fills the raffle box with all the possible games (as lottery balls)
    public void createLotteryBalls(){
        
        // empty the LotteryBallsHashSet
        this.clear();
        
        // creates all the possible combinations of indexes
        for (int homeIndex=0; homeIndex<numberOfCompetitors; homeIndex++)
            for (int awayIndex=homeIndex + 1; awayIndex<numberOfCompetitors; awayIndex++)
                this.add(new CoupleOfCompetitorsIndexLotteryBall(homeIndex,awayIndex)); 
    }
    
    // getter which returns the suitable lotteryBall according to the given
    // homeIndex and awayIndex values.
    public CoupleOfCompetitorsIndexLotteryBall getCoupleOfCompetitorsIndexLotteryBall
                                                                    (int homeIndex, int awayIndex){
        for (CoupleOfCompetitorsIndexLotteryBall currentCoupleOfCompetitorsIndexLotteryBall: this)
            if (currentCoupleOfCompetitorsIndexLotteryBall.getHomeTeamIndex() == homeIndex &&
                    currentCoupleOfCompetitorsIndexLotteryBall.getAwayTeamIndex() == awayIndex)
                return currentCoupleOfCompetitorsIndexLotteryBall;
        
        return null;
    }
    
    // checks if there is at least one available lottery ball
    public boolean existsAvailableBall(){
        for (CoupleOfCompetitorsIndexLotteryBall currentBall:this)
            if (currentBall.getIsAvailable())
                return true;
        
        return false;
    }
    
    // method that makes the lottery for league type of championship.
    // One lottery is needed for whole league schedule.
    // When trying to accomplish a lottery for some league method could fail.
    // That's why this method is boolean so as that it could be called many times 
    // until it succeeds.
    public boolean makeLotteryForLeague(){
        
        // counts the times that the lottery of current sportday has been reseted
        int backTrackCounter=0;
        
        // size of linkedHashSetThatContainsAllAvailableLotteryBallsObject
        int sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject;
        
        // a lottery ball
        CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject;
        
        //indexes
        int homeIndex;
        int awayIndex;
        
        // list that contains the lottery balls (that modelize the games) that have been
        // chosen for current sportDay
        ArrayList<CoupleOfCompetitorsIndexLotteryBall> listOfChosenBallsForCurrentSportDay = 
                                                                                new ArrayList<>();
        // linkedHashSet that contains all available LotteryBalls at the moment
        LinkedHashSetWithGetIndex<Integer> linkedHashSetThatContainsAllAvailableLotteryBallsObject =
                                                                    new LinkedHashSetWithGetIndex<>();
        
        // this for traverses all the game weeks one by one
        for(int counterOfSportDays =0;counterOfSportDays<this.numberOfSportDays;counterOfSportDays++){
            
            // creates a new sportday
            this.championshipObject.addSportDay();
            
            // this "for" traverses all the LotteryBallsHashSet objects and sets every lottery ball
            // as available
            for(CoupleOfCompetitorsIndexLotteryBall initCoupleOfCompetitorsIndexLotteryBallObject: this)
                                initCoupleOfCompetitorsIndexLotteryBallObject.setItOn();
            
            // the list of chosen lottery Balls (that modelize games) for this sportday
            // is initialized as empty
            listOfChosenBallsForCurrentSportDay.clear();
            
            // this "for" traverses all the games indexes of a sportDay one by one
            for (int counterOfGamesForCurrentSportDay =0;
                    counterOfGamesForCurrentSportDay<this.numberOfGamesPerSportDay;
                                                        counterOfGamesForCurrentSportDay++){
                
                // First of all, a check that the lottery hasn't been blocked is necessary
                // Sometimes, the choices of the first games of a sportDay causes a deadlock
                // So the method tries (limit of attempts: 100 times) to backtrack a sportday back, if needed.
                // If the limit has been reached, the method concludes that the problem is too big
                // and therefore it restarts the lottery from the scratch.
                
                // if there is no available lottery ball
                if (!existsAvailableBall()){
                    
                    // if it is the 101st backtrack action in the row
                    if (backTrackCounter >100){
                        // provoke the lottery procedure to be cancelled
                        // and to be restarted from the scratch
                        
                        this.championshipObject.clearSportDay();
                        
                        //System.out.println("return false");
                        
                        // this attempt failed
                        return false;
                    }
                    
                    // move forward for backtrack action
                    
                    // increase backtrack counter by one
                    ++backTrackCounter;
                    
                    // erase all games of current sportday
                    this.championshipObject.removeGamesOfSportDay(counterOfSportDays);
                    
                    // add (restore) all previously chosen  lottery balls to LotteryBallsHashSet
                    this.addAll(listOfChosenBallsForCurrentSportDay);
                    
                    // clear list of chosen balls for current sportday
                    listOfChosenBallsForCurrentSportDay.clear();
                    
                    // reset counter of games
                    counterOfGamesForCurrentSportDay =0;
                    
                    // reset availability to "on" for every lottery ball of the LotteryBallsHashSet
                    for(CoupleOfCompetitorsIndexLotteryBall initCoupleOfCompetitorsIndexLotteryBallObject: this)
                                initCoupleOfCompetitorsIndexLotteryBallObject.setItOn();
                }
                
                // clear the linkedHashSetWithGetIndexObject 
                linkedHashSetThatContainsAllAvailableLotteryBallsObject.clear();
                     
                // initialization of sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject
                sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject=0;
                        
                 // this "for" traverses all the lottery balls of the raffle box
                for(CoupleOfCompetitorsIndexLotteryBall currentBall:this){
                    
                    // add to linkedHashSetThatContainsAllAvailableLotteryBallsObject
                    // all the available at this moment lottery balls
                    if(currentBall.getIsAvailable())
                        linkedHashSetThatContainsAllAvailableLotteryBallsObject.
                                            add(sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject);
                    
                    // update size
                    ++sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject;
                }
                        
                // create a generator of random numbers
                Random randomGenerator = new Random();
                         
                // choose randomly a lottery ball from linkedHashSetThatContainsAllAvailableLotteryBallsObject
                coupleOfCompetitorsIndexLotteryBallObject = this.getElementAt
                        (linkedHashSetThatContainsAllAvailableLotteryBallsObject.getElementAt
                        (randomGenerator.nextInt(linkedHashSetThatContainsAllAvailableLotteryBallsObject.
                                                                                                size())));
                          
                // get indexes
                homeIndex = coupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex();
                awayIndex = coupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex();
                    
                // add the chosen lottery ball to the list Of Chosen Balls For Current SportDay       
                listOfChosenBallsForCurrentSportDay.add(coupleOfCompetitorsIndexLotteryBallObject);
                        
                // give a chance of swap (50% chance)
                coupleOfCompetitorsIndexLotteryBallObject.haveAChanceOfSwap();
                        
                // register the chosen game to the championshipObject schedule
                this.championshipObject.addGameToSportDay(coupleOfCompetitorsIndexLotteryBallObject, 
                                                                                           counterOfSportDays);
                // remove the chosen lottery balls from raffle box
                this.remove(coupleOfCompetitorsIndexLotteryBallObject);
                        
                // make all the possible games which refer to teams that already has been 
                // registered to play at current sport day, unavailable
                for(CoupleOfCompetitorsIndexLotteryBall currentCoupleOfCompetitorsIndexLotteryBallObject: this)
                    if (currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == homeIndex ||
                        currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == homeIndex ||
                        currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == awayIndex ||
                        currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == awayIndex)
                                
                        currentCoupleOfCompetitorsIndexLotteryBallObject.setItOff();
                }
            }
        
        /*
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Total resets: " + backTrackCounter);
        System.out.println("return true");
        */
        
        // lottery was successful
        return true;
    }
    
    // method that makes the lottery for cup type of championship.
    // Multiple lotteries are needed.One after the completion of each phase.
    // Lottery for cup is simpler. It never happens to be stucked to some deadlock
    // when lottering a cup.
    // That 's why this method is a void one instead of boolean.
    
    // note: i should omit the linkedHashet stage of lottery for cup
    // because there are no unavailbale lottery balls
    // i remove every lottery ball that refers to team that has already given a match 
    // in this phase
    public void makeLotteryForCup(){
        
        //int counterForIndexingElementsOfThis;
       
        // a lottery ball
        CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject;
        
        //indexes
        int homeIndex;
        int awayIndex;
        
        // creates a new sportday
        this.championshipObject.addSportDay();
        
        // this "for" traverses all the games indexes of a sportDay (phase) one by one
        for (int counterOfGamesForCurrentSportDay =0;
                counterOfGamesForCurrentSportDay<this.numberOfGamesPerSportDay;
                                             counterOfGamesForCurrentSportDay++){
                        
            // create a generator of random numbers
            Random randomGenerator = new Random();
                         
            // choose randomly a lottery ball from hashSet
            coupleOfCompetitorsIndexLotteryBallObject = this.getElementAt
                                    (randomGenerator.nextInt(this.size()));
                   
            // get indexes
            homeIndex = coupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex();
            awayIndex = coupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex();
                    
            // give a chance of swap (50% chance)
            coupleOfCompetitorsIndexLotteryBallObject.haveAChanceOfSwap();
                        
            // register the chosen game to the championshipObject schedule
            this.championshipObject.addGameToSportDay(
                    ((ChampionshipCup)this.championshipObject).
                            getLotteryBallWithUpdatedIndexes(coupleOfCompetitorsIndexLotteryBallObject), 
                                             this.championshipObject.getSizeOfSportDaysList()-1);
            
            // remove the chosen lottery balls from raffle box
            this.remove(coupleOfCompetitorsIndexLotteryBallObject);
                      
            // remove all the possible games which refer to teams that already has been 
            // registered to play at current sport day
            for(CoupleOfCompetitorsIndexLotteryBall currentCoupleOfCompetitorsIndexLotteryBallObject: 
                                               (Collection<CoupleOfCompetitorsIndexLotteryBall>)this.clone())
                if (currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == homeIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == homeIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == awayIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == awayIndex)
                                
            this.remove(currentCoupleOfCompetitorsIndexLotteryBallObject);
        }
            
        /*
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Total resets: " + backTrackCounter);
        System.out.println("return true");
        */
    }
    
    /*
    /////////////////////////////////////////////////
    // 17/7/18 backup
    
    // method that makes the lottery for cup type of championship.
    // Multiple lotteries are needed.One after the completion of each phase.
    // Lottery for cup is simpler. It never happens to be stucked to some deadlock
    // when lottering a cup.
    // That 's why this method is a void one instead of boolean.
    
    // note: i should omit the linkedHashet stage of lottery for cup
    // because there are no unavailbale lottery balls
    // i remove every lottery ball that refers to team that has already given a match 
    // in this phase
    public void makeLotteryForCup(){
        
        //int counterForIndexingElementsOfThis;
       
        // a lottery ball
        CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject;
        
        //indexes
        int homeIndex;
        int awayIndex;
        
        // size of linkedHashSetThatContainsAllAvailableLotteryBallsObject
        int sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject;
        
        // list that contains the lottery balls (that modelize the games) that have been
        // chosen for current sportDay
        ArrayList<CoupleOfCompetitorsIndexLotteryBall> listOfChosenBallsForCurrentSportDay = 
                                                                                new ArrayList<>();
        
        // linkedHashSet that contains the indexes of all the available at the moment LotteryBalls of the HashSet
        LinkedHashSetWithGetIndex<Integer> linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet =
                                                                    new LinkedHashSetWithGetIndex<>();
        
        // creates a new sportday
        this.championshipObject.addSportDay();
        
        // this "for" traverses all the games indexes of a sportDay (phase) one by one
        for (int counterOfGamesForCurrentSportDay =0;
                counterOfGamesForCurrentSportDay<this.numberOfGamesPerSportDay;
                                             counterOfGamesForCurrentSportDay++){
            
            // clear the linkedHashSetWithGetIndexObject
            linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet.clear();
                      
            // initialization of sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject
            sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject=0;
                        
            // this "for" traverses all the lottery balls of the raffle box
            for(CoupleOfCompetitorsIndexLotteryBall currentBall:this){
                            
                // add to linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet
                // all the indexes of the available at this moment lottery balls of the HashSet
                    
                //if(currentBall.getIsAvailable())
                        linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet.add
                            (sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject);
                // update size                        
                ++sizeOfLinkedHashSetThatContainsAllAvailableLotteryBallsObject;
            }
                       
            // create a generator of random numbers
            Random randomGenerator = new Random();
                         
            // choose randomly a lottery ball from hashSet
            coupleOfCompetitorsIndexLotteryBallObject = this.getElementAt
                (linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet.getElementAt
                (randomGenerator.nextInt
                 (linkedHashSetThatContainsIndexesOfAllAvailableLotteryBallsObjectInHashSet.size())));
                   
            // get indexes
            homeIndex = coupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex();
            awayIndex = coupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex();
                    
            // add the chosen lottery ball to the list Of Chosen Balls For Current SportDay         
            listOfChosenBallsForCurrentSportDay.add(coupleOfCompetitorsIndexLotteryBallObject);
                        
            // give a chance of swap (50% chance)
            coupleOfCompetitorsIndexLotteryBallObject.haveAChanceOfSwap();
                        
            // register the chosen game to the championshipObject schedule
            this.championshipObject.addGameToSportDay(
                    ((ChampionshipCup)this.championshipObject).
                            getLotteryBallWithUpdatedIndexes(coupleOfCompetitorsIndexLotteryBallObject), 
                                             this.championshipObject.getSizeOfSportDaysList()-1);
            
            // remove the chosen lottery balls from raffle box
            this.remove(coupleOfCompetitorsIndexLotteryBallObject);
                      
            // remove all the possible games which refer to teams that already has been 
            // registered to play at current sport day
            for(CoupleOfCompetitorsIndexLotteryBall currentCoupleOfCompetitorsIndexLotteryBallObject: 
                                               (Collection<CoupleOfCompetitorsIndexLotteryBall>)this.clone())
                if (currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == homeIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == homeIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == awayIndex ||
                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == awayIndex)
                                
            this.remove(currentCoupleOfCompetitorsIndexLotteryBallObject);
        }
            
        
        
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //System.out.println("Total resets: " + backTrackCounter);
        //System.out.println("return true");
        
        
        
    }
    */
    /////////////////////////////////////////////////
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // palio
    
    /*
    public boolean makeLotteryForLeague(){
        
        int counterJustForTesting =0;
        int backTrackCounter=0;
        
        boolean done;
        CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject;
        int homeIndex;
        int awayIndex;
        
        ArrayList<CoupleOfCompetitorsIndexLotteryBall> listOfChosenBallsForCurrentSportDay = 
                                                                                new ArrayList<>();
        
        for(int counterOfSportDays =0;counterOfSportDays<this.numberOfSportDays;counterOfSportDays++){
            
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$ eksw for $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println("SportDay: " + (counterOfSportDays));
            System.out.println("");
            
            this.championshipObject.addSportDay();
            
            for(CoupleOfCompetitorsIndexLotteryBall initCoupleOfCompetitorsIndexLotteryBallObject: this)
                                initCoupleOfCompetitorsIndexLotteryBallObject.setItOn();
            
            System.out.println("this (HashSet)");
            System.out.println(this);
            
            listOfChosenBallsForCurrentSportDay.clear();
            
            for (int counterOfGamesPerSportDay =0;counterOfGamesPerSportDay<this.numberOfGamesPerSportDay;
                                                                                    counterOfGamesPerSportDay++){
                System.out.println("************************* mesa for ******************************");
                System.out.println("Game: " + (counterOfGamesPerSportDay));
                System.out.println("");
                
                System.out.println("this (HashSet)");
                System.out.println(this);
            
                if (!existsAvailableBall()){
                    
                    if (backTrackCounter >100){
                        
                        this.championshipObject.clearSportDay();
                        System.out.println("return false");
                        return false;
                    }
                    
                    ++backTrackCounter;
                    
                    this.championshipObject.removeGamesOfSportDay(counterOfSportDays);
                    
                    this.addAll(listOfChosenBallsForCurrentSportDay);
                    listOfChosenBallsForCurrentSportDay.clear();
                    
                    counterOfGamesPerSportDay =0;
                    
                    for(CoupleOfCompetitorsIndexLotteryBall initCoupleOfCompetitorsIndexLotteryBallObject: this)
                                initCoupleOfCompetitorsIndexLotteryBallObject.setItOn();
                    
                    System.out.println("");
                    System.out.println("");
                    System.out.println("RESET---RESET---RESET---RESET---RESET---RESET---RESET---RESET---RESET---RESET!!!");
                    System.out.println("Reset No: " + backTrackCounter);
                    System.out.println("");
                    System.out.println("");
                    
                    System.out.println("Game: " + (counterOfGamesPerSportDay));
                    System.out.println("");
                
                    System.out.println("this (HashSet)");
                    System.out.println(this);
                }
                
                done = false;
                coupleOfCompetitorsIndexLotteryBallObject=null;
                
                while (!done){
                    
                    
                    
                    System.out.println("------------ While -- " 
                            + "Sday: " + counterOfSportDays + " -- "
                            + "Game: " + counterOfGamesPerSportDay +
                            " -----------------------------");
                    System.out.println("counter: " + ++counterJustForTesting);
                    System.out.println("");
                    
                    System.out.println("this (HashSet)");
                    System.out.println(this);
            
                    coupleOfCompetitorsIndexLotteryBallObject = getTheUniqueAvailableBall();
                    
                    if (coupleOfCompetitorsIndexLotteryBallObject != null){
                        System.out.println("Going with unique available ball");
                        
                        homeIndex = coupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex();
                        awayIndex = coupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex();
                    }
                    else{
                        System.out.println("Going with Random");
                        
                        Random randomGenerator = new Random();
                        homeIndex = randomGenerator.nextInt(this.numberOfCompetitors);
                        awayIndex = randomGenerator.nextInt(this.numberOfCompetitors);
                   
                        coupleOfCompetitorsIndexLotteryBallObject = 
                                                        getCoupleOfCompetitorsIndexLotteryBall(homeIndex, awayIndex);   
                    }
                    
                    System.out.println("");
                    System.out.println("Indexes");
                    System.out.println("homeIndex: " + homeIndex);
                    System.out.println("awayIndex: " + awayIndex);
                    
                    System.out.println("aek");
                    System.out.println("check1 !=null?: " + (coupleOfCompetitorsIndexLotteryBallObject != null));
                    System.out.println("ole");
                        
                    if (coupleOfCompetitorsIndexLotteryBallObject != null){
                        System.out.println("coupleOfCompetitorsIndexLotteryBallObject" + 
                                                            coupleOfCompetitorsIndexLotteryBallObject);
                        System.out.println("isAvailable? " + coupleOfCompetitorsIndexLotteryBallObject.getIsAvailable());
                    
                    }
                    
                    if(coupleOfCompetitorsIndexLotteryBallObject != null && 
                            coupleOfCompetitorsIndexLotteryBallObject.getIsAvailable()){
                        
                        done = true;
                        
                        listOfChosenBallsForCurrentSportDay.add(coupleOfCompetitorsIndexLotteryBallObject);
                        
                        coupleOfCompetitorsIndexLotteryBallObject.haveAChanceOfSwap();
                        
                        this.championshipObject.addGameToSportDay(coupleOfCompetitorsIndexLotteryBallObject, 
                                                                                           counterOfSportDays);
                        this.remove(coupleOfCompetitorsIndexLotteryBallObject);
                        
                        for(CoupleOfCompetitorsIndexLotteryBall currentCoupleOfCompetitorsIndexLotteryBallObject: this)
                            if (currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == homeIndex ||
                                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == homeIndex ||
                                    currentCoupleOfCompetitorsIndexLotteryBallObject.getHomeTeamIndex() == awayIndex ||
                                    currentCoupleOfCompetitorsIndexLotteryBallObject.getAwayTeamIndex() == awayIndex)
                                
                                currentCoupleOfCompetitorsIndexLotteryBallObject.setItOff();
                    }
                }
            }
        }
        System.out.println("");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("");
        System.out.println("Total Whiles: " + counterJustForTesting);
        System.out.println("Total resets: " + backTrackCounter);
        
        System.out.println("return true");
        return true;
    }
    */
    
    /*
    public CoupleOfCompetitorsIndexLotteryBall getTheUniqueAvailableBall(){
        
        boolean found = false;
        CoupleOfCompetitorsIndexLotteryBall coupleOfCompetitorsIndexLotteryBallObject = null;
        
        for (CoupleOfCompetitorsIndexLotteryBall currentBall:this){
            if (currentBall.getIsAvailable()){
                
                if (found == true)
                    return null;
                
                found = true;
                coupleOfCompetitorsIndexLotteryBallObject = currentBall;
            }
        }
        return coupleOfCompetitorsIndexLotteryBallObject;
    }
    */
}
