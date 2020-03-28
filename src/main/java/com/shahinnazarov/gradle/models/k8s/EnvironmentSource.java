package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "configMapKeyRef",
                "fieldRef",
                "resourceFieldRef",
                "secretKeyRef",
        }
)
public final class EnvironmentSource<R extends DefaultK8sObject> extends AbstractK8sObject<R, EnvironmentSource<R>> {

    public EnvironmentSource(R result, ChangeListener<EnvironmentSource<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("configMapKeyRef")
    private VolumeKeyRef<EnvironmentSource<R>> configMapKeyRef;
    @JsonProperty("fieldRef")
    private FieldRef<EnvironmentSource<R>> fieldRef;
    @JsonProperty("resourceFieldRef")
    private ResourceFieldRef<EnvironmentSource<R>> resourceFieldRef;
    @JsonProperty("secretKeyRef")
    private VolumeKeyRef<EnvironmentSource<R>> secretKeyRef;


    public VolumeKeyRef<EnvironmentSource<R>> configMapKeyRef() {
        return new VolumeKeyRef<>(this, this::configMapKeyRef);
    }

    public EnvironmentSource<R> configMapKeyRef(VolumeKeyRef<EnvironmentSource<R>> configMapKeyRef) {
        this.configMapKeyRef = configMapKeyRef;
        return this;
    }

    public FieldRef<EnvironmentSource<R>> fieldRef() {
        return new FieldRef<>(this, this::fieldRef);
    }

    public EnvironmentSource<R> fieldRef(FieldRef<EnvironmentSource<R>> fieldRef) {
        this.fieldRef = fieldRef;
        return this;
    }


    public ResourceFieldRef<EnvironmentSource<R>> resourceFieldRef() {
        return new ResourceFieldRef<>(this, this::resourceFieldRef);
    }

    public EnvironmentSource<R> resourceFieldRef(ResourceFieldRef<EnvironmentSource<R>> resourceFieldRef) {
        this.resourceFieldRef = resourceFieldRef;
        return this;
    }


    public VolumeKeyRef<EnvironmentSource<R>> secretKeyRef() {
        return new VolumeKeyRef<>(this, this::secretKeyRef);
    }

    public EnvironmentSource<R> secretKeyRef(VolumeKeyRef<EnvironmentSource<R>> secretKeyRef) {
        this.secretKeyRef = secretKeyRef;
        return this;
    }


    public R buildEnvironmentSource() {
        listener.apply(this);
        return result;
    }
}
