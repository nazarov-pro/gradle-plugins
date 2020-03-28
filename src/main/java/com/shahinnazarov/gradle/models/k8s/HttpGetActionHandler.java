package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(
        {
                "host",
                "httpHeaders",
                "path",
                "port",
                "scheme",
        }
)
public final class HttpGetActionHandler<R extends DefaultK8sObject> extends AbstractK8sObject<R, HttpGetActionHandler<R>> {

    public HttpGetActionHandler(R result, ChangeListener<HttpGetActionHandler<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("host")
    private String host;
    @JsonProperty("httpHeaders")
    private List<HttpHeader<HttpGetActionHandler<R>>> httpHeaders;
    @JsonProperty("path")
    private String path;
    @JsonProperty("port")
    private String port;
    @JsonProperty("scheme")
    private String scheme;

    public HttpHeader<HttpGetActionHandler<R>> addHttpHeader() {
        return new HttpHeader<>(this, this::addHttpHeader);
    }

    public HttpGetActionHandler<R> addHttpHeader(HttpHeader<HttpGetActionHandler<R>> httpHeader) {
        if (this.httpHeaders == null) {
            this.httpHeaders = new ArrayList<>();
        }
        this.httpHeaders.add(httpHeader);
        return this;
    }

    public HttpGetActionHandler<R> host(String host) {
        this.host = host;
        return this;
    }

    public HttpGetActionHandler<R> path(String path) {
        this.path = path;
        return this;
    }

    public HttpGetActionHandler<R> port(String port) {
        this.port = port;
        return this;
    }

    public HttpGetActionHandler<R> scheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public R buildHttpGet() {
        listener.apply(this);
        return result;
    }
}
