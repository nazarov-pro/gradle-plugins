package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "apiVersion",
                "kind",
                "metadata",
                "spec"
        }
)
public final class PersistentVolumeClaimTemplate<R extends DefaultK8sObject> extends AbstractK8sObject<R, PersistentVolumeClaimTemplate<R>> {


    @JsonProperty("kind")
    private String kind = "PersistentVolumeClaim";
    @JsonProperty("metadata")
    private Metadata<PersistentVolumeClaimTemplate<R>> metadata;
    @JsonProperty("apiVersion")
    private String apiVersion = "v1";
    @JsonProperty("spec")
    private PersistentVolumeClaimSpec<PersistentVolumeClaimTemplate<R>> spec;

    public PersistentVolumeClaimTemplate(R result, ChangeListener<PersistentVolumeClaimTemplate<R>> listener) {
        super(result, listener);
    }

    public PersistentVolumeClaimTemplate<R> metadata(Metadata<PersistentVolumeClaimTemplate<R>> metadata) {
        this.metadata = metadata;
        return this;
    }

    public PersistentVolumeClaimSpec<PersistentVolumeClaimTemplate<R>> spec() {
        return new PersistentVolumeClaimSpec<>(this, this::spec);
    }

    public PersistentVolumeClaimTemplate<R> spec(PersistentVolumeClaimSpec<PersistentVolumeClaimTemplate<R>> spec) {
        this.spec = spec;
        return this;
    }

    public Metadata<PersistentVolumeClaimTemplate<R>> metadata() {
        return new Metadata<PersistentVolumeClaimTemplate<R>>(this, this::metadata);
    }

    public R buildPvc() {
        listener.apply(this);
        return result;
    }

}
