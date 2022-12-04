package de.sombeyyy.utilities.security.crypto.rsa;

import de.sombeyyy.utilities.security.crypto.Crypto;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA implements Crypto {

    private static final String ALGORITHM = "RSA";
    private static final int DEFAULT_KEY_SIZE = 2048;
    private static final KeyFactory keyFactory;

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    static {
        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public RSA() {
        KeyPair keyPair = generateKeyPair(DEFAULT_KEY_SIZE);
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    public RSA(final String publicKey, final String privateKey) {
        this.publicKey = getPublicKeyFrom(publicKey);
        this.privateKey = getPrivateKeyFrom(privateKey);
    }

    public static PublicKey getPublicKeyFrom(final String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        KeySpec keySpec = new X509EncodedKeySpec(bytes);

        try {
            return keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static PrivateKey getPrivateKeyFrom(final String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);

        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static KeyPair generateKeyPair(final int keySize) {
        //TODO: check if value is between 512 and 64*1024 with Asserts
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(keySize);

            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public String encryptWithPublicKey(final String txt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);

            byte[] bytes = txt.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(bytes);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (GeneralSecurityException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public String encryptWithPrivateKey(final String txt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);

            byte[] bytes = txt.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(bytes);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (GeneralSecurityException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public String decryptWithPublicKey(final String cipherTxt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.publicKey);

            byte[] bytes = cipherTxt.getBytes(StandardCharsets.UTF_8);
            byte[] decoded = Base64.getDecoder().decode(bytes);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public String decryptWithPrivateKey(final String cipherTxt) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);

            byte[] bytes = cipherTxt.getBytes(StandardCharsets.UTF_8);
            byte[] decoded = Base64.getDecoder().decode(bytes);
            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(this.publicKey.getEncoded());
    }

    public String getPrivateKey() {
        return Base64.getEncoder().encodeToString(this.privateKey.getEncoded());
    }

    @Override
    public String encrypt(String text) {
        return encryptWithPrivateKey(text);
    }

    @Override
    public String decrypt(String cipherText) {
        return decryptWithPublicKey(cipherText);
    }

    @Override
    public String getKey() {
        return getPublicKey();
    }

}
