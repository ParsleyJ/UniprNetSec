package parsleyj.NetSecLab.lab1.prngcipher;

import java.security.NoSuchAlgorithmException;

/**
 * Created on 05/03/2019.
 */
public interface SymmetricCipher {
    byte[] encrypt(byte[] input, byte[] key) throws NoSuchAlgorithmException;
    byte[] decrypt(byte[] input, byte[] key) throws NoSuchAlgorithmException;
}
