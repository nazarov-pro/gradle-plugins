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
public final class PersistentVolumeClaim implements DefaultK8sResource<PersistentVolumeClaim> {
    private PersistentVolumeClaim() {

    }

    @JsonProperty("kind")
    private String kind = "PersistentVolumeClaim";
    @JsonProperty("metadata")
    private Metadata<PersistentVolumeClaim> metadata;
    @JsonProperty("apiVersion")
    private String apiVersion = "v1";
    @JsonProperty("spec")
    private PersistentVolumeClaimSpec<PersistentVolumeClaim> spec;

    @Override
    public String getApiVersion() {
        return apiVersion;
    }

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    public PersistentVolumeClaim metadata(Metadata<PersistentVolumeClaim> metadata) {
        this.metadata = metadata;
        return this;
    }

    public PersistentVolumeClaimSpec<PersistentVolumeClaim> spec() {
        return new PersistentVolumeClaimSpec<>(this, this::spec);
    }

    public PersistentVolumeClaim spec(PersistentVolumeClaimSpec<PersistentVolumeClaim> spec) {
        this.spec = spec;
        return this;
    }

    public Metadata<PersistentVolumeClaim> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public static PersistentVolumeClaim instance() {
        return new PersistentVolumeClaim();
    }

    public PersistentVolumeClaim buildService() {
        return this;
    }

}
