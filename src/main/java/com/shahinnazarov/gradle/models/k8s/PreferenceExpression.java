package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "key",
                "operator",
                "values"
        }
)
public final class PreferenceExpression<R extends DefaultK8sObject> extends AbstractK8sObject<R, PreferenceExpression<R>> {

    public PreferenceExpression(R result, ChangeListener<PreferenceExpression<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("key")
    private String key;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("values")
    private List<String> values;

    public PreferenceExpression<R> key(String key) {
        this.key = key;
        return this;
    }

    public PreferenceExpression<R> operator(String operator) {
        this.operator = operator;
        return this;
    }

    public PreferenceExpression<R> values(List<String> values) {
        this.values = values;
        return this;
    }

    public PreferenceExpression<R> addValue(String value) {
        if (values == null) {
            values = new ArrayList<>();
        }
        values.add(value);
        return this;
    }

    public R buildPreferenceExpression() {
        listener.apply(this);
        return result;
    }
}
