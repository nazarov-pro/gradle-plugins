package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "apiVersion",
                "fieldPath",
        }
)
public final class FieldRef<R extends DefaultK8sObject> extends AbstractK8sObject<R, FieldRef<R>> {

    public FieldRef(R result, ChangeListener<FieldRef<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("apiVersion")
    private String apiVersion;
    @JsonProperty("fieldPath")
    private String fieldPath;

    public FieldRef<R> apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public FieldRef<R> fieldPath(String fieldPath) {
        this.fieldPath = fieldPath;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
