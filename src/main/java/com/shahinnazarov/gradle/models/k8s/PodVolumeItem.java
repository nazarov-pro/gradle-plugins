package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "key",
                "mode",
                "path"
        }
)
public final class PodVolumeItem<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeItem<R>> {

    public PodVolumeItem(R result, ChangeListener<PodVolumeItem<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("key")
    private String key;
    @JsonProperty("mode")
    private Integer mode;
    @JsonProperty("path")
    private String path;

    public PodVolumeItem<R> key(String key) {
        this.key = key;
        return this;
    }

    public PodVolumeItem<R> mode(Integer mode) {
        this.mode = mode;
        return this;
    }

    public PodVolumeItem<R> path(String path) {
        this.path = path;
        return this;
    }

    public R buildHttHeader() {
        listener.apply(this);
        return result;
    }
}
