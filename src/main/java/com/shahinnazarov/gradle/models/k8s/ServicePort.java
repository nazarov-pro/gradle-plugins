package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "nodePort",
                "port",
                "protocol",
                "targetPort",
        }
)
public final class ServicePort<R extends DefaultK8sObject> extends AbstractK8sObject<R, ServicePort<R>> {

    public ServicePort(R result, ChangeListener<ServicePort<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("nodePort")
    private Integer nodePort;
    @JsonProperty("port")
    private Integer port;
    @JsonProperty("targetPort")
    private Integer targetPort;
    @JsonProperty("protocol")
    private String protocol;

    public ServicePort<R> name(String name) {
        this.name = name;
        return this;
    }

    public ServicePort<R> targetPort(Integer targetPort) {
        this.targetPort = targetPort;
        return this;
    }

    public ServicePort<R> protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public ServicePort<R> nodePort(Integer nodePort) {
        this.nodePort = nodePort;
        return this;
    }

    public ServicePort<R> port(Integer port) {
        this.port = port;
        return this;
    }

    public R buildPort() {
        listener.apply(this);
        return result;
    }
}
