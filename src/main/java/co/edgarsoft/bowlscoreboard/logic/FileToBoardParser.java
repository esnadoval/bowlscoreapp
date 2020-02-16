/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.logic;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.entities.BowlingConstants;
import co.edgarsoft.bowlscoreboard.entities.FinalBonusSpareFrame;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.NormalFrame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import co.edgarsoft.bowlscoreboard.entities.Roll;
import co.edgarsoft.bowlscoreboard.entities.StrikeFrame;
import co.edgarsoft.bowlscoreboard.exceptions.BowlingFileInputException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used for parsing the game file to a board with all the data. Uses the
 * Score Calculator when creating frames
 *
 * @author ed.sandoval1644
 */
public class FileToBoardParser {

    /**
     * Hashmap for storing bare rolls for each player and then build Frames
     */
    private HashMap<String, List<Roll>> playerFrames;

	
    public FileToBoardParser() {

        playerFrames = new HashMap<String, List<Roll>>();
    }

    /**
     * Main method for parsing a Game file to Board.
     *
     * @param file game file in txt
     * @return a well built Board
     * @throws BowlingFileInputException when file is not formated correctly
     */
    public Board parseFile(File file) throws BowlingFileInputException {

        try {

            // Read file
            BufferedReader br = new BufferedReader(new FileReader(file));
            for (String line; (line = br.readLine()) != null;) {

                loadPlayerHashFromLine(line);

            }

            //Validate hash for excessive rolls
            if (!validatePlayerRolls()) {
                throw new BowlingFileInputException(BowlingFileInputException.ROLL_NUMBER_EXCEPTION);
            }

            Board bowlBoard = new Board();

            bowlBoard.setPlayers(processPlayers());

            return bowlBoard;
        } catch (Exception e) {

            throw new BowlingFileInputException(BowlingFileInputException.FILE_FORMAT_EXCEPTION + ": " + e.getMessage());
        }

    }

    /**
     * Validates HashMap if at least one player hass excessive rolls. Hashmap
     * must be initialized
     *
     * @return true if valid, false otherwise
     */
    private boolean validatePlayerRolls() {

        for (Map.Entry<String, List<Roll>> pair : playerFrames.entrySet()) {
            if (pair.getValue().size() > BowlingConstants.MAX_ROLLS) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates directly roll input for invalid numbers or letters
     *
     * @param Roll input
     * @return true if valid, false otherwise
     */
    private boolean validateRollInput(String input) {

        boolean valid = false;
        if (input.equalsIgnoreCase(BowlingConstants.FOUL_ICON)) {
            valid = true;
        } else {
            try {
                int num = Integer.parseInt(input);
                if (num >= 0 && num <= BowlingConstants.MAX_ROLL_SCORE) {
                    valid = true;
                }
            } catch (Exception e) {
            }

        }

        return valid;

    }

    /**
     * Method to process all players in sequence
     *
     * @return procesed players with Frames
     */
    private List<Player> processPlayers() {
        List<Player> pResults = new ArrayList<Player>();

        for (String pName : playerFrames.keySet()) {

            Player nPlayer = new Player();
            nPlayer.setName(pName);
            nPlayer.setFrames(processFramesFromBareRolls(playerFrames.get(pName)));
            pResults.add(nPlayer);

        }

        return pResults;
    }

    /**
     * Processor Method to merge player rolls into a frame
     *
     * @param bRolls player's rolls in sequence
     * @return merged rolls into Frames
     */
    private List<Frame> processFramesFromBareRolls(List<Roll> bRolls) {
        int currentFrame = 0;
        int rollNumber = 0;

        List<Frame> pFrames = new ArrayList<Frame>();
        Roll carryFirstRoll = null;
        Roll carryBonusRoll = null;

        for (Roll roll : bRolls) {

            if (rollNumber == 0 && roll.getPinsTakenDown() == BowlingConstants.MAX_ROLL_SCORE) {// Strike at first case
                currentFrame++;
                pFrames.add(buildFrame(currentFrame, roll, null, null));
            } else if (rollNumber == 1 && currentFrame == 9 && validateBonusRoll(carryFirstRoll, roll)) { //Validate Bonus Strike at end
                carryBonusRoll = roll;
                rollNumber = 3;
            } else if (rollNumber == 3) {//Add Frame with bonus Roll
                rollNumber = 0;
                currentFrame++;
                pFrames.add(buildFrame(currentFrame, carryFirstRoll, carryBonusRoll, roll));
            } else if (rollNumber == 0) {//Second Roll
                rollNumber = 1;
                carryFirstRoll = roll;
            } else {// New Frame
                rollNumber = 0;
                currentFrame++;
                pFrames.add(buildFrame(currentFrame, carryFirstRoll, roll, null));

            }
        }	

        // Now calculate this frame's score
        new ScoreCalculator().calculatePlayerScores(pFrames);

        return pFrames;
    }

    /**
     * Creator method to generate an instance of the multiple kinds of Frame,
     * acording to incoming rolls
     *
     * @param frameNumber frame numer from 1 to 10
     * @param r1 First Roll. Only this makes a StrikeFrame.
     * @param r2 Second Roll. This + r1 makes a NormalFrame.
     * @param r3 Bonus Roll. This + r1 + r2 makes a FinalBonusSpareFrame.
     * @return a Frame instance
     */
    private Frame buildFrame(int frameNumber, Roll r1, Roll r2, Roll r3) {

        Frame fr = null;

        if (r3 == null && r2 == null) {
            fr = new StrikeFrame(frameNumber,r1);
        } else if (r3 == null) {
            fr = new NormalFrame(frameNumber,r1,r2);
        } else {
            fr = new FinalBonusSpareFrame(frameNumber,r1,r2,r3);
            
        }

        return fr;
    }

    /**
     * Validates if rolls allows to play a third
     *
     * @param r1 1st roll
     * @param r2 2nd roll
     * @return true if completes 10 pins, or false otherwise
     */
    private boolean validateBonusRoll(Roll r1, Roll r2) {
        return r1.getPinsTakenDown() + r2.getPinsTakenDown() == BowlingConstants.MAX_ROLL_SCORE;
    }

    /**
     * Auxiliary method to build Rolls from file lines and add it to the HashMap
     *
     * @param line fileLine
     * @throws Exception if any file line does not met the required input
     */
    private void loadPlayerHashFromLine(String line) throws Exception {
        String[] currLine = line.split(" ");

        if (!validateRollInput(currLine[1])) {
            throw new BowlingFileInputException(BowlingFileInputException.INVALID_INPUT_EXCEPTION);
        }

        boolean isFoul = false;
        if (currLine[1].equalsIgnoreCase(BowlingConstants.FOUL_ICON)) { // if file has F instead of 0
            currLine[1] = "0";
            isFoul = true;
            
        }

        Roll nRoll = new Roll(Integer.parseInt(currLine[1]),isFoul);

        if (playerFrames.get(currLine[0]) == null) {
            playerFrames.put(currLine[0], new ArrayList<Roll>());
        }
        
        playerFrames.get(currLine[0]).add(nRoll);
    }

}
