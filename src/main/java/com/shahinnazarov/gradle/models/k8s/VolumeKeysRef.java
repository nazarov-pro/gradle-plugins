package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "optional"
        }
)
public final class VolumeKeysRef<R extends DefaultK8sObject> extends AbstractK8sObject<R, VolumeKeysRef<R>> {

    public VolumeKeysRef(R result, ChangeListener<VolumeKeysRef<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("optional")
    private Boolean optional;

    public VolumeKeysRef<R> name(String name) {
        this.name = name;
        return this;
    }

    public VolumeKeysRef<R> optional(Boolean optional) {
        this.optional = optional;
        return this;
    }


    public R build() {
        listener.apply(this);
        return result;
    }
}
