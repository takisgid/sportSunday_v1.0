
package exceptions;

// exception for unsatisfied wanted inequality relationship between A and B
public class InvalidAShouldBeSmallerEqualThanBInputException extends Exception {
    
    public InvalidAShouldBeSmallerEqualThanBInputException(String valuaAString, String valuaBString){
        super ("Invalid input!" + System.lineSeparator() +
                valuaAString + " should be smaller or equal than " + valuaBString + ".");
    } 
}
