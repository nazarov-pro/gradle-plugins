package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "host",
                "port",
        }
)
public final class TcpSocketActionHandler<R extends DefaultK8sObject> extends AbstractK8sObject<R, TcpSocketActionHandler<R>> {

    public TcpSocketActionHandler(R result, ChangeListener<TcpSocketActionHandler<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("host")
    private String host;
    @JsonProperty("port")
    private String port;

    public TcpSocketActionHandler<R> host(String host) {
        this.host = host;
        return this;
    }

    public TcpSocketActionHandler<R> port(String port) {
        this.port = port;
        return this;
    }

    public R buildTcpSocket() {
        listener.apply(this);
        return result;
    }
}
