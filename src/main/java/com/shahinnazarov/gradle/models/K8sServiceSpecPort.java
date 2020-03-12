package com.shahinnazarov.gradle.models;

import com.shahinnazarov.gradle.models.enums.K8sServiceSpecPortProtocols;
import lombok.Data;

@Data
public class K8sServiceSpecPort {
    private String name;
    private Integer nodePort;
    private Integer port;
    private Integer containerPort;
    private Integer targetPort;
    private K8sServiceSpecPortProtocols protocol;

    public K8sServiceSpecPort(String name, Integer nodePort, Integer port, Integer targetPort, K8sServiceSpecPortProtocols protocol) {
        this.name = name;
        this.nodePort = nodePort;
        this.port = port;
        this.targetPort = targetPort;
        this.protocol = protocol;
    }

    public K8sServiceSpecPort(String name, Integer port, Integer targetPort, K8sServiceSpecPortProtocols protocol) {
        this.name = name;
        this.port = port;
        this.targetPort = targetPort;
        this.protocol = protocol;
    }

    public K8sServiceSpecPort(String name, Integer containerPort) {
        this.name = name;
        this.containerPort = containerPort;
    }

    public K8sServiceSpecPort(Integer nodePort, Integer port, Integer targetPort, K8sServiceSpecPortProtocols protocol) {
        this.nodePort = nodePort;
        this.port = port;
        this.targetPort = targetPort;
        this.protocol = protocol;
    }

    public K8sServiceSpecPort(Integer port, Integer targetPort, K8sServiceSpecPortProtocols protocol) {
        this.port = port;
        this.targetPort = targetPort;
        this.protocol = protocol;
    }
}
