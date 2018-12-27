package SafeContext;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private static BigInteger n;
    private static BigInteger d;
    private static BigInteger e;


    public static void init() {
        BigInteger p = new BigInteger(512,32,new Random(System.currentTimeMillis()));
        System.out.println("P = "+p.toString(10));
        BigInteger q = new BigInteger(512,32,new Random(System.currentTimeMillis()));
        System.out.println("Q = "+q.toString(10));
        n = p.multiply(q);
        System.out.println("N = "+n.toString(10));
        BigInteger f = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
        System.out.println("F = "+f.toString(10));
        d = BigInteger.valueOf(997);//mutuallySimple(f);
        System.out.println("D = "+d.toString(10));
        e = d.modInverse(f);
        System.out.println("E = "+e.toString(10));
    }

    public static BigInteger getN() {
        return n;
    }

    public static BigInteger getE() {
        return e;
    }

    public static BigInteger getD() {
        return d;
    }

}
