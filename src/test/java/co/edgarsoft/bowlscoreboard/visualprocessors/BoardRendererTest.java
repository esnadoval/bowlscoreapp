/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edgarsoft.bowlscoreboard.visualprocessors;

import co.edgarsoft.bowlscoreboard.AbstractBowlingTest;
import co.edgarsoft.bowlscoreboard.logic.FileToBoardParser;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Test for Graphical Board Output
 *
 * @author ed.sandoval1644
 */
@RunWith(value = Parameterized.class)
public class BoardRendererTest extends AbstractBowlingTest {

    /**
     * Case 1 encoded oracle
     */
    private String base64Case1OutputOracle = "RnJhbWUgICAgIDEgICAgIDIgICAgIDMgICAgIDQgICAgIDUgICAgIDYgICAgIDcgICAgIDggICAgIDkgICAgIDEwICAgIApKZWZmClBpbmZhbGxzICAgICBYICA3ICAvICA5ICAwICAgICBYICBGICA4ICA4ICAvICBGICA2ICAgICBYICAgICBYICAgICBYIDggIDEgIApTY29yZSAgICAgMjAgICAgMzkgICAgNDggICAgNjYgICAgNzQgICAgODQgICAgOTAgICAgMTIwICAgMTQ5ICAgMTY3ICAgICAgICAKSm9obgpQaW5mYWxscyAgMyAgLyAgNiAgMyAgICAgWCAgOCAgMSAgICAgWCAgICAgWCAgOSAgMCAgNyAgLyAgNCAgNCAgICAgWCA5ICAwICAKU2NvcmUgICAgIDE2ICAgIDI1ICAgIDQ0ICAgIDUzICAgIDgyICAgIDEwMSAgIDExMCAgIDEyNCAgIDEzMiAgIDE1MSAgICAgICAgCg";
    /**
     * Case 2 encoded oracle
     */
    private String base64Case2OutputOracle = "RnJhbWUgICAgIDEgICAgIDIgICAgIDMgICAgIDQgICAgIDUgICAgIDYgICAgIDcgICAgIDggICAgIDkgICAgIDEwICAgIApDYXJsClBpbmZhbGxzICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICBGICAwICAKU2NvcmUgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIDAgICAgIAo";
    /**
     * Case 3 encoded oracle
     */
    private String base64Case3OutputOracle = "RnJhbWUgICAgIDEgICAgIDIgICAgIDMgICAgIDQgICAgIDUgICAgIDYgICAgIDcgICAgIDggICAgIDkgICAgIDEwICAgIApDYXJsClBpbmZhbGxzICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgICBYICAgIFggICAgWCAgClNjb3JlICAgICAzMCAgICA2MCAgICA5MCAgICAxMjAgICAxNTAgICAxODAgICAyMTAgICAyNDAgICAyNzAgICAzMDAgICAgICAgICAgICAgCg";

    public BoardRendererTest() {
    }

    /**
     * Test the correct output board
     */
    @Test
    public void testGetGraphicalBoard() throws Exception {
        super.initialize();

        BoardRenderer br = BoardRenderer.getInstance();
        // Get case oracle
        String decodedOracle = decodeBase64(setupCase == CASE_STD ? base64Case1OutputOracle : (setupCase == CASE_FULL ? base64Case2OutputOracle : base64Case3OutputOracle));
        // calculate board output
        String calculatedBoard = br.getGraphicalBoard(FileToBoardParser.getInstance().parseFile(scoreFile));

        // verify if Oracle is same as calculated Output
        assertTrue(calculatedBoard.equals(decodedOracle));

    }

    /**
     * Auxilary method to decode oracles
     *
     * @param codedString
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String decodeBase64(String codedString) throws UnsupportedEncodingException {

        return new String(Base64.getDecoder().decode(codedString.getBytes("UTF-8")));

    }
}
