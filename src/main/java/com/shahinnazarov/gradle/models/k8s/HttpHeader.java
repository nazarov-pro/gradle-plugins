package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "value"
        }
)
public final class HttpHeader<R extends DefaultK8sObject> extends AbstractK8sObject<R, HttpHeader<R>> {

    public HttpHeader(R result, ChangeListener<HttpHeader<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    public HttpHeader<R> name(String name) {
        this.name = name;
        return this;
    }

    public HttpHeader<R> value(String value) {
        this.value = value;
        return this;
    }

    public R buildHttHeader() {
        listener.apply(this);
        return result;
    }
}
