package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "timeoutSeconds"
        }
)
public final class ServiceSessionAffinityConfigClientIp<R extends DefaultK8sObject> extends AbstractK8sObject<R, ServiceSessionAffinityConfigClientIp<R>> {

    public ServiceSessionAffinityConfigClientIp(R result, ChangeListener<ServiceSessionAffinityConfigClientIp<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("timeoutSeconds")
    private Integer timeoutSeconds;

    public ServiceSessionAffinityConfigClientIp<R> timeoutSeconds(Integer timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
