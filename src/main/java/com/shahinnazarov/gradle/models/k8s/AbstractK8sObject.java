package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractK8sObject<R extends DefaultK8sObject, M extends DefaultK8sObject<R>>
        implements DefaultK8sObject<R> {

    public AbstractK8sObject(
            R result,
            ChangeListener<M> listener) {
        this.result = result;
        this.listener = listener;
    }

    @JsonIgnore
    protected R result;
    @JsonIgnore
    protected ChangeListener<M> listener;


}
