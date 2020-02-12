/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.logic;

import co.edgarsoft.bowlscoreboard.entities.BowlingConstants;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.NormalFrame;
import java.util.List;

/**
 * Class used to calculate all player Frames scores
 *
 * @author ed.sandoval1644
 */
public class ScoreCalculator {

    public static ScoreCalculator getInstance() {
        return new ScoreCalculator();
    }

    /**
     * Main method to populate scores. Requires well built frames first without
     * score
     *
     * @param frames player Frames
     */
    public void calculatePlayerScores(List<Frame> frames) {
        for (Frame frame : frames) {
            calculateFrameScore(frames, frame.getFrameNumber());
        }
    }

    /**
     * Initial method to calculate a frame score recursively.
     *
     * @param frames list of player frames
     * @param frameNumber Frame Number to Calculate from 1 to 10
     */
    private void calculateFrameScore(List<Frame> frames, int frameNumber) {

        Frame currentFrame = searchFrameOnListByframeNumber(frames, frameNumber);
        // taking the previous frame score
        Frame prevFrame = searchFrameOnListByframeNumber(frames, frameNumber - 1);

        int score = prevFrame == null ? 0 : prevFrame.getCurrentScore();
        if (currentFrame.getFrameScore() == BowlingConstants.MAX_ROLL_SCORE) {
            if (currentFrame instanceof NormalFrame) { // flag off for normal frames
                score += getFrameScoreRecurcively(currentFrame, frames, false, 0);
            } else {// flag on for strikes
                score += getFrameScoreRecurcively(currentFrame, frames, true, 0);
            }

        } else {
            // add new score to previous frame
            score += currentFrame.getFrameScore();
        }

        currentFrame.setCurrentScore(score);

    }

    /**
     * Auxiliary method to calculate a frame score recursively, depending of its
     * type.
     *
     * @param frame
     * @param frames all player frames
     * @param isStrike true if the current frame is a Strike (to handle a type
     * of calculation)
     * @param strikeCounter carry counter for strike calculation that restricts
     * is recusion down to 2 (in order to calculate a strike streak)
     * @return the calculated score on current recusion point
     */
    private int getFrameScoreRecurcively(Frame frame, List<Frame> frames, boolean isStrike, int strikeCounter) {

        if (frame != null) {
            if (frame instanceof NormalFrame) {// handle calculation for normal frame

                if (frame.getFrameScore() == BowlingConstants.MAX_ROLL_SCORE) {// if is a 10 scoring frame (a spare)

                    //Spare frame handling 
                    return isStrike ? BowlingConstants.MAX_ROLL_SCORE : BowlingConstants.MAX_ROLL_SCORE + getFrameScoreRecurcively(searchFrameOnListByframeNumber(frames, (frame.getFrameNumber() + 1)), frames, isStrike, strikeCounter + 1);
                } else {

                    int score = 0;
                    if (isStrike) {
                        if (frame.getFrameNumber() > BowlingConstants.MAX_FRAMES) {// Handle the last bonus rolls calculation
                            if (strikeCounter == 1) {
                                // 2 Strikes in a row
                                score = ((NormalFrame) frame).getFirstRoll().getPinsTakenDown();
                            } else {
                                // 1 strike
                                score = frame.getFrameScore();
                            }
                        } else {

                            // Spare frame from Strike calcualtion
                            score = frame.getFrameScore();
                        }
                    } else {
                        // Spare normal score handling
                        score = ((NormalFrame) frame).getFirstRoll().getPinsTakenDown();
                    }

                    return score;
                }

            } else {
                // Handling strike score carry here
                return strikeCounter > 1 ? BowlingConstants.MAX_ROLL_SCORE : (BowlingConstants.MAX_ROLL_SCORE + getFrameScoreRecurcively(searchFrameOnListByframeNumber(frames, (frame.getFrameNumber() + 1)), frames, isStrike, strikeCounter + 1));
            }
        } else {
            return 0;
        }

    }

    /**
     * Auxiliary method to search a frame on list o frames
     *
     * @param frames Frames List
     * @param number frame number (1 to 10)
     * @return frame found, otherwise null
     */
    private Frame searchFrameOnListByframeNumber(List<Frame> frames, int number) {
        for (Frame frame : frames) {
            if (frame.getFrameNumber() == number) {
                return frame;
            }
        }

        return null;
    }
}
