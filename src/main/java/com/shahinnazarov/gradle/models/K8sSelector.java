package com.shahinnazarov.gradle.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class K8sSelector {
    private Map<String, String> matchLabels;
    private List<String> matchExpressions;

    public K8sSelector(Map<String, String> matchLabels, List<String> matchExpressions) {
        this.matchLabels = matchLabels;
        this.matchExpressions = matchExpressions;
    }

    public K8sSelector(Map<String, String> matchLabels) {
        this.matchLabels = matchLabels;
    }

    public K8sSelector(List<String> matchExpressions) {
        this.matchExpressions = matchExpressions;
    }
}

