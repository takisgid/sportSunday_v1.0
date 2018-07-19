
package exceptions;

// Exception for invalid input where a positive integer should have been given.
public class InvalidPositiveIntegerInputException extends Exception {
    
    public InvalidPositiveIntegerInputException(){
        super ("Invalid input!" + System.lineSeparator() + "You should enter a positive integer.");
    }
    
    public InvalidPositiveIntegerInputException(String labelString){
        super ("Invalid input at " + labelString + "!" + System.lineSeparator() +
                                "You should enter a positive integer.");
    }
}
