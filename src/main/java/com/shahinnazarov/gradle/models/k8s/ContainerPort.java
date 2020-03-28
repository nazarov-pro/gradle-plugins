package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "containerPort",
                "hostIP",
                "hostPort",
                "name",
                "protocol",
        }
)
public final class ContainerPort<R extends DefaultK8sObject> extends AbstractK8sObject<R, ContainerPort<R>> {

    public ContainerPort(R result, ChangeListener<ContainerPort<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("containerPort")
    private Integer containerPort;
    @JsonProperty("hostIP")
    private String hostIP;
    @JsonProperty("hostPort")
    private Integer hostPort;
    @JsonProperty("name")
    private String name;
    @JsonProperty("protocol")
    private String protocol;

    public ContainerPort<R> name(String name) {
        this.name = name;
        return this;
    }

    public ContainerPort<R> hostIP(String hostIP) {
        this.hostIP = hostIP;
        return this;
    }

    public ContainerPort<R> protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ContainerPort<R> containerPort(Integer containerPort) {
        this.containerPort = containerPort;
        return this;
    }

    public ContainerPort<R> hostPort(Integer hostPort) {
        this.hostPort = hostPort;
        return this;
    }

    public R buildPort() {
        listener.apply(this);
        return result;
    }
}
