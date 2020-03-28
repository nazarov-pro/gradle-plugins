package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "effect",
                "key",
                "operator",
                "tolerationSeconds",
                "value"
        }
)
public final class Toleration<R extends DefaultK8sObject> extends AbstractK8sObject<R, Toleration<R>> {

    public Toleration(R result, ChangeListener<Toleration<R>> listener) {
        super(result, listener);
    }

    /**
     * NoSchedule, PreferNoSchedule
     * and NoExecute
     */
    @JsonProperty("effect")
    private String effect;
    @JsonProperty("key")
    private String key;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("tolerationSeconds")
    private Integer tolerationSeconds;
    @JsonProperty("value")
    private String value;

    public Toleration<R> effect(String effect) {
        this.effect = effect;
        return this;
    }

    public Toleration<R> key(String key) {
        this.key = key;
        return this;
    }

    public Toleration<R> operator(String operator) {
        this.operator = operator;
        return this;
    }

    public Toleration<R> tolerationSeconds(Integer tolerationSeconds) {
        this.tolerationSeconds = tolerationSeconds;
        return this;
    }

    public Toleration<R> value(String value) {
        this.value = value;
        return this;
    }

    public R buildSysCtl() {
        listener.apply(this);
        return result;
    }
}
