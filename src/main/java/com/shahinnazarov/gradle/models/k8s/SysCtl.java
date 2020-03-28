package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "name",
                "value"
        }
)
public final class SysCtl<R extends DefaultK8sObject> extends AbstractK8sObject<R, SysCtl<R>> {

    public SysCtl(R result, ChangeListener<SysCtl<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    public SysCtl<R> name(String name) {
        this.name = name;
        return this;
    }

    public SysCtl<R> value(String value) {
        this.value = value;
        return this;
    }

    public R buildSysCtl() {
        listener.apply(this);
        return result;
    }
}
