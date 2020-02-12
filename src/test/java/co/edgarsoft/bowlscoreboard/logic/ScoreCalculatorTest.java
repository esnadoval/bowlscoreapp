/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.logic;

import co.edgarsoft.bowlscoreboard.AbstractBowlingTest;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Tests Score Calculator alone
 *
 * @author ed.sandoval1644
 */
@RunWith(value = Parameterized.class)
public class ScoreCalculatorTest extends AbstractBowlingTest {

    public ScoreCalculatorTest() {

        super();
    }

    /**
     * Test of calculatePlayerScores method, of class ScoreCalculator.
     */
    @Test
    public void testCalculatePlayerScores() throws Exception {

        super.initialize();

        // Test over only one player
        Player selected = FileToBoardParser.getInstance().parseFile(scoreFile).getPlayers().get(0);

        // Load Board and implicitly run calculatePlayerScores
        List<Frame> caseFrames = selected.getFrames();

        for (Frame frm : caseFrames) {
            if (frm.getFrameNumber() == 5) {
                Frame oracleFrame = playerFramesOracle.get(selected.getName()).get(0);

                // Only assert the currentScore part
                if (oracleFrame.getCurrentScore() != frm.getCurrentScore()) {
                    assertTrue(false);
                }
            } else if (frm.getFrameNumber() == 10) {
                Frame oracleFrame = playerFramesOracle.get(selected.getName()).get(1);
                if (oracleFrame.getCurrentScore() != frm.getCurrentScore()) {
                    assertTrue(false);
                }
            }
        }

    }

}
