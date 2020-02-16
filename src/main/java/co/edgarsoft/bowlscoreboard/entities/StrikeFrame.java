/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

/**
 * Represents a frame which has only 1 roll (it only happens when player makes a strike)
 * @author ed.sandoval1644
 */
public class StrikeFrame extends Frame {

    /**
     * Roll containing the strike number. It must score 10 always
     */
    protected Roll firstRoll;

     /**
     * Gets de calculated Score of the only roll available
     * @return The calculateed score
     */
    @Override
    public Integer getFrameScore() {
        return this.firstRoll.getPinsTakenDown();
    }


    public Roll getFirstRoll() {
        return firstRoll;
    }

  

    public void setFirstRoll(Roll firstRoll) {
        this.firstRoll = firstRoll;
    }


	public StrikeFrame(int frameNumber, Roll firstRoll) {
		super(frameNumber);
		this.firstRoll = firstRoll;
	}

    
    	

}
