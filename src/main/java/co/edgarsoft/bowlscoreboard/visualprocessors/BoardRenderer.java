/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.visualprocessors;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.entities.BowlingConstants;
import co.edgarsoft.bowlscoreboard.entities.FinalBonusSpareFrame;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.NormalFrame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import co.edgarsoft.bowlscoreboard.entities.StrikeFrame;

/**
 * Visual processor for chart view of Board
 *
 * @author ed.sandoval1644
 */
public class BoardRenderer {

	/**
	 * Outputs a formated bowling board with players and scores
	 *
	 * @param board Well made Board objects
	 * @return formated board string to print on file or console
	 */
	public String getGraphicalBoard(Board board) {

		// build table headers

		String finalStr = "Frame\t\t";
		for (int i = 1; i <= BowlingConstants.MAX_FRAMES; i++) {
			finalStr += (i + (i == BowlingConstants.MAX_FRAMES ? "" : "\t\t"));
		}

		finalStr += "\n";

		// build players score
		for (Player player : board.getPlayers()) {

			finalStr += player.getName() + "\nPinfalls";

			String scoreRow = "\nScore\t";

			// build frames (translate its values)
			for (Frame frm : player.getFrames()) {

				if (frm.getFrameNumber() < BowlingConstants.MAX_FRAMES) {

					finalStr += "\t" + printFrame(frm);
					scoreRow += "\t" + frm.getCurrentScore() + "\t";

				} else {

					finalStr += "\t" + printFrame(frm).trim();
					if (frm.getFrameNumber() <= BowlingConstants.MAX_FRAMES) {
						scoreRow += "\t" + frm.getCurrentScore();
					}
				}

			}

			finalStr += scoreRow + "\n";

		}

		return finalStr.trim();

	}

	/**
	 * Generates a string representation for a frame
	 * @param frm the player's frame
	 * @return visual representation of a frame
	 */
	private String printFrame(Frame frm) {

		if (frm instanceof FinalBonusSpareFrame) {
			return printBonusFinalFrame((FinalBonusSpareFrame) frm);
		} else if (frm instanceof NormalFrame) {
			return printNormalFrame((NormalFrame) frm);

		} else if (frm instanceof StrikeFrame) {
			return printStrikeFrame();
		}
		return "";

	}

	/**
	 * Prints a normal frame with 2 rolls
	 * @param frm the normal frame
	 * @return visual representation of normal frame
	 */
	private String printNormalFrame(NormalFrame frm) {

		String firstRollStr = "" + frm.getFirstRoll().getPinsTakenDown();
		String secondRollStr = "" + frm.getSecondRoll().getPinsTakenDown();

		if (frm.getFirstRoll().isFoul()) {
			firstRollStr = BowlingConstants.FOUL_ICON;
		}

		if (frm.getSecondRoll().getPinsTakenDown() + frm.getFirstRoll().getPinsTakenDown() == BowlingConstants.MAX_ROLL_SCORE) {
			secondRollStr = BowlingConstants.SPARE_ICON;
		}

		return firstRollStr + "\t" + secondRollStr;
	}

	/**
	 * Prints a strike frame with the unique roll
	 * @param frm the strike frame
	 * @return visual representation of strike frame
	 */
	private String printStrikeFrame() {

		return "\t" + BowlingConstants.STRIKE_ICON;
	}

	/**
	 * Prints the final frame with  3 rolls if is spare
	 * @param frm the spare frame
	 * @return visual representation of bonus spare frame
	 */
	private String printBonusFinalFrame(FinalBonusSpareFrame frm) {
		String result = printNormalFrame(frm);

		result.concat("\t" + (frm.getBonusRoll().getPinsTakenDown() == 10 ? BowlingConstants.STRIKE_ICON : "\t" + frm.getBonusRoll().getPinsTakenDown()));

		return result;
	}

}
