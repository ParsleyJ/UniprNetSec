package parsleyj.NetSecLab.lab1.prngcipher;

/**
 * Created on 05/03/2019.
 */
public class KeyGenerator {
    private final int keyLength;
    private final long keySpaceSize;

    public KeyGenerator(int keyLength) {
        this.keyLength = keyLength;
        this.keySpaceSize = (long) Math.pow(2, keyLength * 8);
    }

    
}
