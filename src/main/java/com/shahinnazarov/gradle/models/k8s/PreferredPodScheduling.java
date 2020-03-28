package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "podAffinityTerm",
                "weight",
        }
)
public final class PreferredPodScheduling<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, PreferredPodScheduling<R>> {

    public PreferredPodScheduling(R result, ChangeListener<PreferredPodScheduling<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("podAffinityTerm")
    private PodAffinityTerm<PreferredPodScheduling<R>> podAffinityTerm;
    @JsonProperty("weight")
    private Integer weight;

    public PreferredPodScheduling<R> weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public PodAffinityTerm<PreferredPodScheduling<R>> podAffinityTerm() {
        return new PodAffinityTerm<>(this, this::podAffinityTerm);
    }

    public PreferredPodScheduling<R> podAffinityTerm(PodAffinityTerm<PreferredPodScheduling<R>> podAffinityTerm) {
        this.podAffinityTerm = podAffinityTerm;
        return this;
    }

    public R buildPreferredScheduling() {
        listener.apply(this);
        return result;
    }
}
