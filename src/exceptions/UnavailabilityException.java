
package exceptions;

// Exception which expresses that some unavaialable for current version 
// action has been chosen from the user.
public class UnavailabilityException extends Exception {
    
    public UnavailabilityException(String stringObject){
        super (stringObject);
    }
}
