package de.sombeyyy.utilities.assertion;

import de.sombeyyy.utilities.data.ArrayUtils;
import de.sombeyyy.utilities.string.StringUtils;

import java.util.*;
import java.util.function.Function;

public abstract class Descriptor<T extends Descriptor<T>> {

    protected final T self;
    private String description;
    private Object[] arguments;
    private Function<String, ? extends RuntimeException> exception = IllegalArgumentException::new;
    private List<Map.Entry<String, String>> desciptionVariables;

    protected Descriptor() {
        this.self = (T) this;
    }

    protected Descriptor(final Descriptor<?> descriptor) {
        this();

        this.description = descriptor.description;
        this.arguments = descriptor.arguments;
        this.exception = descriptor.exception;
    }

    public final T describedAs(final String description, Object... args) {
        this.description = Objects.requireNonNull(description, "Descriptor.description cannot be null");
        this.arguments = Objects.requireNonNull(args, "Descriptor.arguments cannot be null");
        return this.self;
    }

    public final T thrownBy(final Function<String, ? extends RuntimeException> function) {
        this.exception = Objects.requireNonNull(function, "Descriptor.exception cannot be null");
        return this.self;
    }

    protected final void setDefaultDescription(final String description, final Object... args) {
        if(!StringUtils.isNullOrEmpty(this.description)) return;
        describedAs(description, args);
    }

    protected final void setDescriptionVariables(final Map.Entry<String, Object>... variables) {
        List<Map.Entry<String, String>> descriptionVariables = new ArrayList<>();
        Arrays.stream(variables).forEach(var -> {
            String value = ArrayUtils.toString(var.getValue());
            Map.Entry<String, String> entry = new AbstractMap.SimpleImmutableEntry<>(var.getKey(), value);
            descriptionVariables.add(entry);
        });
        this.desciptionVariables = Collections.unmodifiableList(descriptionVariables);
    }

    protected final RuntimeException getException() {
        String message = getFailureMessage();

        //TODO: To be continued
    }

}
