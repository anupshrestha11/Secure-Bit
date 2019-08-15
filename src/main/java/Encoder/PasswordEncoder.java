package Encoder;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncoder {

    //hashing password with sha-512


    public String sha512PasswordEncoder(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        //digest method with input password returns array of byte
        byte[] message = messageDigest.digest(input.getBytes());
        //convert byte array into signum representation
        BigInteger bigInteger = new BigInteger(1, message);

        //convert to hex value
        String hashedText = bigInteger.toString(16);
        while (hashedText.length() < 32) {
            hashedText = "0" + hashedText;

        }

        return hashedText;


    }
}
