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


    /*private static boolean isPrime(int n) {
        for (int i = 2; i <= sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long computeE(long d, long f){
        long e = randomPrime() + f;
        while (e*d%f!=1){
            e-=1;
        }
        return e;
    }

    private static long mutuallySimple(long f){
        long d = randomPrime()%f;
        for(int i = 2; i < d; i++){
            if((f%i==0)&&(d%i==0)){
                d = d-1;
            }
        }
        return d;
    }

    private static int randomPrime() {
        int a = 20000;
        int b = 2100000;
        int i;
        Random random = new Random(System.currentTimeMillis());
        int res = random.nextInt(b - a) + a;
        for (i = res; i < 2 * res; i++) {
            if (isPrime(i) && isPrime((i - 1) / 2)) {
                return i;
            }
        }
        return i;
    }

    public static long powMod(long base, long exp, long mod) {
        long d = 1;
        while (exp > 1) {
            if (exp % 2 == 0) {
                base = base * base % mod;
                exp /= 2;
            } else {
                exp -= 1;
                d = d * base % mod;
            }
        }
        return d * base % mod;
    }*/
}
