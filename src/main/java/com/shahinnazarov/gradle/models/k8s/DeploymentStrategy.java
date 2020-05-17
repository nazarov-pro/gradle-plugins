package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "type",
                "rollingUpdate"
        }
)
public final class DeploymentStrategy<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, DeploymentStrategy<R>> {

    public DeploymentStrategy(R result, ChangeListener<DeploymentStrategy<R>> listener) {
        super(result, listener);
    }

    public static final String TYPE_ROLLING_UPDATE = "RollingUpdate";
    public static final String TYPE_RECREATE = "Recreate";

    @JsonProperty("type")
    private String type;
    @JsonProperty("rollingUpdate")
    private RollingUpdateStrategy<DeploymentStrategy<R>> rollingUpdateStrategy;

    public DeploymentStrategy<R> recreate() {
        this.type = TYPE_RECREATE;
        return this;
    }

    public DeploymentStrategy<R> rollingUpdate(RollingUpdateStrategy<DeploymentStrategy<R>> rollingUpdateStrategy) {
        this.type = TYPE_ROLLING_UPDATE;
        this.rollingUpdateStrategy = rollingUpdateStrategy;
        return this;
    }

    public RollingUpdateStrategy<DeploymentStrategy<R>> rollingUpdate() {
        return new RollingUpdateStrategy<>(this, this::rollingUpdate);
    }

    public R buildDeploymentStrategy() {
        listener.apply(this);
        return result;
    }
}
