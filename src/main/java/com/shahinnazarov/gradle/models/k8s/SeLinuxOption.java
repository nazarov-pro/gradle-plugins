package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "level",
                "role",
                "type",
                "user",
        }
)
public final class SeLinuxOption<R extends DefaultK8sObject> extends AbstractK8sObject<R, SeLinuxOption<R>> {

    public SeLinuxOption(R result, ChangeListener<SeLinuxOption<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("level")
    private String level;
    @JsonProperty("role")
    private String role;
    @JsonProperty("type")
    private String type;
    @JsonProperty("user")
    private String user;

    public SeLinuxOption<R> level(String level) {
        this.level = level;
        return this;
    }

    public SeLinuxOption<R> role(String role) {
        this.role = role;
        return this;
    }

    public SeLinuxOption<R> type(String type) {
        this.type = type;
        return this;
    }

    public SeLinuxOption<R> user(String user) {
        this.user = user;
        return this;
    }

    public R buildSeLinuxOption() {
        listener.apply(this);
        return result;
    }
}
