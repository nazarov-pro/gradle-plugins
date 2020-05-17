package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "metadata", "spec"
        }
)
public final class PodTemplate<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, PodTemplate<R>> {

    public PodTemplate(R result, ChangeListener<PodTemplate<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("metadata")
    private Metadata<PodTemplate<R>> metadata;
    @JsonProperty("spec")
    private PodTemplateSpec<PodTemplate<R>> specification;

    public Metadata<PodTemplate<R>> metadata() {
        return new Metadata<>(this, this::metadata);
    }

    public PodTemplate<R> metadata(Metadata<PodTemplate<R>> metadata) {
        this.metadata = metadata;
        return this;
    }

    public PodTemplateSpec<PodTemplate<R>> spec() {
        if(specification != null) {
            return specification;
        }
        return new PodTemplateSpec<>(this, this::spec);
    }

    public PodTemplate<R> spec(PodTemplateSpec<PodTemplate<R>> specification) {
        this.specification = specification;
        return this;
    }

    public R buildPodTemplate() {
        listener.apply(this);
        return result;
    }
}
