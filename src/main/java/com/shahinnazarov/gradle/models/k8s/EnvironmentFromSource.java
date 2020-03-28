package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "configMapRef",
                "prefix",
                "secretRef",
        }
)
public final class EnvironmentFromSource<R extends DefaultK8sObject> extends AbstractK8sObject<R, EnvironmentFromSource<R>> {

    public EnvironmentFromSource(R result, ChangeListener<EnvironmentFromSource<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("configMapRef")
    private VolumeKeysRef<EnvironmentFromSource<R>> configMapRef;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("secretRef")
    private VolumeKeysRef<EnvironmentFromSource<R>> secretRef;


    public EnvironmentFromSource<R> prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public VolumeKeysRef<EnvironmentFromSource<R>> configMapRef() {
        return new VolumeKeysRef<>(this, this::configMapRef);
    }

    public EnvironmentFromSource<R> configMapRef(VolumeKeysRef<EnvironmentFromSource<R>> configMapRef) {
        this.configMapRef = configMapRef;
        return this;
    }

    public VolumeKeysRef<EnvironmentFromSource<R>> secretRef() {
        return new VolumeKeysRef<>(this, this::secretRef);
    }

    public EnvironmentFromSource<R> secretRef(VolumeKeysRef<EnvironmentFromSource<R>> secretRef) {
        this.secretRef = secretRef;
        return this;
    }


    public R buildEnvironmentSource() {
        listener.apply(this);
        return result;
    }
}
