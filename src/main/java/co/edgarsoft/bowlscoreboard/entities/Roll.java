/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edgarsoft.bowlscoreboard.entities;

/**
 * Represent a roll of a player
 * @author ed.sandoval1644
 */
public class Roll {
    
    /**
     * pins Taken down form 0 to 10
     */
    private int pinsTakenDown;
    
    /**
     * Frame that indicates if it was a foul ball
     */
    protected boolean isFoul;

    
    
    public int getPinsTakenDown() {
        return pinsTakenDown;
    }

    public void setPinsTakenDown(int pinsTakenDown) {
        this.pinsTakenDown = pinsTakenDown;
    }


	public Roll(int pinsTakenDown, boolean isFoul) {
		super();
		this.pinsTakenDown = pinsTakenDown;
		this.isFoul = isFoul;
	}

	public boolean isFoul() {
		return isFoul;
	}

	public void setFoul(boolean isFoul) {
		this.isFoul = isFoul;
	}

    
    
}
