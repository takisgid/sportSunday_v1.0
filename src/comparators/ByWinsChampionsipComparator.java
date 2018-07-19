
package comparators;

import java.io.Serializable;
import java.util.Comparator;
import model.CardChampionship;
import model.Championship;
import model.Competitor;

// Comparator which is used for sorting Competitor objects by decreasing order
// according to their number of wins.

// Specifically, it is given as arguments, two competitors objects
// (competitorObject1 and competitorObject2) and for a specific
// ChampionshipLeague object returns:
//
// --- -1,if competitorObject1 's number of wins-integer is bigger than
//        competitorObject2 's number of wins-integer.
// ---  1,if competitorObject1 's number of wins-integer is smaller than
//        competitorObject2 's number of wins-integer.
// --- creates a ByScoreDifferenceComparator object and delegate it to resolve this issue, 
//     if their points are equal.

public class ByWinsChampionsipComparator implements Comparator<Competitor>,Serializable {
    
    private Championship currentChampionshipObject;
    
    public ByWinsChampionsipComparator(Championship currentChampionshipObject){
        this.currentChampionshipObject = currentChampionshipObject;
    }
    
    @Override
    public int compare(Competitor competitorObject1, Competitor competitorObject2) {
        
        CardChampionship cardChampionshipObject1 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject1);
        
        CardChampionship cardChampionshipObject2 = 
                this.currentChampionshipObject.getCardOfCompetitor(competitorObject2);
        
        int currentCompareInt = - cardChampionshipObject1.getNumberOfWins().
                                                    compareTo(cardChampionshipObject2.getNumberOfWins());
        
        if (currentCompareInt !=0)
            return currentCompareInt;
        
        Comparator comparatorObject = new ByScoreDifferenceComparator(this.currentChampionshipObject);
        return comparatorObject.compare(competitorObject1, competitorObject2); 
    } 
}
