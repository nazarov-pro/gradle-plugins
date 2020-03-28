package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "nodeSelectorTerms",
        }
)
public final class RequiredNodeScheduling<R extends DefaultK8sObject> extends AbstractK8sObject<R, RequiredNodeScheduling<R>> {

    public RequiredNodeScheduling(R result, ChangeListener<RequiredNodeScheduling<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("nodeSelectorTerms")
    private List<Preference<RequiredNodeScheduling<R>>> nodeSelectorTerms;

    public RequiredNodeScheduling<R> nodeSelectorTerm(Preference<RequiredNodeScheduling<R>> nodeSelectorTerm) {
        if (nodeSelectorTerms == null) {
            nodeSelectorTerms = new ArrayList<>();
        }
        nodeSelectorTerms.add(nodeSelectorTerm);
        return this;
    }

    public Preference<RequiredNodeScheduling<R>> nodeSelector() {
        return new Preference<>(this, this::nodeSelectorTerm);
    }

    public R buildRequiredScheduling() {
        listener.apply(this);
        return result;
    }
}
