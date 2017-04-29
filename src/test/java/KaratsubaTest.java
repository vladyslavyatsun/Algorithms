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
        assertEquals("2205647016448403" , karatsuba.multiplication("49823261", "44269423"));
        assertEquals("6700567850052179472481148730882040129649508491917840721086183384" , karatsuba.multiplication("71611955325935479159397713213124", "93567726499788166681348352945366"));
        assertEquals("32602566183268675582196165592691544162522778809155901895617284287276672563976841699892789718741377833554298336397153444191119684" , karatsuba.multiplication("8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327"));
    }

    @Test
    public void karatsubaMultiplicationRecursive() throws Exception {
        assertEquals("7006652" , karatsuba.multiplicationRecursive("1234", "5678"));
        assertEquals("11989460275519080564894036768322865785999566885539505969749975204962718118914971586072960191064507745920086993438529097266122668" , karatsuba.multiplicationRecursive("1685287499328328297814655639278583667919355849391453456921116729", "7114192848577754587969744626558571536728983167954552999895348492"));
        assertEquals("32602566183268675582196165592691544162522778809155901895617284287276672563976841699892789718741377833554298336397153444191119684" , karatsuba.multiplicationRecursive("8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327"));
        assertEquals("25478534007255378799894857247961445544397925869179138904636157575535921570058983065006369481295619500406669960288667484926076424" , karatsuba.multiplicationRecursive("8711129198194917883527844183686122989894424943636426448417394566", "2924825637132661199799711722273977411715641477832758942277358764"));
    }
}