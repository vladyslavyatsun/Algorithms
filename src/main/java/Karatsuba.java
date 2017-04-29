import java.math.BigInteger;

/**
 * Created by dog on 4/23/17.
 */
public class Karatsuba {
    public String multiplication(String x, String y) {
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

    public String multiplicationRecursive(String x, String y) {
        if (x.length() == 1 && y.length() == 1) {
            return Byte.parseByte(x) * Byte.parseByte(y) + "";
        }

        int capasity = x.length() > y.length() ? x.length() : y.length();
        if (capasity % 2 == 1) capasity++;

        while (capasity != x.length())
        {
            x = "0"+x;
        }

        while (capasity != y.length())
        {
            y = "0"+y;
        }

        String a = x.substring(0, capasity / 2);
        String b = x.substring(capasity / 2);

        String c = y.substring(0, capasity / 2);
        String d = y.substring(capasity /2);

        BigInteger ac = new BigInteger(multiplicationRecursive(a, c));
        BigInteger bd = new BigInteger(multiplicationRecursive(b, d));

        BigInteger apb = new BigInteger(a).add(new BigInteger(b));
        BigInteger cpd = new BigInteger(c).add(new BigInteger(d));

        BigInteger z = new BigInteger(multiplicationRecursive(apb.toString(), cpd.toString())).subtract(ac).subtract(bd);

        BigInteger result = ac.multiply(new BigInteger("10").pow(capasity)).add(z.multiply(new BigInteger("10").pow(capasity/2))).add(bd);
        return result.toString();
    }
}
