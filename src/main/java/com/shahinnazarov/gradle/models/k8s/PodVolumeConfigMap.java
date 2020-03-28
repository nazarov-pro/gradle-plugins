package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "defaultMode",
                "items",
                "name",
                "optional",
        }
)
public final class PodVolumeConfigMap<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeConfigMap<R>> {

    public PodVolumeConfigMap(R result, ChangeListener<PodVolumeConfigMap<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("defaultMode")
    private Integer defaultMode;
    @JsonProperty("items")
    private List<PodVolumeItem<PodVolumeConfigMap<R>>> items;
    @JsonProperty("name")
    private String name;
    @JsonProperty("optional")
    private Boolean optional;

    public PodVolumeConfigMap<R> defaultMode(Integer defaultMode) {
        this.defaultMode = defaultMode;
        return this;
    }

    public PodVolumeConfigMap<R> name(String name) {
        this.name = name;
        return this;
    }

    public PodVolumeConfigMap<R> optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    public PodVolumeItem<PodVolumeConfigMap<R>> addItem() {
        return new PodVolumeItem<>(this, this::addItem);
    }

    public PodVolumeConfigMap<R> addItem(PodVolumeItem<PodVolumeConfigMap<R>> item) {
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
