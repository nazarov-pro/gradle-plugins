package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(
        {
                "minReadySeconds",
                "paused",
                "progressDeadlineSeconds",
                "replicas",
                "revisionHistoryLimit",
                "selector",
                "strategy",
                "podTemplate"
        }
)
public final class DeploymentSpecification<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, DeploymentSpecification<R>> {

    public DeploymentSpecification(R result, ChangeListener<DeploymentSpecification<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("minReadySeconds")
    private Integer minReadySeconds;
    @JsonProperty("paused")
    private Boolean paused;
    @JsonProperty("progressDeadlineSeconds")
    private Integer progressDeadlineSeconds;
    @JsonProperty("replicas")
    private Integer replicaCount;
    @JsonProperty("revisionHistoryLimit")
    private Integer revisionHistoryLimit;
    @JsonProperty("selector")
    private LabelSelector<DeploymentSpecification<R>> labelSelector;
    @JsonProperty("strategy")
    private DeploymentStrategy<DeploymentSpecification<R>> deploymentStrategy;
    @JsonProperty("podTemplate")
    private PodTemplate<DeploymentSpecification<R>> podTemplate;

    public DeploymentSpecification<R> replicas(Integer count) {
        this.replicaCount = count;
        return this;
    }

    public DeploymentSpecification<R> minReadySeconds(Integer minReadySeconds) {
        this.minReadySeconds = minReadySeconds;
        return this;
    }

    public DeploymentSpecification<R> progressDeadlineSeconds(Integer progressDeadlineSeconds) {
        this.progressDeadlineSeconds = progressDeadlineSeconds;
        return this;
    }

    public DeploymentSpecification<R> paused(Boolean paused) {
        this.paused = paused;
        return this;
    }

    public DeploymentSpecification<R> revisionHistoryLimit(Integer revisionHistoryLimit) {
        this.revisionHistoryLimit = revisionHistoryLimit;
        return this;
    }

    public DeploymentSpecification<R> selector(
            LabelSelector<DeploymentSpecification<R>> labelSelector
    ) {
        this.labelSelector = labelSelector;
        return this;
    }

    public LabelSelector<DeploymentSpecification<R>> selector() {
        return new LabelSelector<>(this, this::selector);
    }

    public DeploymentSpecification<R> deploymentStrategy(
            DeploymentStrategy<DeploymentSpecification<R>> deploymentStrategy
    ) {
        this.deploymentStrategy = deploymentStrategy;
        return this;
    }

    public DeploymentStrategy<DeploymentSpecification<R>> deploymentStrategy() {
        return new DeploymentStrategy<>(this, this::deploymentStrategy);
    }


    public PodTemplate<DeploymentSpecification<R>> podTemplate() {
        if(podTemplate != null) {
            return podTemplate;
        }
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
