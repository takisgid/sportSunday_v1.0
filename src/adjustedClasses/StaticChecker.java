
package adjustedClasses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class which contains boolean static methods for checking when some user input
// should be considered invalid.
// using regex.Matcher, regex.Pattern
public class StaticChecker {
    
    // Checks if a stringObject represents a non negative integer.
    public static boolean checkForInvalidNonNegativeIntegerInputException(String stringObject){
        
        String patternString = "[0-9]+|-";
        Pattern patternObject = Pattern.compile(patternString);
        Matcher matcherObject = patternObject.matcher(stringObject);
        
        if(!matcherObject.matches())
            return true; // informs that a relevant exception object should be thrown
        
        return false; 
    }
    
    // Checks if a stringObject represents a positive integer.
    public static boolean checkForInvalidPositiveIntegerInputException(String stringObject){
        
        String patternString = "[1-9][0-9]*";
        Pattern patternObject = Pattern.compile(patternString);
        Matcher matcherObject = patternObject.matcher(stringObject);
        
        if(!matcherObject.matches())
            return true; // informs that a relevant exception object should be thrown
        
        return false;
    }
    
    // Checks if valueA is smaller or equal than valueB.
    public static boolean checkForInvalidAShouldBeSmallerEqualThanBInputException(String stringValueA,
                                                                                 String stringValueB){
        
        int intValueA = Integer.parseInt(stringValueA);
        int intValueB = Integer.parseInt(stringValueB);
        
        if(intValueA > intValueB)
            return true; // informs that a relevant exception object should be thrown
        
        return false; 
    }
    
    // Checks if a String Object could be valid text.
    public static boolean checkForInvalidTextException(String stringObject){
        
        String patternString = "[a-zA-zα-ωΑ-Ω0-9 ]+";
        Pattern patternObject = Pattern.compile(patternString);
        Matcher matcherObject = patternObject.matcher(stringObject);
        
        if(!matcherObject.matches())
            return true; // informs that a relevant exception object should be thrown
        
        return false; 
    }
}
