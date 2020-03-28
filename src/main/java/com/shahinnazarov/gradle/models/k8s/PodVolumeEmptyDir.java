package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "medium",
                "sizeLimit"
        }
)
public final class PodVolumeEmptyDir<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeEmptyDir<R>> {

    public PodVolumeEmptyDir(R result, ChangeListener<PodVolumeEmptyDir<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("medium")
    private String medium;
    @JsonProperty("sizeLimit")
    private String sizeLimit;

    public PodVolumeEmptyDir<R> medium(String medium) {
        this.medium = medium;
        return this;
    }

    public PodVolumeEmptyDir<R> sizeLimit(String sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }

    public R buildEmptyDir() {
        listener.apply(this);
        return result;
    }
}
