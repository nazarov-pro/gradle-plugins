package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "clientIP"
        }
)
public final class ServiceSessionAffinityConfig<R extends DefaultK8sObject> extends AbstractK8sObject<R, ServiceSessionAffinityConfig<R>> {

    public ServiceSessionAffinityConfig(R result, ChangeListener<ServiceSessionAffinityConfig<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("clientIP")
    private ServiceSessionAffinityConfigClientIp<ServiceSessionAffinityConfig<R>> clientIp;

    public ServiceSessionAffinityConfig<R> clientIp(ServiceSessionAffinityConfigClientIp<ServiceSessionAffinityConfig<R>> clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public ServiceSessionAffinityConfigClientIp<ServiceSessionAffinityConfig<R>> clientIp() {
        return new ServiceSessionAffinityConfigClientIp<>(this, this::clientIp);
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
