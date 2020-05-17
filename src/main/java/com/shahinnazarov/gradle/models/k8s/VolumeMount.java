package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "mountPath",
                "mountPropagation",
                "name",
                "readOnly",
                "subPath",
                "subPathExpr",
        }
)
public final class VolumeMount<R extends DefaultK8sObject> extends AbstractK8sObject<R, VolumeMount<R>> {

    public VolumeMount(R result, ChangeListener<VolumeMount<R>> listener) {
        super(result, listener);
    }


    @JsonProperty("mountPath")
    private String mountPath;
    @JsonProperty("mountPropagation")
    private String mountPropagation;
    @JsonProperty("name")
    private String name;
    @JsonProperty("readOnly")
    private Boolean readOnly;
    @JsonProperty("subPath")
    private String subPath;
    @JsonProperty("subPathExpr")
    private String subPathExpr;

    public VolumeMount<R> name(String name) {
        this.name = name;
        return this;
    }


    public VolumeMount<R> mountPath(String mountPath) {
        this.mountPath = mountPath;
        return this;
    }

    public VolumeMount<R> mountPropagation(String mountPropagation) {
        this.mountPropagation = mountPropagation;
        return this;
    }

    public VolumeMount<R> readOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public VolumeMount<R> subPath(String subPath) {
        this.subPath = subPath;
        return this;
    }

    public VolumeMount<R> subPathExpr(String subPathExpr) {
        this.subPathExpr = subPathExpr;
        return this;
    }

    public R buildVolumeMount() {
        listener.apply(this);
        return result;
    }
}
