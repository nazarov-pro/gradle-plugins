package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "nodeAffinity",
                "podAffinity",
                "podAntiAffinity"
        }
)
public final class Affinity<R extends DefaultK8sObject> extends AbstractK8sObject<R, Affinity<R>> {

    public Affinity(R result, ChangeListener<Affinity<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("nodeAffinity")
    private NodeAffinity<Affinity<R>> nodeAffinity;
    @JsonProperty("podAffinity")
    private PodAffinity<Affinity<R>> podAffinity;
    @JsonProperty("podAntiAffinity")
    private PodAffinity<Affinity<R>> podAntiAffinity;

    public NodeAffinity<Affinity<R>> nodeAffinity() {
        return new NodeAffinity<>(this, this::nodeAffinity);
    }

    public Affinity<R> nodeAffinity(NodeAffinity<Affinity<R>> nodeAffinity) {
        this.nodeAffinity = nodeAffinity;
        return this;
    }

    public PodAffinity<Affinity<R>> podAffinity() {
        return new PodAffinity<>(this, this::podAffinity);
    }

    public Affinity<R> podAffinity(PodAffinity<Affinity<R>> podAffinity) {
        this.podAffinity = podAffinity;
        return this;
    }

    public PodAffinity<Affinity<R>> podAntiAffinity() {
        return new PodAffinity<>(this, this::podAntiAffinity);
    }

    public Affinity<R> podAntiAffinity(PodAffinity<Affinity<R>> podAntiAffinity) {
        this.podAntiAffinity = podAntiAffinity;
        return this;
    }

    public R buildAffinity() {
        listener.apply(this);
        return result;
    }
}
