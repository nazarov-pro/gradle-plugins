package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "postStart",
                "preStop",
        }
)
public final class ContainerLifecycle<R extends DefaultK8sObject> extends AbstractK8sObject<R, ContainerLifecycle<R>> {

    public ContainerLifecycle(R result, ChangeListener<ContainerLifecycle<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("postStart")
    private HandlerActions<ContainerLifecycle<R>> postStart;
    @JsonProperty("preStop")
    private HandlerActions<ContainerLifecycle<R>> preStop;

    public ContainerLifecycle<R> postStart(HandlerActions<ContainerLifecycle<R>> postStart) {
        this.postStart = postStart;
        return this;
    }

    public HandlerActions<ContainerLifecycle<R>> postStart() {
        return new HandlerActions<ContainerLifecycle<R>>(this, this::postStart);
    }

    public ContainerLifecycle<R> preStop(HandlerActions<ContainerLifecycle<R>> preStop) {
        this.preStop = preStop;
        return this;
    }

    public HandlerActions<ContainerLifecycle<R>> preStop() {
        return new HandlerActions<ContainerLifecycle<R>>(this, this::preStop);
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
