package parsleyj.NetSecLab.lab1.prngcipher;

import java.util.Iterator;

/**
 * An {@link Iterable} class that generates all the possible {@code byte} array sequences of a given length.
 * <br>
 * <br>
 * Created on 05/03/2019.
 */
public class KeySpace implements Iterable<byte[]> {
    private final int keyLength;

    public KeySpace(int keyLength) {
        this.keyLength = keyLength;
    }


    /**
     * Iterator class that generates all the possible {@code byte} array sequences of the given length.
     */
    public static class KeyIterator implements Iterator<byte[]> {
        private final int keyLength;
        private final long keySpaceSize;
        private long count = 0;
        public KeyIterator(int keyLength) {
            this.keyLength = keyLength;
            this.keySpaceSize = (long) Math.pow(2, keyLength * 8);
        }

        /**
         * Checks if there are more keys in the key space.
         * @return true if there are more keys in the key space, false otherwise
         */
        @Override
        public boolean hasNext() {
            return count<keySpaceSize;
        }

        /**
         * Gives the next key in the keyspace
         * @return
         */
        @Override
        public byte[] next() {
            byte[] key = new byte[keyLength];
            for (int i = 0; i < keyLength; i++) {
                key[i] = (byte) ((count >> (i * 8)) & 0xff);
            }
            count++;
            return key;
        }

        /**
         * DO NOT USE THIS
         */
        @Override
        public void remove() {
            //do not use
        }
    }

    /**
     * Returns a new instance of {@link KeyIterator}
     */
    @Override
    public Iterator<byte[]> iterator() {
        return new KeyIterator(keyLength);
    }
}
