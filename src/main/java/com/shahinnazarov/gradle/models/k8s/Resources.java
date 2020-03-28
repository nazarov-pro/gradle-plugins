package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder(
        {
                "limits",
                "requests",
        }
)
public final class Resources<R extends DefaultK8sObject> extends AbstractK8sObject<R, Resources<R>> {

    public Resources(R result, ChangeListener<Resources<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("limits")
    private Map<String, String> limits;
    @JsonProperty("requests")
    private Map<String, String> requests;


    public Resources<R> addLimit(String key, String value) {
        if (this.limits == null) {
            this.limits = new HashMap<>();
        }
        this.limits.put(key, value);
        return this;
    }

    public Resources<R> addRequest(String key, String value) {
        if (this.requests == null) {
            this.requests = new HashMap<>();
        }
        this.requests.put(key, value);
        return this;
    }

    public R buildResources() {
        listener.apply(this);
        return result;
    }
}
