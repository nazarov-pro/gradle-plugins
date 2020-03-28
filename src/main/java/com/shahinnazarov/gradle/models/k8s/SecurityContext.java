package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "allowPrivilegeEscalation",
                "capabilities",
                "privileged",
                "procMount",
                "readOnlyRootFilesystem",
                "runAsGroup",
                "runAsNonRoot",
                "runAsUser",
                "seLinuxOptions",
        }
)
public final class SecurityContext<R extends DefaultK8sObject> extends AbstractK8sObject<R, SecurityContext<R>> {

    public SecurityContext(R result, ChangeListener<SecurityContext<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("allowPrivilegeEscalation")
    private Boolean allowPrivilegeEscalation;
    @JsonProperty("capabilities")
    private LinuxCapabilities<SecurityContext<R>> capabilities;
    @JsonProperty("privileged")
    private Boolean privileged;
    @JsonProperty("procMount")
    private String procMount;
    @JsonProperty("readOnlyRootFilesystem")
    private Boolean readOnlyRootFilesystem;
    @JsonProperty("runAsGroup")
    private Integer runAsGroup;
    @JsonProperty("runAsNonRoot")
    private Boolean runAsNonRoot;
    @JsonProperty("runAsUser")
    private Integer runAsUser;
    @JsonProperty("seLinuxOptions")
    private SeLinuxOption<SecurityContext<R>> seLinuxOptions;


    public LinuxCapabilities<SecurityContext<R>> capabilities() {
        return new LinuxCapabilities<>(this, this::capabilities);
    }

    public SecurityContext<R> capabilities(LinuxCapabilities<SecurityContext<R>> linuxCapabilities) {
        this.capabilities = linuxCapabilities;
        return this;
    }


    public SeLinuxOption<SecurityContext<R>> seLinuxOptions() {
        return new SeLinuxOption<>(this, this::seLinuxOptions);
    }

    public SecurityContext<R> seLinuxOptions(SeLinuxOption<SecurityContext<R>> seLinuxOptions) {
        this.seLinuxOptions = seLinuxOptions;
        return this;
    }

    public SecurityContext<R> runAsGroup(Integer runAsGroup) {
        this.runAsGroup = runAsGroup;
        return this;
    }

    public SecurityContext<R> runAsUser(Integer runAsUser) {
        this.runAsUser = runAsUser;
        return this;
    }

    public SecurityContext<R> procMount(String procMount) {
        this.procMount = procMount;
        return this;
    }

    public SecurityContext<R> allowPrivilegeEscalation(Boolean allowPrivilegeEscalation) {
        this.allowPrivilegeEscalation = allowPrivilegeEscalation;
        return this;
    }

    public SecurityContext<R> privileged(Boolean privileged) {
        this.privileged = privileged;
        return this;
    }

    public SecurityContext<R> readOnlyRootFilesystem(Boolean readOnlyRootFilesystem) {
        this.readOnlyRootFilesystem = readOnlyRootFilesystem;
        return this;
    }

    public SecurityContext<R> runAsNonRoot(Boolean runAsNonRoot) {
        this.runAsNonRoot = runAsNonRoot;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
