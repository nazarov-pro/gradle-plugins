package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import com.shahinnazarov.gradle.models.enums.K8sPodTemplateSpecDnsPolicies;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder(
        {
                "activeDeadlineSeconds",
                "affinity",
                "automountServiceAccountToken",
                "containers",
                "dnsConfig",
                "dnsPolicy",
                "enableServiceLinks",
                "hostAliases",
                "hostIPC",
                "hostNetwork",
                "hostPID",
                "hostname",
                "imagePullSecrets",
                "initContainers",
                "nodeName",
                "nodeSelector",
                "priority",
                "priorityClassName",
                "readinessGates",
                "restartPolicy",
                "runtimeClassName",
                "schedulerName",
                "securityContext",
                "serviceAccountName",
                "shareProcessNamespace",
                "subdomain",
                "terminationGracePeriodSeconds",
                "tolerations",
                "volumes",
        }
)
public final class PodTemplateSpec<R extends DefaultK8sObject>
        extends AbstractK8sObject<R, PodTemplateSpec<R>> {

    public PodTemplateSpec(R result, ChangeListener<PodTemplateSpec<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("activeDeadlineSeconds")
    private Integer activeDeadlineSeconds;
    @JsonProperty("affinity")
    private Affinity<PodTemplateSpec<R>> affinity;
    @JsonProperty("automountServiceAccountToken")
    private Boolean automountServiceAccountToken;
    @JsonProperty("containers")
    private List<Container<PodTemplateSpec<R>>> containers;
    @JsonProperty("dnsConfig")
    private DNSConfig<PodTemplateSpec<R>> dnsConfig;
    /**
     * 'ClusterFirstWithHostNet', 'ClusterFirst', 'Default' or 'None'. DNS
     */
    @JsonProperty("dnsPolicy")
    private String dnsPolicy;
    @JsonProperty("enableServiceLinks")
    private Boolean enableServiceLinks;
    @JsonProperty("hostAliases")
    private List<HostAlias<PodTemplateSpec<R>>> hostAliases;
    @JsonProperty("hostIPC")
    private Boolean hostIPC;
    @JsonProperty("hostNetwork")
    private Boolean hostNetwork;
    @JsonProperty("hostPID")
    private Boolean hostPID;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("imagePullSecrets")
    private List<ImagePullSecret<PodTemplateSpec<R>>> imagePullSecrets;
    @JsonProperty("initContainers")
    private List<Container<PodTemplateSpec<R>>> initContainers;
    @JsonProperty("nodeName")
    private String nodeName;
    @JsonProperty("nodeSelector")
    private Map<String, String> nodeSelector;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("priorityClassName")
    private String priorityClassName;
    @JsonProperty("readinessGates")
    private List<ReadinessGate<PodTemplateSpec<R>>> readinessGates;
    /**
     * Restart policy for all containers within the pod. One of Always, OnFailure,
     */
    @JsonProperty("restartPolicy")
    private String restartPolicy;
    @JsonProperty("runtimeClassName")
    private String runtimeClassName;
    @JsonProperty("schedulerName")
    private String schedulerName;
    @JsonProperty("securityContext")
    private PodSecurityContext<PodTemplateSpec<R>> securityContext;
    @JsonProperty("serviceAccountName")
    private String serviceAccountName;
    @JsonProperty("shareProcessNamespace")
    private Boolean shareProcessNamespace;
    @JsonProperty("subdomain")
    private String subdomain;
    @JsonProperty("terminationGracePeriodSeconds")
    private Integer terminationGracePeriodSeconds;
    @JsonProperty("tolerations")
    private List<Toleration<PodTemplateSpec<R>>> tolerations;
    @JsonProperty("volumes")
    private List<PodVolume<PodTemplateSpec<R>>> volumes;

    public PodTemplateSpec<R> activeDeadlineSeconds(Integer activeDeadlineSeconds) {
        this.activeDeadlineSeconds = activeDeadlineSeconds;
        return this;
    }

    public Affinity<PodTemplateSpec<R>> affinity() {
        return new Affinity<>(this, this::affinity);
    }

    public PodTemplateSpec<R> affinity(Affinity<PodTemplateSpec<R>> affinity) {
        this.affinity = affinity;
        return this;
    }

    public PodTemplateSpec<R> automountServiceAccountToken(Boolean automountServiceAccountToken) {
        this.automountServiceAccountToken = automountServiceAccountToken;
        return this;
    }

    public PodTemplateSpec<R> addContainer(Container<PodTemplateSpec<R>> container) {
        if (this.containers == null) {
            this.containers = new ArrayList<>();
        }
        this.containers.add(container);
        return this;
    }

    public Container<PodTemplateSpec<R>> addContainer() {
        return new Container<>(this, this::addContainer);
    }

    public DNSConfig<PodTemplateSpec<R>> dnsConfig() {
        return new DNSConfig<>(this, this::dnsConfig);
    }

    public PodTemplateSpec<R> dnsConfig(DNSConfig<PodTemplateSpec<R>> dnsConfig) {
        this.dnsConfig = dnsConfig;
        return this;
    }

    public PodTemplateSpec<R> dnsPolicy(String dnsPolicy) {
        this.dnsPolicy = dnsPolicy;
        return this;
    }

    public PodTemplateSpec<R> dnsPolicy(K8sPodTemplateSpecDnsPolicies dnsPolicy) {
        this.dnsPolicy = dnsPolicy.getType();
        return this;
    }

    public PodTemplateSpec<R> enableServiceLinks(Boolean enableServiceLinks) {
        this.enableServiceLinks = enableServiceLinks;
        return this;
    }


    public PodTemplateSpec<R> addHostAlias(HostAlias<PodTemplateSpec<R>> hostAlias) {
        if (this.hostAliases == null) {
            this.hostAliases = new ArrayList<>();
        }
        this.hostAliases.add(hostAlias);
        return this;
    }

    public HostAlias<PodTemplateSpec<R>> addHostAlias() {
        return new HostAlias<>(this, this::addHostAlias);
    }

    public PodTemplateSpec<R> hostIPC(Boolean hostIPC) {
        this.hostIPC = hostIPC;
        return this;
    }

    public PodTemplateSpec<R> hostNetwork(Boolean hostNetwork) {
        this.hostNetwork = hostNetwork;
        return this;
    }

    public PodTemplateSpec<R> hostPID(Boolean hostPID) {
        this.hostPID = hostPID;
        return this;
    }

    public PodTemplateSpec<R> hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }


    public PodTemplateSpec<R> addImagePullSecret(ImagePullSecret<PodTemplateSpec<R>> imagePullSecret) {
        if (this.imagePullSecrets == null) {
            this.imagePullSecrets = new ArrayList<>();
        }
        this.imagePullSecrets.add(imagePullSecret);
        return this;
    }

    public ImagePullSecret<PodTemplateSpec<R>> addImagePullSecret() {
        return new ImagePullSecret<>(this, this::addImagePullSecret);
    }


    public PodTemplateSpec<R> addInitContainer(Container<PodTemplateSpec<R>> initContainer) {
        if (this.initContainers == null) {
            this.initContainers = new ArrayList<>();
        }
        this.initContainers.add(initContainer);
        return this;
    }

    public Container<PodTemplateSpec<R>> addInitContainer() {
        return new Container<>(this, this::addInitContainer);
    }

    public PodTemplateSpec<R> nodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public PodTemplateSpec<R> addNodeSelector(String key, String value) {
        if (this.nodeSelector == null) {
            this.nodeSelector = new HashMap<>();
        }
        this.nodeSelector.put(key, value);
        return this;
    }

    public PodTemplateSpec<R> nodeSelector(Map<String, String> nodeSelector) {
        this.nodeSelector = nodeSelector;
        return this;
    }

    public PodTemplateSpec<R> priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public PodTemplateSpec<R> priorityClassName(String priorityClassName) {
        this.priorityClassName = priorityClassName;
        return this;
    }


    public PodTemplateSpec<R> addReadinessGate(ReadinessGate<PodTemplateSpec<R>> readinessGate) {
        if (this.readinessGates == null) {
            this.readinessGates = new ArrayList<>();
        }
        this.readinessGates.add(readinessGate);
        return this;
    }

    public ReadinessGate<PodTemplateSpec<R>> addReadinessGate() {
        return new ReadinessGate<>(this, this::addReadinessGate);
    }

    public PodTemplateSpec<R> restartPolicy(String restartPolicy) {
        this.restartPolicy = restartPolicy;
        return this;
    }

    public PodTemplateSpec<R> runtimeClassName(String runtimeClassName) {
        this.runtimeClassName = runtimeClassName;
        return this;
    }

    public PodTemplateSpec<R> schedulerName(String schedulerName) {
        this.schedulerName = schedulerName;
        return this;
    }

    public PodSecurityContext<PodTemplateSpec<R>> podSecurityContext() {
        return new PodSecurityContext<>(this, this::podSecurityContext);
    }

    public PodTemplateSpec<R> podSecurityContext(PodSecurityContext<PodTemplateSpec<R>> podSecurityContext) {
        this.securityContext = podSecurityContext;
        return this;
    }

    public PodTemplateSpec<R> serviceAccountName(String serviceAccountName) {
        this.serviceAccountName = serviceAccountName;
        return this;
    }

    public PodTemplateSpec<R> shareProcessNamespace(Boolean shareProcessNamespace) {
        this.shareProcessNamespace = shareProcessNamespace;
        return this;
    }

    public PodTemplateSpec<R> subdomain(String subdomain) {
        this.subdomain = subdomain;
        return this;
    }

    public PodTemplateSpec<R> terminationGracePeriodSeconds(Integer terminationGracePeriodSeconds) {
        this.terminationGracePeriodSeconds = terminationGracePeriodSeconds;
        return this;
    }

    public PodTemplateSpec<R> addToleration(Toleration<PodTemplateSpec<R>> toleration) {
        if (this.tolerations == null) {
            this.tolerations = new ArrayList<>();
        }
        this.tolerations.add(toleration);
        return this;
    }

    public Toleration<PodTemplateSpec<R>> addToleration() {
        return new Toleration<>(this, this::addToleration);
    }

    public PodTemplateSpec<R> addVolume(PodVolume<PodTemplateSpec<R>> podVolume) {
        if (this.volumes == null) {
            this.volumes = new ArrayList<>();
        }
        this.volumes.add(podVolume);
        return this;
    }

    public PodVolume<PodTemplateSpec<R>> addVolume() {
        return new PodVolume<>(this, this::addVolume);
    }

    public R buildPodTemplateSpec() {
        listener.apply(this);
        return result;
    }
}
