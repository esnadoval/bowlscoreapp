package co.edgarsoft.bowlscoreboard;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.logic.FileToBoardParser;
import co.edgarsoft.bowlscoreboard.visualprocessors.BoardRenderer;
import java.io.File;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        try {
            Board bd = FileToBoardParser.getInstance().parseFile(new File("full.txt"));
            String result = BoardRenderer.getInstace().getGraphicalBoard(bd);
            System.out.print(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        
        
    }
}
