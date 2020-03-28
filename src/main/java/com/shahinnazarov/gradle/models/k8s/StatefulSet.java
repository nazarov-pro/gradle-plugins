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
public final class StatefulSet implements DefaultK8sResource<StatefulSet> {
    private StatefulSet() {
    }

    @JsonProperty("kind")
    private String kind = "Deployment";
    @JsonProperty("apiVersion")
    private String apiVersion = "apps/v1";
    @JsonProperty("metadata")
    private Metadata<StatefulSet> metadata;
    @JsonProperty("spec")
    private StatefulSetSpec<StatefulSet> specification;

    public Metadata<StatefulSet> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public StatefulSet metadata(Metadata<StatefulSet> metadata) {
        this.metadata = metadata;
        return this;
    }

    public StatefulSetSpec<StatefulSet> spec() {
        return new StatefulSetSpec<>(this, this::spec);
    }

    public StatefulSet spec(StatefulSetSpec<StatefulSet> specification) {
        this.specification = specification;
        return this;
    }

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

    public StatefulSet buildDeployment() {
        return this;
    }

    public static StatefulSet instance() {
        return new StatefulSet();
    }
}
