package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "claimName",
                "readOnly"
        }
)
public final class PodVolumePersistentVolumeClaim<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumePersistentVolumeClaim<R>> {

    public PodVolumePersistentVolumeClaim(R result, ChangeListener<PodVolumePersistentVolumeClaim<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("claimName")
    private String claimName;
    @JsonProperty("readOnly")
    private Boolean readOnly;

    public PodVolumePersistentVolumeClaim<R> claimName(String claimName) {
        this.claimName = claimName;
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
