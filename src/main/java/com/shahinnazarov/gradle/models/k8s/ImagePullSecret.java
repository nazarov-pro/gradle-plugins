package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name"
        }
)
public final class ImagePullSecret<R extends DefaultK8sObject> extends AbstractK8sObject<R, ImagePullSecret<R>> {

    public ImagePullSecret(R result, ChangeListener<ImagePullSecret<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;

    public ImagePullSecret<R> name(String name) {
        this.name = name;
        return this;
    }

    public R buildImagePullSecret() {
        listener.apply(this);
        return result;
    }
}
