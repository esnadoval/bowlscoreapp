/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard;

import co.edgarsoft.bowlscoreboard.entities.Frame;
import co.edgarsoft.bowlscoreboard.entities.NormalFrame;
import co.edgarsoft.bowlscoreboard.entities.Roll;
import co.edgarsoft.bowlscoreboard.entities.StrikeFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import junit.framework.TestCase;
import org.junit.runners.Parameterized;

/**
 * Class for handling the possible cases for a Unit test. Bulds all test cases
 *
 * @author ed.sandoval1644
 */
public abstract class AbstractBowlingTest extends TestCase {

    public static final String CASE_STD = "Case Standard";
    public static final String CASE_FULL = "Case Full Play";
    public static final String CASE_EMPTY = "Case Empty Play";

    /**
     * Parameter for switching test case
     */
    @Parameterized.Parameter(value = 0)
    public String setupCase = CASE_STD;

    /**
     * Posible parameters for Parametrized tests
     *
     * @return
     */
    @Parameterized.Parameters(name = "Case {index} - {0}")
    public static Collection<Object> data() {
        return Arrays.asList(new String[]{
            CASE_STD, CASE_FULL, CASE_EMPTY
        });
    }

    /**
     * Initialization of data for test cases
     */
    protected void initialize() {

        switch (setupCase) {
            case CASE_FULL:
                setupC2();
                break;

            case CASE_EMPTY:
                setupC3();
                break;

            default:
                setupC1();
                break;
        }

    }

    /**
     * Oracle handling the expected frame results
     */
    protected HashMap<String, List<Frame>> playerFramesOracle;
    /**
     * txt of game
     */
    protected File scoreFile;

    /**
     * Setup case for score.txt
     */
    public void setupC1() {

        playerFramesOracle = new HashMap<String, List<Frame>>();

        scoreFile = new File("score.txt");

        playerFramesOracle.put("Jeff", new ArrayList<Frame>());
        playerFramesOracle.put("John", new ArrayList<Frame>());

        // Jeff
        Frame hfrm = new NormalFrame();

        ((NormalFrame) hfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) hfrm).setSecondRoll(new Roll(8));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(74);

        playerFramesOracle.get("Jeff").add(hfrm);

        Frame plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(167);

        playerFramesOracle.get("Jeff").add(plfrm);

        // John
        hfrm = new StrikeFrame();

        ((StrikeFrame) hfrm).setFirstRoll(new Roll(10));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(82);

        playerFramesOracle.get("John").add(hfrm);

        plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(151);

        playerFramesOracle.get("John").add(plfrm);

    }

    /**
     * Setup case for empty text
     */
    public void setupC2() {

        playerFramesOracle = new HashMap<String, List<Frame>>();

        scoreFile = new File("empty.txt");

        playerFramesOracle.put("Carl", new ArrayList<Frame>());

        // Carl
        Frame hfrm = new NormalFrame();

        ((NormalFrame) hfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) hfrm).setSecondRoll(new Roll(0));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(0);

        playerFramesOracle.get("Carl").add(hfrm);

        Frame plfrm = new NormalFrame();

        ((NormalFrame) plfrm).setFirstRoll(new Roll(0));
        ((NormalFrame) plfrm).setSecondRoll(new Roll(0));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(0);

        playerFramesOracle.get("Carl").add(plfrm);

    }

    /**
     * Setup case fora all Strike
     */
    public void setupC3() {

        playerFramesOracle = new HashMap<String, List<Frame>>();

        scoreFile = new File("full.txt");

        playerFramesOracle.put("Carl", new ArrayList<Frame>());

        // Carl
        Frame hfrm = new NormalFrame();

        hfrm = new StrikeFrame();

        ((StrikeFrame) hfrm).setFirstRoll(new Roll(10));

        hfrm.setFrameNumber(5);
        hfrm.setCurrentScore(150);

        playerFramesOracle.get("Carl").add(hfrm);

        Frame plfrm = new StrikeFrame();

        ((StrikeFrame) plfrm).setFirstRoll(new Roll(10));

        plfrm.setFrameNumber(10);
        plfrm.setCurrentScore(300);

        playerFramesOracle.get("Carl").add(plfrm);

    }
}
