package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder(
        {
                "matchExpressions",
                "matchLabels"
        }
)
public final class LabelSelector<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, LabelSelector<R>> {

    public LabelSelector(R result, ChangeListener<LabelSelector<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("matchLabels")
    private Map<String, String> matchLabels;
    @JsonProperty("matchExpressions")
    private List<PreferenceExpression<LabelSelector<R>>> matchExpressions;

    public LabelSelector<R> matchLabels(Map<String, String> matchLabels) {
        this.matchLabels = matchLabels;
        return this;
    }

    public LabelSelector<R> addMatchLabel(String key, String value) {
        if (matchLabels == null) {
            matchLabels = new HashMap<>();
        }
        matchLabels.put(key, value);
        return this;
    }

    public PreferenceExpression<LabelSelector<R>> matchExpression() {
        return new PreferenceExpression<>(this, this::addMatchExpression);

    }

    public LabelSelector<R> addMatchExpression(PreferenceExpression<LabelSelector<R>> preferenceExpression) {
        if (matchExpressions == null) {
            matchExpressions = new ArrayList<>();
        }
        matchExpressions.add(preferenceExpression);
        return this;
    }

    public R buildSelector() {
        listener.apply(this);
        return super.result;
    }
}
