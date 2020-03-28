package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder(
        {
                "defaultMode",
                "items",
                "optional",
                "secretName",
        }
)
public final class PodVolumeSecret<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeSecret<R>> {

    public PodVolumeSecret(R result, ChangeListener<PodVolumeSecret<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("defaultMode")
    private Integer defaultMode;
    @JsonProperty("items")
    private List<PodVolumeItem<PodVolumeSecret<R>>> items;
    @JsonProperty("secretName")
    private String secretName;
    @JsonProperty("optional")
    private Boolean optional;

    public PodVolumeSecret<R> defaultMode(Integer defaultMode) {
        this.defaultMode = defaultMode;
        return this;
    }

    public PodVolumeSecret<R> secretName(String secretName) {
        this.secretName = secretName;
        return this;
    }

    public PodVolumeSecret<R> optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    public PodVolumeItem<PodVolumeSecret<R>> addItem() {
        return new PodVolumeItem<>(this, this::addItem);
    }

    public PodVolumeSecret<R> addItem(PodVolumeItem<PodVolumeSecret<R>> item) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.add(item);
        return this;
    }

    public R buildConfigMap() {
        listener.apply(this);
        return result;
    }
}
