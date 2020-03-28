package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "add",
                "drop",
        }
)
public final class LinuxCapabilities<R extends DefaultK8sObject> extends AbstractK8sObject<R, LinuxCapabilities<R>> {

    public LinuxCapabilities(R result, ChangeListener<LinuxCapabilities<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("add")
    private List<String> add;
    @JsonProperty("drop")
    private List<String> drop;

    public LinuxCapabilities<R> addCapabilities(String... capabilities) {
        if(this.add == null) {
            this.add = new ArrayList<>();
        }
        this.add.addAll(Arrays.asList(capabilities));
        return this;
    }

    public LinuxCapabilities<R> dropCapabilities(String... capabilities) {
        if(this.drop == null) {
            this.drop = new ArrayList<>();
        }
        this.drop.addAll(Arrays.asList(capabilities));
        return this;
    }

    public R buildCapability() {
        listener.apply(this);
        return result;
    }
}
