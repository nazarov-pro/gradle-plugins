package com.shahinnazarov.gradle.models;

import lombok.Data;

import java.util.Map;

@Data
public class K8sPodTemplateSpecContainerVolumeMount {
    private String name;
    private String mountPath;

    public K8sPodTemplateSpecContainerVolumeMount(String name, String mountPath) {
        this.name = name;
        this.mountPath = mountPath;
    }
}
