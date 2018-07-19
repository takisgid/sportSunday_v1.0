
package adjustedClasses;

import javax.swing.JOptionPane;

// Class which contains static methods for showing modified informative pop up windows
public class StaticShower {
    
    // some constants
    public static final String NOT_EXACTLY_ONE_LINE_PERIOD = "You should select exactly one record!";
    public static final String NOT_EXACTLY_ONE_LINE = "You should select exactly one record";
    
    public static void showWarningMessageForInvalidInput(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showWarningMessage(String stringObject){
        JOptionPane.showMessageDialog(null, stringObject, "Warning!", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showMessage(String stringObject){
        JOptionPane.showMessageDialog(null, stringObject, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showMessage(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showSavedMessage(){
        JOptionPane.showMessageDialog(null, "Data saved successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showInitializationMessage(){
        JOptionPane.showMessageDialog(null, "Default data initialized successfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
