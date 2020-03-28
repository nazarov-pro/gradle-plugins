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
public final class LivenessProbe<R extends DefaultK8sObject> extends AbstractK8sObject<R, LivenessProbe<R>> {

    public LivenessProbe(R result, ChangeListener<LivenessProbe<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("exec")
    private ExecutionActionHandler<LivenessProbe<R>> exec;
    @JsonProperty("failureThreshold")
    private Integer failureThreshold;
    @JsonProperty("httpGet")
    private HttpGetActionHandler<LivenessProbe<R>> httpGet;
    @JsonProperty("initialDelaySeconds")
    private Integer initialDelaySeconds;
    @JsonProperty("periodSeconds")
    private Integer periodSeconds;
    @JsonProperty("successThreshold")
    private Integer successThreshold;
    @JsonProperty("tcpSocket")
    private TcpSocketActionHandler<LivenessProbe<R>> tcpSocket;
    @JsonProperty("timeoutSeconds")
    private Integer timeoutSeconds;

    public LivenessProbe<R> failureThreshold(Integer failureThreshold) {
        this.failureThreshold = failureThreshold;
        return this;
    }

    public LivenessProbe<R> initialDelaySeconds(Integer initialDelaySeconds) {
        this.initialDelaySeconds = initialDelaySeconds;
        return this;
    }

    public LivenessProbe<R> periodSeconds(Integer periodSeconds) {
        this.periodSeconds = periodSeconds;
        return this;
    }

    public LivenessProbe<R> successThreshold(Integer successThreshold) {
        this.successThreshold = successThreshold;
        return this;
    }

    public LivenessProbe<R> timeoutSeconds(Integer timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public LivenessProbe<R> exec(ExecutionActionHandler<LivenessProbe<R>> exec) {
        this.exec = exec;
        return this;
    }

    public ExecutionActionHandler<LivenessProbe<R>> exec() {
        return new ExecutionActionHandler<LivenessProbe<R>>(this, this::exec);
    }

    public LivenessProbe<R> httpGet(HttpGetActionHandler<LivenessProbe<R>> httpGet) {
        this.httpGet = httpGet;
        return this;
    }

    public HttpGetActionHandler<LivenessProbe<R>> httpGet() {
        return new HttpGetActionHandler<LivenessProbe<R>>(this, this::httpGet);
    }

    public LivenessProbe<R> tcpSocket(TcpSocketActionHandler<LivenessProbe<R>> tcpSocket) {
        this.tcpSocket = tcpSocket;
        return this;
    }

    public TcpSocketActionHandler<LivenessProbe<R>> tcpSocket() {
        return new TcpSocketActionHandler<LivenessProbe<R>>(this, this::tcpSocket);
    }


    public R buildProbe() {
        listener.apply(this);
        return result;
    }
}
