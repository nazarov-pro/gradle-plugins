package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "matchExpressions",
                "matchFields"
        }
)
public final class Preference<R extends DefaultK8sObject> extends AbstractK8sObject<R, Preference<R>> {

    public Preference(R result, ChangeListener<Preference<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("matchExpressions")
    private List<PreferenceExpression<Preference<R>>> matchExpressions;

    @JsonProperty("matchFields")
    private List<PreferenceExpression<Preference<R>>> matchFields;

    public PreferenceExpression<Preference<R>> addMatchFields() {
        return new PreferenceExpression<>(this, this::matchFields);
    }

    public PreferenceExpression<Preference<R>> addMatchExpressions() {
        return new PreferenceExpression<>(this, this::matchExpressions);
    }

    public Preference<R> matchExpressions(PreferenceExpression<Preference<R>> matchExpressions) {
        if(this.matchExpressions == null) {
            this.matchExpressions = new ArrayList<>();
        }
        this.matchExpressions.add(matchExpressions);
        return this;
    }

    public Preference<R> matchFields(PreferenceExpression<Preference<R>> matchFields) {
        if (this.matchFields == null) {
            this.matchFields = new ArrayList<>();
        }
        this.matchFields.add(matchFields);
        return this;
    }

    public R buildPreference() {
        listener.apply(this);
        return result;
    }
}
