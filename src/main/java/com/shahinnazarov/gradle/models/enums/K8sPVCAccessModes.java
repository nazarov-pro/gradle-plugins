package com.shahinnazarov.gradle.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum K8sPVCAccessModes {
    RWO("ReadWriteOnce"),
    RWX("ReadWriteMany"),
    ROX("ReadOnlyMany");

    private String name;
}
