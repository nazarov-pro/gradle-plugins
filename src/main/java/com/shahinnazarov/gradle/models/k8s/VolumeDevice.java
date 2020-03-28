package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "devicePath",
                "name",
        }
)
public final class VolumeDevice<R extends DefaultK8sObject> extends AbstractK8sObject<R, VolumeDevice<R>> {

    public VolumeDevice(R result, ChangeListener<VolumeDevice<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("devicePath")
    private String devicePath;

    public VolumeDevice<R> name(String name) {
        this.name = name;
        return this;
    }


    public VolumeDevice<R> devicePath(String devicePath) {
        this.devicePath = devicePath;
        return this;
    }

    public R buildVolumeDevice() {
        listener.apply(this);
        return result;
    }
}
