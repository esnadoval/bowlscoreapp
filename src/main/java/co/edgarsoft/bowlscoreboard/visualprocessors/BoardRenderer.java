/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.visualprocessors;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.entities.BowlingConstants;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import co.edgarsoft.bowlscoreboard.newpackage.utils.PrintUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Visual processor for chart view of Board
 *
 * @author ed.sandoval1644
 */
public class BoardRenderer {

    public static BoardRenderer getInstance() {
        return new BoardRenderer();
    }

    /**
     * Outputs a formated bowling board with players and scores
     *
     * @param board Well made Board objects
     * @return formated board string to print on file or console
     */
    public String getGraphicalBoard(Board board) {

        PrintUtils pu = PrintUtils.getInstance();

        List<List<String>> tableHeaders = new ArrayList<List<String>>();

        // build table headers
        List<String> frameCell = new ArrayList<String>();
        frameCell.add(pu.getFormatedTrailingSpace(8, "Frame"));
        tableHeaders.add(frameCell);
        for (int i = 1; i <= BowlingConstants.MAX_FRAMES; i++) {
            List<String> cell = new ArrayList<String>();
            cell.add("" + pu.getFormatedTrailingSpace(4, "" + i));
            tableHeaders.add(cell);
        }

        String finalStr = pu.printTable(tableHeaders, 2);

        // build players score
        for (Player player : board.getPlayers()) {

            finalStr += player.getName() + "\n";

            List<List<String>> table = new ArrayList<List<String>>();

            List<String> labels = new ArrayList<String>();
            labels.add("Pinfalls");
            labels.add("Score");

            table.add(labels);

            //build frames (translate its values)
            for (Frame frm : player.getFrames()) {

                if (frm.getFrameNumber() <= BowlingConstants.MAX_FRAMES) {
                    List<String> frameStrs = new ArrayList<String>();
                    frameStrs.add(frm.toString());
                    frameStrs.add("" + frm.getCurrentScore());
                    // add to columns the current frame
                    table.add(frameStrs);
                } else {

                    // add Roll result to last frame if there is bonus rolls (built on a frame > 10)
                    String latestPinfalls = table.get(table.size() - 1).get(0);

                    table.get(table.size() - 1).set(0, latestPinfalls += " " + frm.toString());

                }

            }

            finalStr += pu.printTable(table, 2);

        }

        return finalStr;

    }
}
