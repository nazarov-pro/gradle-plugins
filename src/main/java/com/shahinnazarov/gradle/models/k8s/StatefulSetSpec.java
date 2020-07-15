package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder(
        {
                "podManagementPolicy",
                "replicas",
                "revisionHistoryLimit",
                "selector",
                "serviceName",
                "template",
                "updateStrategy",
                "volumeClaimTemplates",
        }
)
public final class StatefulSetSpec<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, StatefulSetSpec<R>> {

    public StatefulSetSpec(R result, ChangeListener<StatefulSetSpec<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("podManagementPolicy")
    private String podManagementPolicy;
    @JsonProperty("replicas")
    private Integer replicaCount;
    @JsonProperty("revisionHistoryLimit")
    private Integer revisionHistoryLimit;
    @JsonProperty("selector")
    private LabelSelector<StatefulSetSpec<R>> labelSelector;
    @JsonProperty("serviceName")
    private String serviceName;
    @JsonProperty("template")
    private PodTemplate<StatefulSetSpec<R>> podTemplate;
    @JsonProperty("updateStrategy")
    private StatefulSetUpdateStrategy<StatefulSetSpec<R>> updateStrategy;
    @JsonProperty("volumeClaimTemplates")
    private List<PersistentVolumeClaimTemplate<StatefulSetSpec<R>>> volumeClaimTemplates;

    public StatefulSetSpec<R> replicas(Integer count) {
        this.replicaCount = count;
        return this;
    }


    public StatefulSetSpec<R> revisionHistoryLimit(Integer revisionHistoryLimit) {
        this.revisionHistoryLimit = revisionHistoryLimit;
        return this;
    }


    public StatefulSetSpec<R> serviceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public StatefulSetSpec<R> selector(
            LabelSelector<StatefulSetSpec<R>> labelSelector
    ) {
        this.labelSelector = labelSelector;
        return this;
    }

    public LabelSelector<StatefulSetSpec<R>> selector() {
        if(labelSelector != null) {
            return labelSelector;
        }
        return new LabelSelector<>(this, this::selector);
    }

    public StatefulSetSpec<R> updateStrategy(
            StatefulSetUpdateStrategy<StatefulSetSpec<R>> updateStrategy
    ) {
        this.updateStrategy = updateStrategy;
        return this;
    }

    public StatefulSetUpdateStrategy<StatefulSetSpec<R>> updateStrategy() {
        if(updateStrategy != null) {
            return updateStrategy;
        }
        return new StatefulSetUpdateStrategy<>(this, this::updateStrategy);
    }


    public PodTemplate<StatefulSetSpec<R>> podTemplate() {
        if(podTemplate != null) {
            return podTemplate;
        }
        return new PodTemplate<>(this, this::podTemplate);
    }

    public StatefulSetSpec<R> podTemplate(
            PodTemplate<StatefulSetSpec<R>> podTemplate) {
        this.podTemplate = podTemplate;
        return this;
    }

    public PersistentVolumeClaimTemplate<StatefulSetSpec<R>> addVolumeClaimTemplate() {
        return new PersistentVolumeClaimTemplate<>(this, this::addVolumeClaimTemplate);
    }

    public StatefulSetSpec<R> addVolumeClaimTemplate(PersistentVolumeClaimTemplate<StatefulSetSpec<R>> volumeClaimTemplate) {
        if (this.volumeClaimTemplates == null) {
            this.volumeClaimTemplates = new ArrayList<>();
        }
        this.volumeClaimTemplates.add(volumeClaimTemplate);
        return this;
    }

    public R buildSpec() {
        super.listener.apply(this);
        return super.result;
    }
}
