
package exceptions;

// Exception for invalid try of adding when limit has already been reached.
public class InvalidAddBecauseOfLimit extends Exception {
    
    public InvalidAddBecauseOfLimit(String labelString){
        super ("Invalid add!"+ System.lineSeparator() + "You have reached the maximum "
                + "allowed number of " + labelString + ".");
    }
}
