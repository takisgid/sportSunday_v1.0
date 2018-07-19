
package comparators;

import java.io.Serializable;
import java.util.Comparator;
import model.CardChampionshipLeague;
import model.ChampionshipLeague;
import model.Competitor;

// Comparator which is used for sorting Competitor objects by decreasing order
// according to their points.

// Specifically, it is given as arguments, two competitors objects
// (competitorObject1 and competitorObject2) and for a specific
// ChampionshipLeague object returns:
//
// --- -1,if competitorObject1 's points-integer is bigger than competitorObject2 's points-integer
// ---  1,if competitorObject1 's points-integer is smaller than competitorObject2 's points-integer
// --- creates a ByScoreDifferenceComparator object and delegate it to resolve this issue, 
//     if their points are equal.

public class ByPointsLeagueComparator implements Comparator<Competitor>,Serializable {

    private ChampionshipLeague currentChampionshipLeagueObject;
    
    public ByPointsLeagueComparator(ChampionshipLeague currentChampionshipLeagueObject){
        this.currentChampionshipLeagueObject = currentChampionshipLeagueObject;
    }
    
    @Override
    public int compare(Competitor competitorObject1, Competitor competitorObject2) {
        
        CardChampionshipLeague cardChampionshipLeagueObject1 = (CardChampionshipLeague)
                this.currentChampionshipLeagueObject.getCardOfCompetitor(competitorObject1);
        
        CardChampionshipLeague cardChampionshipLeagueObject2 = (CardChampionshipLeague)
                this.currentChampionshipLeagueObject.getCardOfCompetitor(competitorObject2);
        
        int currentCompareInt = - cardChampionshipLeagueObject1.getPoints().
                                                    compareTo(cardChampionshipLeagueObject2.getPoints());
        
        if (currentCompareInt !=0)
            return currentCompareInt;
        
        Comparator comparatorObject = new ByScoreDifferenceComparator(this.currentChampionshipLeagueObject);
        return comparatorObject.compare(competitorObject1, competitorObject2);
    }
}
