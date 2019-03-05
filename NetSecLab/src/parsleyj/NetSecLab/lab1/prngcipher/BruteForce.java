package parsleyj.NetSecLab.lab1.prngcipher;

import parsleyj.NetSecLab.util.HexStrings;

import java.security.NoSuchAlgorithmException;

/**
 * Class to perform a brute force attack against a ciphertext. The plain text is known to
 * contain only lowercase letters and simple whitespace characters.
 *
 * <br>
 * <br>
 * Original exercise text:
 * <br>
 * <par>
 * 2.1. Using the above cipher try encrypt a text through a 24bit key (the PRNG seed) and mount a brute force attack
 * against the ciphertext, i.e. a brute force search of the key.
 * <br>
 * 2.2. In case the stream cipher is based on the SHA1PRNG, the key (PRNG seed) has size 24bit, and the plaintext
 * contains only lower-case characters and spaces, try to obtain the original plaintext corresponding to the
 * following ciphertext (represented in hexadecimal): 525680c7ab4b7a91261607ba1054fddedf57a0.
 * </par>
 * <br>
 * <br>
 * Created on 03/03/2019.
 *
 * @author Giuseppe Petrosino - giuseppe.petrosino@studenti.unipr.it
 */
public class BruteForce {

    private int key_len;
    private final SymmetricCipher cipher;

    /**
     * Initializes the brute force session with the necessary hints
     *
     * @param key_len the length of the secret key, in bytes
     * @param cipher  the cipher that will be used to try to decrypt the text
     */
    public BruteForce(int key_len, SymmetricCipher cipher) {
        this.key_len = key_len;
        this.cipher = cipher;
    }

    /**
     * Performs a brute force attack trying to discover the secret seed used to initialize the stream cipher.
     * The unknown plaintext is supposed to be a string of lower-case ascii characters plus spaces.
     *
     * @param ciphertext the ciphertext
     * @return the found key, or a 0-sized array in the case the key could not be found
     */
    public byte[] bruteForce(byte[] ciphertext) throws NoSuchAlgorithmException {
        for (byte[] key : new KeySpace(key_len)) {
            byte[] plaintext = cipher.decrypt(ciphertext, key);
            boolean match = true;

            for (byte c : plaintext) {
                //the plaintext is assumed to contain only lowercase letters and spaces
                match = (c >= 'a' && c <= 'z') || c == ' ';
                if (!match) break;
            }

            if (match) {
                return key;
            }
        }
        return new byte[0];

    }


    /**
     * Test main to check the bruteforce attack
     *
     * @param argv COMMAND LINE ARGUMENTS IGNORED
     * @throws NoSuchAlgorithmException if the SHA1PRNG algorithm is not available in the environment
     */
    public static void main(String[] argv) throws NoSuchAlgorithmException {

        byte[] seed = "sss".getBytes();
        byte[] ciphertext = new PRNGStreamCipher().encrypt("hello world".getBytes(), seed);
        System.out.println("ciphertext: " + HexStrings.toHexString(ciphertext));

        long start = System.currentTimeMillis();
        // brute force attack
        final byte[] foundKey = new BruteForce(seed.length, new PRNGStreamCipher()).bruteForce(ciphertext);
        if (foundKey.length == 0) {
            System.out.println("Not found");
        } else {
            byte[] plaintext = new PRNGStreamCipher().decrypt(ciphertext, foundKey);
            System.out.println("plaintext: \"" + new String(plaintext) + "\" (key:" + " hex=" + HexStrings.toHexString(foundKey) + ", str=" + new String(foundKey) + ")");
        }

        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
