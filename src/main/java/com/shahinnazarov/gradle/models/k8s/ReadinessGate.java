package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "conditionType"
        }
)
public final class ReadinessGate<R extends DefaultK8sObject> extends AbstractK8sObject<R, ReadinessGate<R>> {

    public ReadinessGate(R result, ChangeListener<ReadinessGate<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("conditionType")
    private String conditionType;

    public ReadinessGate<R> conditionType(String conditionType) {
        this.conditionType = conditionType;
        return this;
    }

    public R buildReadinessGate() {
        listener.apply(this);
        return result;
    }
}
