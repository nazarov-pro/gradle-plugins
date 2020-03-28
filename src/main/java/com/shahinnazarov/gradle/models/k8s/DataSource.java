package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "apiVersion",
                "kind",
                "name"
        }
)
public final class DataSource<R extends DefaultK8sObject> extends AbstractK8sObject<R, DataSource<R>> {
    public DataSource(R result, ChangeListener<DataSource<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("apiVersion")
    private String apiVersion;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("name")
    private String name;

    public DataSource<R> apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public DataSource<R> kind(String kind) {
        this.kind = kind;
        return this;
    }

    public DataSource<R> name(String name) {
        this.name = name;
        return this;
    }

    public DataSource<R> buildDatSource() {
        listener.apply(this);
        return this;
    }
}
