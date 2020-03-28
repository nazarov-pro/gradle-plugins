package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "partition"
        }
)
public final class StatefulSetRollingUpdateStrategy<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, StatefulSetRollingUpdateStrategy<R>> {

    public StatefulSetRollingUpdateStrategy(R result, ChangeListener<StatefulSetRollingUpdateStrategy<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("maxSurge")
    private Integer maxSurge;

    public StatefulSetRollingUpdateStrategy<R> maxSurge(Integer maxSurge) {
        this.maxSurge = maxSurge;
        return this;
    }

    public R buildRollingUpdate() {
        listener.apply(this);
        return result;
    }

}
