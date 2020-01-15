package com.shahinnazarov.gradle.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum K8sImagePullPolicies {
    IfNotPresent("IfNotPresent"),
    Always("Always"),
    Never("Never");

    private String name;
}
