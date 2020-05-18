package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "maxSurge",
                "maxUnavailable"
        }
)
public final class RollingUpdateStrategy<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, RollingUpdateStrategy<R>> {

    public RollingUpdateStrategy(R result, ChangeListener<RollingUpdateStrategy<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("maxSurge")
    private Object maxSurge;
    @JsonProperty("maxUnavailable")
    private Object maxUnavailable;

    public RollingUpdateStrategy<R> maxSurge(Object maxSurge) {
        this.maxSurge = maxSurge;
        return this;
    }

    public RollingUpdateStrategy<R> maxUnavailable(Object maxUnavailable) {
        this.maxUnavailable = maxUnavailable;
        return this;
    }

    public R buildRollingUpdate() {
        listener.apply(this);
        return result;
    }

}
