package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "key",
                "name",
                "optional"
        }
)
public final class VolumeKeyRef<R extends DefaultK8sObject> extends AbstractK8sObject<R, VolumeKeyRef<R>> {

    public VolumeKeyRef(R result, ChangeListener<VolumeKeyRef<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("optional")
    private Boolean optional;

    public VolumeKeyRef<R> key(String key) {
        this.key = key;
        return this;
    }

    public VolumeKeyRef<R> name(String name) {
        this.name = name;
        return this;
    }

    public VolumeKeyRef<R> optional(Boolean optional) {
        this.optional = optional;
        return this;
    }


    public R build() {
        listener.apply(this);
        return result;
    }
}
