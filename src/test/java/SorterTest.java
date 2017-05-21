import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by dog on 5/10/17.
 */
public class SorterTest {
    public Sorter sorter;

    @Before
    public void init () {
        sorter = new Sorter();
    }

    @After
    public void tearDown() {
        Sorter.count = 0;
        sorter = null;
    }

    @Test
    public void testQuickSort1() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/testSort1");
        sorter.quickSort(array, 0, array.length-1, Sorter.PartitionType.LAST_BASIC_ELEMENT);
        assertEquals(656, Sorter.count);
    }

    @Test
    public void testQuickSort2() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/testSort2");
        sorter.quickSort(array, 0, array.length-1, Sorter.PartitionType.MEDIANA_BASIC_ELEMENT);
        assertEquals(9290, Sorter.count);
    }

    @Test
    public void controlTestQuickSort1() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/testSort3");
        sorter.quickSort(array, 0, array.length-1, Sorter.PartitionType.LAST_BASIC_ELEMENT);
        assertEquals(150262, Sorter.count);
    }

    @Test
    public void controlTestQuickSort2() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/testSort3");
        sorter.quickSort(array, 0, array.length-1, Sorter.PartitionType.FIRST_BASIC_ELEMENT);
        assertEquals(159864, Sorter.count);
    }

    @Test
    public void controlTestQuickSort3() throws Exception {
        int [] array = Utils.readIntegerArray("src/test/data/testSort3");
        sorter.quickSort(array, 0, array.length-1, Sorter.PartitionType.MEDIANA_BASIC_ELEMENT);
        assertEquals(130957, Sorter.count);
    }

    @Test
    public void testRadixSort() throws Exception {
        String [] array = Utils.readStringArray("src/test/data/testRadixSort");
        String [] result = sorter.radixSort(array);
        assertEquals("dni", result[123]);
        assertEquals("ubu", result[765]);
        assertEquals("vut", result[841]);
    }
}