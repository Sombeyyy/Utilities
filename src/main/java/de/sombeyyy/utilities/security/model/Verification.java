package de.sombeyyy.utilities.security.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Verification {

    private final Object credentials;
    private final Duration duration;
    private final LocalDateTime created = LocalDateTime.now();

    public Verification(final Object credentials, Duration duration) {
        //TODO: check parameters
        this.credentials = credentials;
        this.duration = duration;
    }

    public boolean verify(final Object credentials) {
        if(!Objects.deepEquals(this.credentials, credentials)) return false;

        return !isExpired();
    }

    public boolean isExpired() {
        Duration duration = Duration.between(this.created, LocalDateTime.now());
        if(duration.isNegative()) return true;

        BigDecimal thatDuration = new BigDecimal(duration.getSeconds() + "." + duration.getNano());
        BigDecimal thisDuration = new BigDecimal(this.duration.getSeconds() + "." + this.duration.getNano());

        return thatDuration.compareTo(thisDuration) > 0;
    }

    public Object getCredentials() {
        return credentials;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
