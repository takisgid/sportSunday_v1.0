
package comparators;

import java.io.Serializable;
import java.util.Comparator;
import model.Athlete;
import model.CardChampionship;
import model.Championship;
import model.Competitor;
import model.Team;

// Comparator which is used for sorting Competitor objects by increasing order
// according to their score against number.

// Specifically, it is given as arguments, two competitors objects
// (competitorObject1 and competitorObject2) and for a specific
// ChampionshipLeague object returns:
//
// ---  -1,if competitorObject1 's score against-integer is smaller than 
//      competitorObject2 's score against-integer.
// ---   1,if competitorObject1 's score against-integer is bigger than
//      competitorObject2 's score against-integer.
// --- order them by alphabetical order 
//     if their score against integers are equal.

public class ByScoreAgainstComparator implements Comparator<Competitor>,Serializable {
    
    private Championship currentChampionshipObject;
    
    public ByScoreAgainstComparator(Championship currentChampionshipObject){
        this.currentChampionshipObject = currentChampionshipObject;
    }
    
    @Override
    public int compare(Competitor competitorObject1, Competitor competitorObject2) {
        
        CardChampionship cardChampionshipObject1 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject1);
        
        CardChampionship cardChampionshipObject2 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject2);
        
        int currentCompareInt = cardChampionshipObject1.getScoreAgainst().
                                                    compareTo(cardChampionshipObject2.getScoreAgainst());
        
        if (currentCompareInt !=0)
            return currentCompareInt;
        
        if (competitorObject1 instanceof Team)
            return ((Team)competitorObject1).compareTo((Team)competitorObject2);
        
        return ((Athlete)competitorObject1).compareTo((Athlete)competitorObject2);
    }
}
