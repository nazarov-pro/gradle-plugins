package com.shahinnazarov.gradle.models.k8s;

public interface DefaultK8sResource<R extends DefaultK8sResource> extends DefaultK8sObject<R> {
    String getApiVersion();

    String getKind();

    Metadata getMetadata();

}
