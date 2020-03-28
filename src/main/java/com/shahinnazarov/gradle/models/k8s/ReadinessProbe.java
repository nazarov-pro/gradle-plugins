package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "exec",
                "failureThreshold",
                "httpGet",
                "initialDelaySeconds",
                "periodSeconds",
                "successThreshold",
                "tcpSocket",
                "timeoutSeconds",
        }
)
public final class ReadinessProbe<R extends DefaultK8sObject> extends AbstractK8sObject<R, ReadinessProbe<R>> {

    public ReadinessProbe(R result, ChangeListener<ReadinessProbe<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("exec")
    private ExecutionActionHandler<ReadinessProbe<R>> exec;
    @JsonProperty("failureThreshold")
    private Integer failureThreshold;
    @JsonProperty("httpGet")
    private HttpGetActionHandler<ReadinessProbe<R>> httpGet;
    @JsonProperty("initialDelaySeconds")
    private Integer initialDelaySeconds;
    @JsonProperty("periodSeconds")
    private Integer periodSeconds;
    @JsonProperty("successThreshold")
    private Integer successThreshold;
    @JsonProperty("tcpSocket")
    private TcpSocketActionHandler<ReadinessProbe<R>> tcpSocket;
    @JsonProperty("timeoutSeconds")
    private Integer timeoutSeconds;

    public ReadinessProbe<R> failureThreshold(Integer failureThreshold) {
        this.failureThreshold = failureThreshold;
        return this;
    }

    public ReadinessProbe<R> initialDelaySeconds(Integer initialDelaySeconds) {
        this.initialDelaySeconds = initialDelaySeconds;
        return this;
    }

    public ReadinessProbe<R> periodSeconds(Integer periodSeconds) {
        this.periodSeconds = periodSeconds;
        return this;
    }

    public ReadinessProbe<R> successThreshold(Integer successThreshold) {
        this.successThreshold = successThreshold;
        return this;
    }

    public ReadinessProbe<R> timeoutSeconds(Integer timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public ReadinessProbe<R> exec(ExecutionActionHandler<ReadinessProbe<R>> exec) {
        this.exec = exec;
        return this;
    }

    public ExecutionActionHandler<ReadinessProbe<R>> exec() {
        return new ExecutionActionHandler<ReadinessProbe<R>>(this, this::exec);
    }

    public ReadinessProbe<R> httpGet(HttpGetActionHandler<ReadinessProbe<R>> httpGet) {
        this.httpGet = httpGet;
        return this;
    }

    public HttpGetActionHandler<ReadinessProbe<R>> httpGet() {
        return new HttpGetActionHandler<ReadinessProbe<R>>(this, this::httpGet);
    }

    public ReadinessProbe<R> tcpSocket(TcpSocketActionHandler<ReadinessProbe<R>> tcpSocket) {
        this.tcpSocket = tcpSocket;
        return this;
    }

    public TcpSocketActionHandler<ReadinessProbe<R>> tcpSocket() {
        return new TcpSocketActionHandler<ReadinessProbe<R>>(this, this::tcpSocket);
    }


    public R buildProbe() {
        listener.apply(this);
        return result;
    }
}
