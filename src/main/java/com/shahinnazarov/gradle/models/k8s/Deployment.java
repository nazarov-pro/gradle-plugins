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
public final class Deployment implements DefaultK8sResource<Deployment> {
    private Deployment() {
    }

    @JsonProperty("kind")
    private String kind = "Deployment";
    @JsonProperty("apiVersion")
    private String apiVersion = "apps/v1";
    @JsonProperty("metadata")
    private Metadata<Deployment> metadata;
    @JsonProperty("spec")
    private DeploymentSpecification<Deployment> specification;

    public Metadata<Deployment> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public Deployment metadata(Metadata<Deployment> metadata) {
        this.metadata = metadata;
        return this;
    }

    public DeploymentSpecification<Deployment> spec() {
        return new DeploymentSpecification<>(this, this::spec);
    }

    public Deployment spec(DeploymentSpecification<Deployment> specification) {
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

    public Deployment buildDeployment() {
        return this;
    }

    public static Deployment instance() {
        return new Deployment();
    }
}
