package com.shahinnazarov.gradle.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class K8sPodTemplateSpecContainer {
    private String name;
    private String image;
    private List<K8sServiceSpecPort> ports;
    private Map<String, String> environments;
    private List<K8sPodTemplateSpecContainerVolumeMount> volumeMounts;

    public K8sPodTemplateSpecContainer(String name, String image, List<K8sServiceSpecPort> ports, Map<String, String> environments, List<K8sPodTemplateSpecContainerVolumeMount> volumeMounts) {
        this.name = name;
        this.image = image;
        this.ports = ports;
        this.environments = environments;
        this.volumeMounts = volumeMounts;
    }

    public K8sPodTemplateSpecContainer(String name, String image, List<K8sServiceSpecPort> ports, List<K8sPodTemplateSpecContainerVolumeMount> volumeMounts) {
        this.name = name;
        this.image = image;
        this.ports = ports;
        this.volumeMounts = volumeMounts;
    }

    public K8sPodTemplateSpecContainer(String name, String image, List<K8sPodTemplateSpecContainerVolumeMount> volumeMounts) {
        this.name = name;
        this.image = image;
        this.volumeMounts = volumeMounts;
    }
}
