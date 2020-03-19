package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(
        {
                "matchLabels"
        }
)
public final class ResourceSelectorByLabels<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, ResourceSelectorByLabels<R>> {

    public ResourceSelectorByLabels(R result, ChangeListener<ResourceSelectorByLabels<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("matchLabels")
    private Map<String, String> matchLabels = new HashMap<>();

    public ResourceSelectorByLabels<R> matchLabels(Map<String, String> matchLabels) {
        this.matchLabels = matchLabels;
        return this;
    }

    public ResourceSelectorByLabels<R> addMatchLabel(String key, String value) {
        matchLabels.put(key, value);
        return this;
    }

    public R buildSelector() {
        listener.apply(this);
        return super.result;
    }
}
