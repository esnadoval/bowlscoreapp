/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

import co.edgarsoft.bowlscoreboard.newpackage.utils.PrintUtils;

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

    public String toString() {
        String result = super.toString();

        PrintUtils pu = PrintUtils.getInstance();

        result.concat(" " + (bonusRoll.getPinsTakenDown() == 10 ? "X" : pu.getFormatedLeadingSpace(2, "" + bonusRoll.getPinsTakenDown())));

        return result;
    }

    public Roll getBonusRoll() {
        return bonusRoll;
    }

    public void setBonusRoll(Roll bonusRoll) {
        this.bonusRoll = bonusRoll;
    }

}
