package com.shahinnazarov.gradle.models.k8s;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(
        {
                "name", "namespace", "labels", "annotations"
        }
)
@Getter
public final class Metadata<R extends DefaultK8sObject> extends AbstractK8sObject<R, Metadata<R>> {

    public Metadata(R result, ChangeListener<Metadata<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("namespace")
    private String namespace;
    @JsonProperty("labels")
    private Map<String, String> labels;
    @JsonProperty("annotations")
    private Map<String, String> annotations;

    public Metadata<R> name(String name) {
        this.name = name;
        return this;
    }

    public Metadata<R> namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public Metadata<R> annotations(Map<String, String> annotations) {
        this.annotations = annotations;
        return this;
    }

    public Metadata<R> labels(Map<String, String> labels) {
        this.labels = labels;
        return this;
    }

    public Metadata<R> addLabel(String key, String value) {
        if (labels == null) {
            labels = new HashMap<>();
        }
        labels.put(key, value);
        return this;
    }

    public Metadata<R> addAnnotation(String key, String value) {
        if (annotations == null) {
            annotations = new HashMap<>();
        }
        annotations.put(key, value);
        return this;
    }

    public R buildMetadata() {
        listener.apply(this);
        return result;
    }
}


