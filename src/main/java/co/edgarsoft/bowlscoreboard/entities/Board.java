/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.entities;

import java.util.List;

/**
 * Class containing a bowling game with multiple players
 *
 * @author ed.sandoval1644
 */
public class Board {

    /**
     * Players on game
     */
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
