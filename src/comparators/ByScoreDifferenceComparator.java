
package comparators;

import java.io.Serializable;
import java.util.Comparator;
import model.CardChampionship;
import model.Championship;
import model.Competitor;

// Comparator which is used for sorting Competitor objects by decreasing order
// according to their score difference.

// Specifically,it is given as arguments, two competitors objects
// (competitorObject1 and competitorObject2) and for a specific
// ChampionshipLeague object returns:
//
// ---  -1,if competitorObject1 's score Difference-integer is bigger than 
//      competitorObject2 's score Difference-integer
// ---   1,if competitorObject1 's score Difference-integer is smaller than
//      competitorObject2 's score Difference-integer
// --- creates a ByScoreForComparator object and delegate it to resolve this issue, 
//     if their score Difference integers are equal.

public class ByScoreDifferenceComparator implements Comparator<Competitor>,Serializable {
    
    private Championship currentChampionshipObject;
    
    public ByScoreDifferenceComparator(Championship currentChampionshipObject){
        this.currentChampionshipObject = currentChampionshipObject;
    }
    
    @Override
    public int compare(Competitor competitorObject1, Competitor competitorObject2) {
        
        CardChampionship cardChampionshipObject1 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject1);
        
        CardChampionship cardChampionshipObject2 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject2);
        
        int currentCompareInt = - cardChampionshipObject1.getScoreDifference().
                                                    compareTo(cardChampionshipObject2.getScoreDifference());
        
        if (currentCompareInt !=0)
            return currentCompareInt;
        
        Comparator comparatorObject = new ByScoreForComparator(this.currentChampionshipObject);
        return comparatorObject.compare(competitorObject1, competitorObject2);
    } 
}
