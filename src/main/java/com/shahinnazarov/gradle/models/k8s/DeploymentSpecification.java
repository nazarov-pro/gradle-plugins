package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "replicas",
                "selector",
                "podTemplate"
        }
)
public final class DeploymentSpecification<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, DeploymentSpecification<R>> {

    public DeploymentSpecification(R result, ChangeListener<DeploymentSpecification<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("replicas")
    private Integer replicaCount;
    @JsonProperty("selector")
    private ResourceSelectorByLabels<DeploymentSpecification<R>> resourceSelectorByLabels;
    @JsonProperty("podTemplate")
    private PodTemplate<DeploymentSpecification<R>> podTemplate;

    public DeploymentSpecification<R> replicas(Integer count) {
        this.replicaCount = count;
        return this;
    }

    public ResourceSelectorByLabels<DeploymentSpecification<R>> selector() {
        return new ResourceSelectorByLabels<>(this, this::selector);
    }

    public DeploymentSpecification<R> selector(
            ResourceSelectorByLabels<DeploymentSpecification<R>> resourceSelectorByLabels
    ) {
        this.resourceSelectorByLabels = resourceSelectorByLabels;
        return this;
    }

    public PodTemplate<DeploymentSpecification<R>> podTemplate() {
        return new PodTemplate<>(this, this::podTemplate);
    }

    public DeploymentSpecification<R> podTemplate(
            PodTemplate<DeploymentSpecification<R>> podTemplate) {
        this.podTemplate = podTemplate;
        return this;
    }

    public R buildSpecification() {
        super.listener.apply(this);
        return super.result;
    }
}
