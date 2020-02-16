/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

/**
 * Represents a frame with 2 rolls (the common frame for not taking down all the
 * pins). Used for spare a foul. Extends StrikeFrame that contains the
 * firstRoll.
 *
 * @author ed.sandoval1644
 */
public class NormalFrame extends StrikeFrame {

    /**
     * Second roll of player.it can be Spare
     */
    protected Roll secondRoll;


    
     /**
     * Gets de calculated Score of two rolls
     * @return The calculateed score
     */	
    
    @Override
    public Integer getFrameScore() {
        return this.firstRoll.getPinsTakenDown() + this.getSecondRoll().getPinsTakenDown();
    }



    public Roll getFirstRoll() {
        return firstRoll;
    }

    public Roll getSecondRoll() {
        return secondRoll;
    }

    public void setFirstRoll(Roll firstRoll) {
        this.firstRoll = firstRoll;
    }

    public void setSecondRoll(Roll secondRoll) {
        this.secondRoll = secondRoll;
    }



	public NormalFrame(int frameNumber,Roll firstRoll, Roll secondRoll) {
		super(frameNumber, firstRoll);
		this.secondRoll = secondRoll;

	}
	
	
    
    

}
