
package exceptions;

// Exception for invalid result when a tie is registered by the user although 
// this is not legit according to the referred championship type and sport.
public class InvalidResultTieException extends Exception {
    
    public InvalidResultTieException(){
        super ("Invalid input!" + System.lineSeparator() + 
                                "Tie is not a valid result.");
    }
}
