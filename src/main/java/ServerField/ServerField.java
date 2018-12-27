package ServerField;

import java.math.BigInteger;

class ServerField {


    static BigInteger encryption(BigInteger n, BigInteger d, String msg){
        BigInteger string = new BigInteger(msg.getBytes());
        string = string.modPow(d,n);
        System.out.println("Encoded message: "+string);
        return string;
    }
    static String decryption(BigInteger n, BigInteger d, BigInteger msg){
        BigInteger decode = msg.modPow(d,n);
        return new String(decode.toByteArray());
    }
}
