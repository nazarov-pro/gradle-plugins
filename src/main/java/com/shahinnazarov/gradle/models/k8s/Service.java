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
public final class Service implements DefaultK8sResource<Service> {
    private Service() {

    }

    @JsonProperty("kind")
    private String kind = "Service";
    @JsonProperty("metadata")
    private Metadata<Service> metadata;
    @JsonProperty("apiVersion")
    private String apiVersion = "v1";
    @JsonProperty("spec")
    private ServiceSpec<Service> spec;

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

    public Service metadata(Metadata<Service> metadata) {
        this.metadata = metadata;
        return this;
    }

    public ServiceSpec<Service> spec() {
        return new ServiceSpec<>(this, this::spec);
    }

    public Service spec(ServiceSpec<Service> spec) {
        this.spec = spec;
        return this;
    }

    public Metadata<Service> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public static Service instance() {
        return new Service();
    }

    public Service buildService() {
        return this;
    }

}
