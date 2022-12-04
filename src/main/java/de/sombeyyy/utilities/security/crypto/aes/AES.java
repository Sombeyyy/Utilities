package de.sombeyyy.utilities.security.crypto.aes;

import de.sombeyyy.utilities.security.crypto.Crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Base64;

public class AES implements Crypto {

    private static final String ALGORITHM = "AES";
    private final Key key;
    private final Charset charset;

    protected AES(final String key,final int length, final Charset charset) {
        //TODO: check right values with asserts
        byte[] bytes = key.getBytes(charset);

        this.key = new SecretKeySpec(bytes, ALGORITHM);
        this.charset = charset;
    }

    @Override
    public String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.key);

            byte[] encrypted = cipher.doFinal(text.getBytes(this.charset));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.key);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            return new String(decrypted, this.charset);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    @Override
    public String getKey() {
        return new String(this.key.getEncoded(), this.charset);
    }

}
