/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

import java.util.List;

/**
 * Class containing al player Frames
 *
 * @author ed.sandoval1644
 */
public class Player {

    /**
     * player frames. Cannot be greater than 10
     */
    private List<Frame> frames;
    /**
     * Player name
     */
    private String name;
    /**
     * Player accumulated final score
     */
    private String totalScore;

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalculatedTotalScore() {
        int sum = 0;
        for (Frame frame : frames) {
            sum += frame.getFrameScore();
        }

        return sum;
    }

}
