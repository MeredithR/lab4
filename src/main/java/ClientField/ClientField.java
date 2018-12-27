package ClientField;

import java.math.BigInteger;

class ClientField {
    static BigInteger encryption(BigInteger n, BigInteger e, String msg) {
        BigInteger string = new BigInteger(msg.getBytes());
        string = string.modPow(e, n);
        System.out.println("Encoded message: " + string);
        return string;
    }

    static String decryption(BigInteger n, BigInteger e, BigInteger msg) {
        BigInteger decode = msg.modPow(e, n);
        return new String(decode.toByteArray());
    }
}
