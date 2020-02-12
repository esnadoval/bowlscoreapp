/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.logic;

import co.edgarsoft.bowlscoreboard.entities.Board;
import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.NormalFrame;
import co.edgarsoft.bowlscoreboard.entities.Player;
import co.edgarsoft.bowlscoreboard.entities.Roll;
import co.edgarsoft.bowlscoreboard.entities.StrikeFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author ed.sandoval1644
 */
public class FileToBoardParserTest extends TestCase {

    private HashMap<String, List<Frame>> playerFrames;


    private File scoreFile;

    public FileToBoardParserTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setupC1Frames();

    }

    private void setupC1Frames() {

        playerFrames = new HashMap<String, List<Frame>>();

        scoreFile = new File("score.txt");

        playerFrames.put("Jeff", new ArrayList<Frame>());
        playerFrames.put("John", new ArrayList<Frame>());

        // Jeff
        Frame hfrm = new NormalFrame();

        ((NormalFrame) hfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) hfrm).setSecondRoll(new Roll(8));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(74);

        playerFrames.get("Jeff").add(hfrm);

        Frame plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(167);

        playerFrames.get("Jeff").add(plfrm);

        // John
        hfrm = new StrikeFrame();

        ((StrikeFrame) hfrm).setFirstRoll(new Roll(10));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(82);

        playerFrames.get("John").add(hfrm);

        plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(151);

        playerFrames.get("John").add(plfrm);

    }

    private void setupC2Frames() {

        playerFrames = new HashMap<String, List<Frame>>();

        scoreFile = new File("empty.txt");

        playerFrames.put("Carl", new ArrayList<Frame>());

        // Carl
        Frame hfrm = new NormalFrame();

        ((NormalFrame) hfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) hfrm).setSecondRoll(new Roll(0));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(0);

        playerFrames.get("Carl").add(hfrm);

        Frame plfrm = new NormalFrame();

        ((NormalFrame) plfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) plfrm).setSecondRoll(new Roll(0));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(0);

        playerFrames.get("Carl").add(plfrm);

    }

    private void setupC3Frames() {

        playerFrames = new HashMap<String, List<Frame>>();

        scoreFile = new File("full.txt");

        playerFrames.put("Carl", new ArrayList<Frame>());

        // Carl
        Frame hfrm = new NormalFrame();

        hfrm = new StrikeFrame();

        ((StrikeFrame) hfrm).setFirstRoll(new Roll(10));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(150);

        playerFrames.get("Carl").add(hfrm);

        Frame plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(300);

        playerFrames.get("Carl").add(plfrm);

      

    }

    public void testParseFile() throws Exception {

        Board rb = FileToBoardParser.getInstance().parseFile(scoreFile);

        for (Player player : rb.getPlayers()) {

            for (Frame frm : player.getFrames()) {
                if (frm.getFrameNumber() == 5) {
                    Frame oracleFrame = playerFrames.get(player.getName()).get(0);
                    if (!checkFrameIsEqual(oracleFrame, frm)) {
                        assertTrue(false);
                    }
                } else if (frm.getFrameNumber() == 10) {
                    Frame oracleFrame = playerFrames.get(player.getName()).get(1);
                    if (!checkFrameIsEqual(oracleFrame, frm)) {
                        assertTrue(false);
                    }
                }
            }
        }

    }

    private boolean checkFrameIsEqual(Frame f1, Frame f2) {

        return f1.getFrameNumber() == f2.getFrameNumber() && f1.getCurrentScore() == f2.getCurrentScore() && f1.toString().equals(f2.toString());
    }

}
