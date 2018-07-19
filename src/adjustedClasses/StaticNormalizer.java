
package adjustedClasses;

// Class which contains static methods for normalizing user input.
public class StaticNormalizer {
    
    // Normalizes number input
    // Erases all leading zeros and every space.
    public static String normalizeNumberString(String nameString){
        return (nameString.replaceFirst("^0+(?!$)", "").replaceAll(" ", ""));
    }
    
    // Normalizes text input
    // Erases all leading and trailing white spaces and
    // replaces multiple continuous spaces with a single one.
    public static String normalizeTextString(String textString){
        return textString.trim().replaceAll(" +", " ");
    }
}
