package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "fsGroup",
                "runAsGroup",
                "runAsNonRoot",
                "runAsUser",
                "seLinuxOptions",
                "supplementalGroups",
                "sysctls",
        }
)
public final class PodSecurityContext<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodSecurityContext<R>> {

    public PodSecurityContext(R result, ChangeListener<PodSecurityContext<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("privileged")
    private Integer fsGroup;
    @JsonProperty("runAsGroup")
    private Integer runAsGroup;
    @JsonProperty("runAsNonRoot")
    private Boolean runAsNonRoot;
    @JsonProperty("runAsUser")
    private Integer runAsUser;
    @JsonProperty("seLinuxOptions")
    private SeLinuxOption<PodSecurityContext<R>> seLinuxOptions;
    @JsonProperty("supplementalGroups")
    private List<Integer> supplementalGroups;
    @JsonProperty("sysctls")
    private List<SysCtl<PodSecurityContext<R>>> sysctls;


    public SysCtl<PodSecurityContext<R>> addSysCtl() {
        return new SysCtl<>(this, this::addSysCtl);
    }

    public PodSecurityContext<R> addSysCtl(SysCtl<PodSecurityContext<R>> sysctl) {
        if (this.sysctls == null) {
            this.sysctls = new ArrayList<>();
        }
        this.sysctls.add(sysctl);
        return this;
    }


    public SeLinuxOption<PodSecurityContext<R>> seLinuxOptions() {
        return new SeLinuxOption<>(this, this::seLinuxOptions);
    }

    public PodSecurityContext<R> seLinuxOptions(SeLinuxOption<PodSecurityContext<R>> seLinuxOptions) {
        this.seLinuxOptions = seLinuxOptions;
        return this;
    }

    public PodSecurityContext<R> runAsGroup(Integer runAsGroup) {
        this.runAsGroup = runAsGroup;
        return this;
    }

    public PodSecurityContext<R> runAsUser(Integer runAsUser) {
        this.runAsUser = runAsUser;
        return this;
    }

    public PodSecurityContext<R> addSupplementalGroups(Integer... supplementalGroups) {
        if (this.supplementalGroups == null) {
            this.supplementalGroups = new ArrayList<>();
        }
        this.supplementalGroups.addAll(Arrays.asList(supplementalGroups));
        return this;
    }

    public PodSecurityContext<R> runAsNonRoot(Boolean runAsNonRoot) {
        this.runAsNonRoot = runAsNonRoot;
        return this;
    }

    public R build() {
        listener.apply(this);
        return result;
    }
}
