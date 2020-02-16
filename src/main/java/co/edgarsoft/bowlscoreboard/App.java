package co.edgarsoft.bowlscoreboard;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.logic.FileToBoardParser;
import co.edgarsoft.bowlscoreboard.visualprocessors.BoardRenderer;
import java.io.File;

/**
 * Entry Point for Bowling App!
 *
 */
public class App {

    public static void main(String[] args) {
        
        try {
            Board bd = new FileToBoardParser().parseFile(new File(args[0]));
            String result = new BoardRenderer().getGraphicalBoard(bd);
            System.out.print(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        
        
    }
}
