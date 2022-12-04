package de.sombeyyy.utilities.security.crypto;

import org.jetbrains.annotations.NotNull;

public interface Crypto {

    /**
     * Returns the encrypted text
     *
     * @param text plaintext
     * @return encrypted text
     */
    String encrypt(String text);

    /**
     * Returns decrypted text
     *
     * @param cipherText encrypted text
     * @return plaintext
     */

    String decrypt(String cipherText);

    /**
     * Returns the given key
     *
     * @return key
     */

    String getKey();

}
