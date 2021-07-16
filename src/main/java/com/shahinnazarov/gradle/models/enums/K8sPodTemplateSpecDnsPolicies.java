package com.shahinnazarov.gradle.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum K8sPodTemplateSpecDnsPolicies {
    ClusterFirst("ClusterFirst"),
    ClusterFirstWithHostNet("ClusterFirstWithHostNet"),
    Default("Default"),
    None("None"),
    ;
    private final String type;
}
