
package exceptions;

// Exception for invalid input where text consist only of letters, numbers and space
// should have been given.
public class InvalidTextException extends Exception {
    
    public InvalidTextException(String labelString){
        super ("Invalid input at " + labelString + "!" + System.lineSeparator() + 
                                "You should enter a non empty text consisted of letters and numbers.");
    }
}
