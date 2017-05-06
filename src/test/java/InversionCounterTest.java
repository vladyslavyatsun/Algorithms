import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by dog on 4/30/17.
 */
public class InversionCounterTest {
    private static Utils utils;
    private InversionCounter inversionCounter;

    @BeforeClass
    public static void initClass() {
        utils = new Utils();
    }

    @AfterClass
    public static void tearDownClass() {
        utils = null;
    }

    @Before
    public void initTest() {
        inversionCounter = new InversionCounter();
    }
    @After
    public void tearDownTest() {
        inversionCounter = null;
    }

    @Test
    public void testInversion() {
        int [][] matrix = utils.shiftLeft(utils.readMatrix("src/test/data/testInversion1"), 1);
        assertEquals(8, inversionCounter.inversionCount(matrix[0], matrix[1]));
        assertEquals(5, inversionCounter.inversionCount(matrix[0], matrix[2]));
        assertEquals(2, inversionCounter.inversionCount(matrix[0], matrix[3]));
        assertEquals(6, inversionCounter.inversionCount(matrix[0], matrix[4]));
    }

    @Test
    public void controlTestInversion1() {
        int [][] matrix = utils.shiftLeft(utils.readMatrix("src/test/data/testInversion2"), 1);
        assertEquals(7, inversionCounter.inversionCount(matrix[451], matrix[99]));
        assertEquals(0, inversionCounter.inversionCount(matrix[862], matrix[28]));
    }

    @Test
    public void controlTestInversion2() {
        int [][] matrix = utils.shiftLeft(utils.readMatrix("src/test/data/testInversion3"), 1);
        assertEquals(2368, inversionCounter.inversionCount(matrix[617], matrix[0]));
        assertEquals(2483, inversionCounter.inversionCount(matrix[950], matrix[177]));
    }
}