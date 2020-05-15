package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.*;

@JsonPropertyOrder(
        {
                "clusterIP",
                "externalIPs",
                "externalName",
                "externalTrafficPolicy",
                "healthCheckNodePort",
                "loadBalancerIP",
                "loadBalancerSourceRanges",
                "ports",
                "publishNotReadyAddresses",
                "selector",
                "sessionAffinity",
                "sessionAffinityConfig",
                "type",
        }
)
public final class ServiceSpec<R extends DefaultK8sObject> extends AbstractK8sObject<R, ServiceSpec<R>> {

    public ServiceSpec(R result, ChangeListener<ServiceSpec<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("clusterIP")
    private String clusterIP;
    @JsonProperty("externalIPs")
    private List<String> externalIPs;
    @JsonProperty("externalName")
    private String externalName;
    @JsonProperty("externalTrafficPolicy")
    private String externalTrafficPolicy;
    @JsonProperty("healthCheckNodePort")
    private Integer healthCheckNodePort;
    @JsonProperty("loadBalancerIP")
    private String loadBalancerIP;
    @JsonProperty("loadBalancerSourceRanges")
    private List<String> loadBalancerSourceRanges;
    @JsonProperty("ports")
    private List<ServicePort<ServiceSpec<R>>> ports;
    @JsonProperty("publishNotReadyAddresses")
    private Boolean publishNotReadyAddresses;
    @JsonProperty("selector")
    private Map<String, String> selector;
    @JsonProperty("sessionAffinity")
    private String sessionAffinity;
    @JsonProperty("sessionAffinityConfig")
    private ServiceSessionAffinityConfig<ServiceSpec<R>> sessionAffinityConfig;
    @JsonProperty("type")
    private String type;

    public ServiceSpec<R> clusterIP(String clusterIP) {
        this.clusterIP = clusterIP;
        return this;
    }

    public ServiceSpec<R> addExternalIps(String... externalIPs) {
        if (this.externalIPs == null) {
            this.externalIPs = new ArrayList<>();
        }
        this.externalIPs.addAll(Arrays.asList(externalIPs));
        return this;
    }

    public ServiceSpec<R> externalName(String externalName) {
        this.externalName = externalName;
        return this;
    }

    public ServiceSpec<R> externalTrafficPolicy(String externalTrafficPolicy) {
        this.externalTrafficPolicy = externalTrafficPolicy;
        return this;
    }

    public ServiceSpec<R> healthCheckNodePort(Integer healthCheckNodePort) {
        this.healthCheckNodePort = healthCheckNodePort;
        return this;
    }

    public ServiceSpec<R> loadBalancerIP(String loadBalancerIP) {
        this.loadBalancerIP = loadBalancerIP;
        return this;
    }

    public ServiceSpec<R> addLoadBalancerSourceRanges(String... loadBalancerSourceRanges) {
        if (this.loadBalancerSourceRanges == null) {
            this.loadBalancerSourceRanges = new ArrayList<>();
        }
        this.loadBalancerSourceRanges.addAll(Arrays.asList(loadBalancerSourceRanges));
        return this;
    }

    public ServiceSpec<R> addPort(ServicePort<ServiceSpec<R>> port) {
        if (this.ports == null) {
            this.ports = new ArrayList<>();
        }
        this.ports.add(port);
        return this;
    }

    public ServicePort<ServiceSpec<R>> addPort() {
        return new ServicePort<>(this, this::addPort);
    }

    public ServiceSpec<R> publishNotReadyAddresses(Boolean publishNotReadyAddresses) {
        this.publishNotReadyAddresses = publishNotReadyAddresses;
        return this;
    }

    public ServiceSpec<R> selector(Map<String, String> selector) {
        this.selector = selector;
        return this;
    }

    public ServiceSpec<R> addSelector(String key, String value) {
        if (this.selector == null) {
            this.selector = new HashMap<>();
        }
        this.selector.put(key, value);
        return this;
    }

    public ServiceSpec<R> sessionAffinity(String sessionAffinity) {
        this.sessionAffinity = sessionAffinity;
        return this;
    }

    public ServiceSessionAffinityConfig<ServiceSpec<R>> sessionAffinityConfig() {
        return sessionAffinityConfig == null ?
                new ServiceSessionAffinityConfig<>(this, this::sessionAffinityConfig) : sessionAffinityConfig;
    }

    public ServiceSpec<R> sessionAffinityConfig(ServiceSessionAffinityConfig<ServiceSpec<R>> sessionAffinityConfig) {
        this.sessionAffinityConfig = sessionAffinityConfig;
        return this;
    }

    public ServiceSpec<R> type(String type) {
        this.type = type;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
