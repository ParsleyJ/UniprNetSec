package parsleyj.NetSecLab.lab1.prngcipher;

/**
 * <br>
 * <br>
 *     Original exercise text:
 * <br>
 * <par>
 *     2.1. Using the above cipher try encrypt a text through a 24bit key (the PRNG seed) and mount a brute force attack
 *     against the ciphertext, i.e. a brute force search of the key.
 * <br>
 *     2.2. In case the stream cipher is based on the SHA1PRNG, the key (PRNG seed) has size 24bit, and the plaintext
 *     contains only lower-case characters and spaces, try to obtain the original plaintext corresponding to the
 *     following ciphertext (represented in hexadecimal): 525680c7ab4b7a91261607ba1054fddedf57a0.
 * </par>
 * <br>
 * <br>
 * Created on 03/03/2019.
 *
 * @author Giuseppe Petrosino - giuseppe.petrosino@studenti.unipr.it
 */
public class PRNGBruteForce {

    public static void main(String[] argv){
        PRNGBruteForce prngBruteForce = new PRNGBruteForce();
    }
}
