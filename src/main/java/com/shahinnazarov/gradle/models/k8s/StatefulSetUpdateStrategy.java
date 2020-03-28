package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(
        {
                "rollingUpdate",
                "type",
        }
)
public final class StatefulSetUpdateStrategy<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, StatefulSetUpdateStrategy<R>> {

    public StatefulSetUpdateStrategy(R result, ChangeListener<StatefulSetUpdateStrategy<R>> listener) {
        super(result, listener);
    }

    private static final String TYPE_ROLLING_UPDATE = "RollingUpdate";
    private static final String TYPE_RECREATE = "Recreate";

    @JsonProperty("type")
    private String type;
    @JsonProperty("rollingUpdate")
    private StatefulSetRollingUpdateStrategy<StatefulSetUpdateStrategy<R>> rollingUpdateStrategy;

    public StatefulSetUpdateStrategy<R> recreate() {
        this.type = TYPE_RECREATE;
        return this;
    }

    public StatefulSetUpdateStrategy<R> rollingUpdate(StatefulSetRollingUpdateStrategy<StatefulSetUpdateStrategy<R>> rollingUpdateStrategy) {
        this.type = TYPE_ROLLING_UPDATE;
        this.rollingUpdateStrategy = rollingUpdateStrategy;
        return this;
    }

    public StatefulSetRollingUpdateStrategy<StatefulSetUpdateStrategy<R>> rollingUpdate() {
        return new StatefulSetRollingUpdateStrategy<>(this, this::rollingUpdate);
    }

    public R buildUpdateStrategy() {
        listener.apply(this);
        return result;
    }
}
