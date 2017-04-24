import java.math.BigInteger;

/**
 * Created by dog on 4/23/17.
 */
public class Karatsuba {
    public String karatsubaMultiplication(String x, String y) {
        int xCapasity = x.length();
        int yCapasity = y.length();

        BigInteger a = new BigInteger(x.substring(0, xCapasity/2));
        BigInteger b = new BigInteger(x.substring(xCapasity/2));

        BigInteger c = new BigInteger(y.substring(0, yCapasity/2));
        BigInteger d = new BigInteger(y.substring(yCapasity/2));

        BigInteger ac = a.multiply(c);
        BigInteger bd = b.multiply(d);

        BigInteger z = (a.add(b)).multiply(c.add(d)).subtract(ac).subtract(bd); // a*d + b*c

        BigInteger result = ac.multiply(new BigInteger("10").pow(xCapasity)).add(z.multiply(new BigInteger("10").pow(xCapasity/2))).add(bd);
        return result.toString();
    }

}
