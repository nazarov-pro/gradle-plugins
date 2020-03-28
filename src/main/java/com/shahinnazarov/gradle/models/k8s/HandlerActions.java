package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "exec",
                "httpGet",
                "tcpSocket",
        }
)
public final class HandlerActions<R extends DefaultK8sObject> extends AbstractK8sObject<R, HandlerActions<R>> {

    public HandlerActions(R result, ChangeListener<HandlerActions<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("exec")
    private ExecutionActionHandler<HandlerActions<R>> exec;
    @JsonProperty("httpGet")
    private HttpGetActionHandler<HandlerActions<R>> httpGet;
    @JsonProperty("tcpSocket")
    private TcpSocketActionHandler<HandlerActions<R>> tcpSocket;

    public HandlerActions<R> exec(ExecutionActionHandler<HandlerActions<R>> exec) {
        this.exec = exec;
        return this;
    }

    public ExecutionActionHandler<HandlerActions<R>> exec() {
        return new ExecutionActionHandler<HandlerActions<R>>(this, this::exec);
    }

    public HandlerActions<R> httpGet(HttpGetActionHandler<HandlerActions<R>> httpGet) {
        this.httpGet = httpGet;
        return this;
    }

    public HttpGetActionHandler<HandlerActions<R>> httpGet() {
        return new HttpGetActionHandler<HandlerActions<R>>(this, this::httpGet);
    }

    public HandlerActions<R> tcpSocket(TcpSocketActionHandler<HandlerActions<R>> tcpSocket) {
        this.tcpSocket = tcpSocket;
        return this;
    }

    public TcpSocketActionHandler<HandlerActions<R>> tcpSocket() {
        return new TcpSocketActionHandler<HandlerActions<R>>(this, this::tcpSocket);
    }


    public R build() {
        listener.apply(this);
        return result;
    }
}
