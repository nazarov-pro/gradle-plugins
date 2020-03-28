package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "preference",
                "weight",
        }
)
public final class PreferredNodeScheduling<R extends DefaultK8sObject> extends AbstractK8sObject<R, PreferredNodeScheduling<R>> {

    public PreferredNodeScheduling(R result, ChangeListener<PreferredNodeScheduling<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("preference")
    private Preference<PreferredNodeScheduling<R>> preference;
    @JsonProperty("weight")
    private Integer weight;

    public PreferredNodeScheduling<R> weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Preference<PreferredNodeScheduling<R>> preference() {
        return new Preference<>(this, this::preference);
    }

    public PreferredNodeScheduling<R> preference(Preference<PreferredNodeScheduling<R>> preference) {
        this.preference = preference;
        return this;
    }

    public R buildPreferredScheduling() {
        listener.apply(this);
        return result;
    }
}
