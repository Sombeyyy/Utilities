package de.sombeyyy.utilities.security.crypto.aes;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AES192 extends AES {

    public AES192(final String key) {
        this(key, StandardCharsets.UTF_8);
    }

    public AES192(final String key, final Charset charset) {
        super(key, 24, charset);
    }

}
