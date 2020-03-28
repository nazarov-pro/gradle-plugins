package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "path",
                "type"
        }
)
public final class PodVolumeHostPath<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeHostPath<R>> {

    public PodVolumeHostPath(R result, ChangeListener<PodVolumeHostPath<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("path")
    private String path;
    @JsonProperty("type")
    private String type;

    public PodVolumeHostPath<R> path(String path) {
        this.path = path;
        return this;
    }

    public PodVolumeHostPath<R> type(String type) {
        this.type = type;
        return this;
    }

    public R buildHostPath() {
        listener.apply(this);
        return result;
    }
}
