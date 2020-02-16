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
    private String base64Case1OutputOracle = "RnJhbWUJCTEJCTIJCTMJCTQJCTUJCTYJCTcJCTgJCTkJCTEwCkplZmYKUGluZmFsbHMJCVgJNwkvCTkJMAkJWAkwCTgJOAkvCUYJNgkJWAkJWAlYCTgJMQpTY29yZQkJMjAJCTM5CQk0OAkJNjYJCTc0CQk4NAkJOTAJCTEyMAkJMTQ5CQkxNjcKSm9obgpQaW5mYWxscwkzCS8JNgkzCQlYCTgJMQkJWAkJWAk5CTAJNwkvCTQJNAlYCTkJMApTY29yZQkJMTYJCTI1CQk0NAkJNTMJCTgyCQkxMDEJCTExMAkJMTI0CQkxMzIJCTE1MQ==";
    /**
     * Case 2 encoded oracle
     */
    private String base64Case2OutputOracle = "RnJhbWUJCTEJCTIJCTMJCTQJCTUJCTYJCTcJCTgJCTkJCTEwCkNhcmwKUGluZmFsbHMJCVgJCVgJCVgJCVgJCVgJCVgJCVgJCVgJCVgJWAlYCVgKU2NvcmUJCTMwCQk2MAkJOTAJCTEyMAkJMTUwCQkxODAJCTIxMAkJMjQwCQkyNzAJCTMwMA==";
    
    		
    /**
     * Case 3 encoded oracle
     */
    private String base64Case3OutputOracle = "RnJhbWUJCTEJCTIJCTMJCTQJCTUJCTYJCTcJCTgJCTkJCTEwCkNhcmwKUGluZmFsbHMJMAkwCTAJMAkwCTAJMAkwCTAJMAkwCTAJMAkwCTAJMAkwCTAJMAkwClNjb3JlCQkwCQkwCQkwCQkwCQkwCQkwCQkwCQkwCQkwCQkw";

    public BoardRendererTest() {
    }

    /**
     * Test the correct output board
     */
    @Test
    public void testGetGraphicalBoard() throws Exception {
        super.initialize();

        BoardRenderer br = new BoardRenderer();
        // Get case oracle
        String decodedOracle = decodeBase64(setupCase == CASE_STD ? base64Case1OutputOracle : (setupCase == CASE_FULL ? base64Case2OutputOracle : base64Case3OutputOracle));
        // calculate board output
        String calculatedBoard = br.getGraphicalBoard(new FileToBoardParser().parseFile(scoreFile));
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
