package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "value"
        }
)
public final class DNSConfigOption<R extends DefaultK8sObject> extends AbstractK8sObject<R, DNSConfigOption<R>> {

    public DNSConfigOption(R result, ChangeListener<DNSConfigOption<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    public DNSConfigOption<R> name(String name) {
        this.name = name;
        return this;
    }

    public DNSConfigOption<R> value(String value) {
        this.value = value;
        return this;
    }

    public R buildDnsConfigOption() {
        listener.apply(this);
        return result;
    }
}
