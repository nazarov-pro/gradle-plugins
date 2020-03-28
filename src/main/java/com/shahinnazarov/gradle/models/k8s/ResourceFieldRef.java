package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "containerName",
                "divisor",
                "resource",
        }
)
public class ResourceFieldRef<R extends DefaultK8sObject> extends AbstractK8sObject<R, ResourceFieldRef<R>> {

    public ResourceFieldRef(R result, ChangeListener<ResourceFieldRef<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("containerName")
    private String containerName;
    @JsonProperty("divisor")
    private String divisor;
    @JsonProperty("resource")
    private String resource;

    public ResourceFieldRef<R> containerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    public ResourceFieldRef<R> divisor(String divisor) {
        this.divisor = divisor;
        return this;
    }

    public ResourceFieldRef<R> resource(String resource) {
        this.resource = resource;
        return this;
    }

    public R buildResourceFieldRef() {
        listener.apply(this);
        return result;
    }
}
