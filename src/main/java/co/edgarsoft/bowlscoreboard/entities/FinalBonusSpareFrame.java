/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

/**
 * Represents the 10th Frame which have 3 posible Rolls, if the last roll was an Spare.
 *
 * @author ed.sandoval1644
 */
public class FinalBonusSpareFrame extends NormalFrame {

    /**
     * The bonus roll to count
     */
    protected Roll bonusRoll;

    /**
     * Gets de calculated Score of the three rolls combined
     *
     * @return The calculateed score
     */
    public Integer getFrameScore() {
        return this.firstRoll.getPinsTakenDown() + this.getSecondRoll().getPinsTakenDown() + bonusRoll.getPinsTakenDown();
    }

  
    public Roll getBonusRoll() {
        return bonusRoll;
    }

    public void setBonusRoll(Roll bonusRoll) {
        this.bonusRoll = bonusRoll;
    }

	public FinalBonusSpareFrame(int frameNumber,Roll firstRoll, Roll secondRoll,Roll bonusRoll) {
		super(frameNumber,firstRoll, secondRoll);
		this.bonusRoll = bonusRoll;
	}
    
    

}
