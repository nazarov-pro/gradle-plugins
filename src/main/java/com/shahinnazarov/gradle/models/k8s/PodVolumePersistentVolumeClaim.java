package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "readOnly"
        }
)
public final class PodVolumePersistentVolumeClaim<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumePersistentVolumeClaim<R>> {

    public PodVolumePersistentVolumeClaim(R result, ChangeListener<PodVolumePersistentVolumeClaim<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("readOnly")
    private Boolean readOnly;

    public PodVolumePersistentVolumeClaim<R> name(String name) {
        this.name = name;
        return this;
    }

    public PodVolumePersistentVolumeClaim<R> readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public R buildPvc() {
        listener.apply(this);
        return result;
    }
}
