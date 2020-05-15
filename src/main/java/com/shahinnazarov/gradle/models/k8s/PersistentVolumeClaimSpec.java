package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "accessModes",
                "dataSource",
                "resources",
                "selector",
                "storageClassName",
                "volumeMode",
                "volumeName",
        }
)
public final class PersistentVolumeClaimSpec<R extends DefaultK8sObject> extends AbstractK8sObject<R, PersistentVolumeClaimSpec<R>> {

    public PersistentVolumeClaimSpec(R result, ChangeListener<PersistentVolumeClaimSpec<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("accessModes")
    private List<String> accessModes;
    @JsonProperty("dataSource")
    private DataSource<PersistentVolumeClaimSpec<R>> dataSource;
    @JsonProperty("resources")
    private Resources<PersistentVolumeClaimSpec<R>> resources;
    @JsonProperty("selector")
    private LabelSelector<PersistentVolumeClaimSpec<R>> selector;
    @JsonProperty("storageClassName")
    private String storageClassName;
    @JsonProperty("volumeMode")
    private String volumeMode;
    @JsonProperty("volumeName")
    private String volumeName;

    public PersistentVolumeClaimSpec<R> addAccessModes(String... accessModes) {
        if (this.accessModes == null) {
            this.accessModes = new ArrayList<>();
        }
        this.accessModes.addAll(Arrays.asList(accessModes));
        return this;
    }

    public PersistentVolumeClaimSpec<R> accessModes(List<String> accessModes) {
        this.accessModes = accessModes;
        return this;
    }

    public PersistentVolumeClaimSpec<R> dataSource(DataSource<PersistentVolumeClaimSpec<R>> dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public DataSource<PersistentVolumeClaimSpec<R>> dataSource() {
        return new DataSource<>(this, this::dataSource);
    }


    public PersistentVolumeClaimSpec<R> resources(Resources<PersistentVolumeClaimSpec<R>> resources) {
        this.resources = resources;
        return this;
    }

    public Resources<PersistentVolumeClaimSpec<R>> resources() {
        return new Resources<>(this, this::resources);
    }


    public PersistentVolumeClaimSpec<R> selector(
            LabelSelector<PersistentVolumeClaimSpec<R>> selector
    ) {
        this.selector = selector;
        return this;
    }

    public LabelSelector<PersistentVolumeClaimSpec<R>> selector() {
        return new LabelSelector<>(this, this::selector);
    }

    public PersistentVolumeClaimSpec<R> storageClassName(String storageClassName) {
        this.storageClassName = storageClassName;
        return this;
    }

    public PersistentVolumeClaimSpec<R> volumeMode(String volumeMode) {
        this.volumeMode = volumeMode;
        return this;
    }

    public PersistentVolumeClaimSpec<R> volumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    public R buildPvcSpec() {
        listener.apply(this);
        return result;
    }
}
