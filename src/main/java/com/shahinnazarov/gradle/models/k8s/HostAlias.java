package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "hostnames",
                "ip",
        }
)
public final class HostAlias<R extends DefaultK8sObject> extends AbstractK8sObject<R, HostAlias<R>> {

    public HostAlias(R result, ChangeListener<HostAlias<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("hostnames")
    private List<String> hostnames;
    @JsonProperty("ip")
    private String ip;

    public HostAlias<R> addHost(String... hostnames) {
        if (this.hostnames == null) {
            this.hostnames = new ArrayList<>();
        }
        this.hostnames.addAll(Arrays.asList(hostnames));
        return this;
    }

    public HostAlias<R> ip(String ip) {
        this.ip = ip;
        return this;
    }

    public R buildHostAlias() {
        listener.apply(this);
        return result;
    }
}
