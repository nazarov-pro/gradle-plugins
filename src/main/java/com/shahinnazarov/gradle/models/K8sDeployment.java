package com.shahinnazarov.gradle.models;

import lombok.Data;

@Data
public class K8sDeployment {
    private String apiVersion = "apps/v1";
    private String kind = "Deployment";
    private K8sMetadata metadata;
    private K8sDeploymentSpec spec;

    public K8sDeployment(K8sMetadata metadata, K8sDeploymentSpec spec) {
        this.metadata = metadata;
        this.spec = spec;
    }
}
