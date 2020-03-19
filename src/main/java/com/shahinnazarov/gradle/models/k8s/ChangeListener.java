package com.shahinnazarov.gradle.models.k8s;

@FunctionalInterface
public interface ChangeListener<T extends DefaultK8sObject> {
    void apply(T model);
}
