package de.sombeyyy.utilities.security.crypto.aes;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AES256 extends AES {

    public AES256(final String key) {
        this(key, StandardCharsets.UTF_8);
    }

    public AES256(final String key, Charset charset) {
        super(key, 32, charset);
    }

}
