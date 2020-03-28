package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "labelSelector",
                "namespaces",
                "topologyKey"
        }
)
public final class PodAffinityTerm<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodAffinityTerm<R>> {

    public PodAffinityTerm(R result, ChangeListener<PodAffinityTerm<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("labelSelector")
    private LabelSelector<PodAffinityTerm<R>> labelSelector;
    @JsonProperty("namespaces")
    private List<String> namespaces;
    @JsonProperty("topologyKey")
    private String topologyKey;

    public LabelSelector<PodAffinityTerm<R>> labelSelector() {
        return new LabelSelector<>(this, this::labelSelector);
    }

    public PodAffinityTerm<R> labelSelector(LabelSelector<PodAffinityTerm<R>> labelSelector) {
        this.labelSelector = labelSelector;
        return this;
    }

    public PodAffinityTerm<R> topologyKey(String topologyKey) {
        this.topologyKey = topologyKey;
        return this;
    }

    public PodAffinityTerm<R> addNamespaces(String... namespaces) {
        if (this.namespaces == null) {
            this.namespaces = new ArrayList<String>();
        }
        this.namespaces.addAll(Arrays.asList(namespaces));
        return this;
    }


    public R buildPodAffinity() {
        listener.apply(this);
        return result;
    }
}
