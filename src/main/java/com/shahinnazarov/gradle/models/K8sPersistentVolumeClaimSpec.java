package com.shahinnazarov.gradle.models;

import com.shahinnazarov.gradle.models.enums.K8sPVCAccessModes;
import lombok.Data;

import java.util.List;

@Data
public class K8sPersistentVolumeClaimSpec {
    private List<K8sPVCAccessModes> accessModes;
    private K8sResource resource;
    private String storageClassName;

    public K8sPersistentVolumeClaimSpec(List<K8sPVCAccessModes> accessModes, K8sResource resource, String storageClassName) {
        this.accessModes = accessModes;
        this.resource = resource;
        this.storageClassName = storageClassName;
    }

    public K8sPersistentVolumeClaimSpec(List<K8sPVCAccessModes> accessModes, K8sResource resource) {
        this.accessModes = accessModes;
        this.resource = resource;
    }
}
