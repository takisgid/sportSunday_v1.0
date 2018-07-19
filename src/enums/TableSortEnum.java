
package enums;

import java.io.Serializable;

// Enum Sport: Includes all supportive sorting options of Statistics table at report screen 
// of any Championship.
public enum TableSortEnum implements Serializable {
    BASIC(0),BYSCOREFOR(1),BYSCOREAGAINST(2),BYSCOREDIFFERENCE(3);
    
    int orderNumber;
    
    TableSortEnum(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    // static method returns the enum value of the given index.
    
    public static TableSortEnum byGivenOrderNumber(int orderNumber) {
        for (TableSortEnum currentTableSortEnum : TableSortEnum.values()) {
            if (currentTableSortEnum.orderNumber == orderNumber) 
                return currentTableSortEnum;
        }
        return null;
    }
    
    @Override
    public String toString(){
        
       switch (this) {
            case BASIC:
                    return "Basic";
            case BYSCOREFOR:
                    return "Best Offence";
            case BYSCOREAGAINST:
                    return "Best Defence";
            case BYSCOREDIFFERENCE:
                    return "Best difference";
            default:
                return null;
        }
    }
}

