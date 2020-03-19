package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "apiVersion", "kind", "metadata"
        }
)
public final class Namespace implements DefaultK8sResource<Namespace> {
    private Namespace() {

    }

    @JsonProperty("kind")
    private String kind = "Namespace";
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("apiVersion")
    private String apiVersion = "v1";

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

    public Namespace metadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    public Metadata<Namespace> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public static Namespace instance() {
        return new Namespace();
    }

    public Namespace buildNamespace() {
        return this;
    }
}
