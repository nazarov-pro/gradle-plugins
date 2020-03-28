package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "preferredDuringSchedulingIgnoredDuringExecution",
                "requiredDuringSchedulingIgnoredDuringExecution"
        }
)
public final class PodAffinity<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodAffinity<R>> {

    public PodAffinity(R result, ChangeListener<PodAffinity<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("preferredDuringSchedulingIgnoredDuringExecution")
    private List<PreferredPodScheduling<PodAffinity<R>>> preferredPodScheduling;
    @JsonProperty("requiredDuringSchedulingIgnoredDuringExecution")
    private List<PodAffinityTerm<PodAffinity<R>>> requiredPodScheduling;

    public PreferredPodScheduling<PodAffinity<R>> addPreferredPodScheduling() {
        return new PreferredPodScheduling<>(this, this::preferredPodScheduling);
    }

    public PodAffinity<R> preferredPodScheduling(PreferredPodScheduling<PodAffinity<R>> preferredPodScheduling) {
        if (this.preferredPodScheduling == null) {
            this.preferredPodScheduling = new ArrayList<>();
        }
        this.preferredPodScheduling.add(preferredPodScheduling);
        return this;
    }

    public PodAffinityTerm<PodAffinity<R>> addRequiredPodScheduling() {
        return new PodAffinityTerm<>(this, this::requiredPodScheduling);
    }

    public PodAffinity<R> requiredPodScheduling(PodAffinityTerm<PodAffinity<R>> requiredPodScheduling) {
        if (this.requiredPodScheduling == null) {
            this.requiredPodScheduling = new ArrayList<>();
        }
        this.requiredPodScheduling.add(requiredPodScheduling);
        return this;
    }

    public R buildPodAffinity() {
        listener.apply(this);
        return result;
    }
}
