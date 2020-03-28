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
public final class NodeAffinity<R extends DefaultK8sObject> extends AbstractK8sObject<R, NodeAffinity<R>> {

    public NodeAffinity(R result, ChangeListener<NodeAffinity<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("preferredDuringSchedulingIgnoredDuringExecution")
    private List<PreferredNodeScheduling<NodeAffinity<R>>> preferredNodeScheduling;
    @JsonProperty("requiredDuringSchedulingIgnoredDuringExecution")
    private RequiredNodeScheduling<NodeAffinity<R>> requiredNodeScheduling;

    public PreferredNodeScheduling<NodeAffinity<R>> addPreferredNodeScheduling() {
        return new PreferredNodeScheduling<>(this, this::preferredNodeScheduling);
    }

    public NodeAffinity<R> preferredNodeScheduling(PreferredNodeScheduling<NodeAffinity<R>> preferredNodeScheduling) {
        if(this.preferredNodeScheduling == null) {
            this.preferredNodeScheduling = new ArrayList<>();
        }
        this.preferredNodeScheduling.add(preferredNodeScheduling);
        return this;
    }

    public RequiredNodeScheduling<NodeAffinity<R>> requiredNodeScheduling() {
        return new RequiredNodeScheduling<>(this, this::requiredNodeScheduling);
    }

    public NodeAffinity<R> requiredNodeScheduling(RequiredNodeScheduling<NodeAffinity<R>> requiredNodeScheduling) {
        this.requiredNodeScheduling = requiredNodeScheduling;
        return this;
    }

    public R buildNodeAffinity() {
        listener.apply(this);
        return result;
    }
}
