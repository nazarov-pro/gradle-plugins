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
    private String maxSurge;
    @JsonProperty("maxUnavailable")
    private String maxUnavailable;

    public RollingUpdateStrategy<R> maxSurge(String maxSurge) {
        this.maxSurge = maxSurge;
        return this;
    }

    public RollingUpdateStrategy<R> maxUnavailable(String maxUnavailable) {
        this.maxUnavailable = maxUnavailable;
        return this;
    }

    public R buildRollingUpdate() {
        listener.apply(this);
        return result;
    }

}
