import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dog on 4/24/17.
 */
public class KaratsubaTest {

    Karatsuba karatsuba;

    @Before
    public void init() {
        karatsuba = new Karatsuba();
    }
    @After
    public void tearDown() {
        karatsuba = null;
    }

    @Test
    public void karatsubaMultiplication() throws Exception {
        assertEquals("2205647016448403" , karatsuba.karatsubaMultiplication("49823261", "44269423"));
        assertEquals("6700567850052179472481148730882040129649508491917840721086183384" , karatsuba.karatsubaMultiplication("71611955325935479159397713213124", "93567726499788166681348352945366"));
        assertEquals("32602566183268675582196165592691544162522778809155901895617284287276672563976841699892789718741377833554298336397153444191119684" , karatsuba.karatsubaMultiplication("8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327"));
    }

}