package com.shahinnazarov.gradle.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum K8sServiceSpecPortProtocols {
    TCP("TCP"),
    UDP("UDP");

    private String name;
}
