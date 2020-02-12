/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

/**
 * Represents a general frame composition. Extended classes implement the way a frame calculates the Score. 
 * @author ed.sandoval1644
 */
public abstract class Frame {

    /**
     * Frame number from 1 to 10
     */
    private int frameNumber;

    /**
     * Score of the currentFrame
     */
    private int currentScore;
    
    
    /**
     * Gets de calculated Score of a frame
     * @return The calculateed score
     */
    public abstract Integer getFrameScore();

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
    
    

}
