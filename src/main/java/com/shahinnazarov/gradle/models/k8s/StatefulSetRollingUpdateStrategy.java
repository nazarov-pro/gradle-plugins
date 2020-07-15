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

    @JsonProperty("partition")
    private Integer partition;

    public StatefulSetRollingUpdateStrategy<R> partition(Integer partition) {
        this.partition = partition;
        return this;
    }

    public R buildRollingUpdate() {
        listener.apply(this);
        return result;
    }

}
