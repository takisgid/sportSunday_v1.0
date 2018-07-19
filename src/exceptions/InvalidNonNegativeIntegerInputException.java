
package exceptions;

// Exception for invalid input where a non negative integer should have been given.
public class InvalidNonNegativeIntegerInputException extends Exception {
    
    public InvalidNonNegativeIntegerInputException(){
        super ("Invalid input!" + System.lineSeparator() + "You should enter a non negative integer.");
    }
    
}
