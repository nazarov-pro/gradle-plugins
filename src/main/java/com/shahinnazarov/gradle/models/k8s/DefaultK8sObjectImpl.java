package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name"
        }
)
public final class DefaultK8sObjectImpl<R extends DefaultK8sObject> extends AbstractK8sObject<R, DefaultK8sObjectImpl<R>> {

    public DefaultK8sObjectImpl(R result, ChangeListener<DefaultK8sObjectImpl<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;

    public DefaultK8sObject<R> name(String name) {
        this.name = name;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
