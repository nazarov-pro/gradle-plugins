package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "value",
                "valueFrom",
        }
)
public final class Environment<R extends DefaultK8sObject> extends AbstractK8sObject<R, Environment<R>> {

    public Environment(R result, ChangeListener<Environment<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonProperty("valueFrom")
    private EnvironmentSource<Environment<R>> valueFrom;

    public Environment<R> name(String name) {
        this.name = name;
        return this;
    }

    public Environment<R> value(String value) {
        this.value = value;
        return this;
    }

    public Environment<R> entry(String name, String value) {
        this.name = name;
        this.value = value;
        return this;
    }

    public Environment<R> valueFrom(EnvironmentSource<Environment<R>> valueFrom) {
        this.valueFrom = valueFrom;
        return this;
    }

    public EnvironmentSource<Environment<R>> valueFrom() {
        return new EnvironmentSource<>(this, this::valueFrom);
    }

    public R buildEnvironment() {
        listener.apply(this);
        return result;
    }
}
