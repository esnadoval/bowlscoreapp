/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edgarsoft.bowlscoreboard.exceptions;

/**
 *  Exception for handling bad file inputs
 * @author ed.sandoval1644
 */
public class BowlingFileInputException extends Exception {
    
    public static final String NUMBER_EXCEPTION = "Number must be between 0 and 10";
    public static final String INVALID_INPUT_EXCEPTION = "Invalid Input";
    public static final String ROLL_NUMBER_EXCEPTION = "More than 12 rolls are not allowed";
    public static final String FILE_FORMAT_EXCEPTION = "Invalid File Format";

    public BowlingFileInputException(String message) {
        super(message);
    }
    
    
    
    
}
