package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "command"
        }
)
public final class ExecutionActionHandler<R extends DefaultK8sObject> extends AbstractK8sObject<R, ExecutionActionHandler<R>> {

    public ExecutionActionHandler(R result, ChangeListener<ExecutionActionHandler<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("command")
    private List<String> command;

    public ExecutionActionHandler<R> command(String... commands) {
        if (this.command == null) {
            command = new ArrayList<>();
        }
        this.command.addAll(Arrays.asList(commands));
        return this;
    }

    public R buildExec() {
        listener.apply(this);
        return result;
    }
}
