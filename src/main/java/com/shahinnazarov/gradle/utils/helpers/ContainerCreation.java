package com.shahinnazarov.gradle.utils.helpers;

import com.shahinnazarov.gradle.models.k8s.Container;
import com.shahinnazarov.gradle.models.k8s.DefaultK8sObject;

@FunctionalInterface
public interface ContainerCreation {
    Container<? extends DefaultK8sObject> container();
}
