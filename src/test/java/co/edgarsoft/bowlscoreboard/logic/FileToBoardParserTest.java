/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.logic;

import co.edgarsoft.bowlscoreboard.AbstractBowlingTest;
import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * Test For validating correct board building
 *
 * @author ed.sandoval1644
 */
@RunWith(value = Parameterized.class)
public class FileToBoardParserTest extends AbstractBowlingTest {

  

    public FileToBoardParserTest() {
        super();

    }

    

    /**
     * Unit test for main Method parseFile. Ifthis test fails, something is
     * wrong in that class. Other methods of this class are not tested because
     * they are auxiliar for this one
     *
     * @throws Exception
     */
    @Test
    public void testParseFile() throws Exception {

        super.initialize();

        Board rb = FileToBoardParser.getInstance().parseFile(scoreFile);

        for (Player player : rb.getPlayers()) {

            for (Frame frm : player.getFrames()) {
                if (frm.getFrameNumber() == 5) {
                    Frame oracleFrame = playerFramesOracle.get(player.getName()).get(0);
                    if (!checkFrameIsEqual(oracleFrame, frm)) {
                        assertTrue(false);
                    }
                } else if (frm.getFrameNumber() == 10) {
                    Frame oracleFrame = playerFramesOracle.get(player.getName()).get(1);
                    if (!checkFrameIsEqual(oracleFrame, frm)) {
                        assertTrue(false);
                    }
                }
            }
        }

    }

    /**
     * Checks if two frames are equal. Does not compare currentScores because is a concern of other test class
     *
     * @param f1 frame 1
     * @param f2
     * @return true if equal, otherwise false
     */
    private boolean checkFrameIsEqual(Frame f1, Frame f2) {

        return f1.getFrameNumber() == f2.getFrameNumber() && f1.toString().equals(f2.toString());
    }

}
