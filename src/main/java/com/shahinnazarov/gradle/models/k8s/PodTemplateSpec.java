package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "imagePullSecret"
        }
)
public final class PodTemplateSpec<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, PodTemplateSpec<R>> {

    public PodTemplateSpec(R result, ChangeListener<PodTemplateSpec<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("imagePullSecret")
    private String imagePullSecret;

    public PodTemplateSpec<R> imagePullSecret(String imagePullSecret) {
        this.imagePullSecret = imagePullSecret;
        return this;
    }


    public R buildPodTemplateSpec() {
        listener.apply(this);
        return result;
    }
}
