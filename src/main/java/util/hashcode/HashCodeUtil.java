package util.hashcode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCodeUtil {

    public static byte[] getHashCode(byte[] bytes, String algorithm)  {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
