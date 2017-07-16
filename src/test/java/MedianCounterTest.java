import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dog on 7/17/17.
 */
public class MedianCounterTest {
    public MedianCounter medianCounter;

    @Before
    public void init () {
        medianCounter = new MedianCounter();
    }

    @After
    public void tearDown() {
        medianCounter = null;
    }

    @Test
    public void controlTestMedianCounter() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/medianCounter1");

        int [] result1 = null;
        int [] result2 = null;
        for (int i = 0; i < array.length; i++) {
            medianCounter.addElement(array[i]);

            if (i == 2014) {
                result1 = medianCounter.getMedian();
            }

            if (i == 9875) {
                result2 = medianCounter.getMedian();
                break;
            }
        }
        assertNotNull(result1);
        assertNotNull(result2);
        assertArrayEquals(result1, new int[]{4905});
        assertArrayEquals(result2, new int[]{4994, 4995});
    }
}