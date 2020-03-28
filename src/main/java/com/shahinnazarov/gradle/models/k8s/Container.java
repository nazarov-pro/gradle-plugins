package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "args",
                "command",
                "env",
                "envFrom",
                "image",
                "imagePullPolicy",
                "lifecycle",
                "livenessProbe",
                "name",
                "ports",
                "readinessProbe",
                "resources",
                "securityContext",
                "stdin",
                "stdinOnce",
                "terminationMessagePath",
                "terminationMessagePolicy",
                "tty",
                "volumeDevices",
                "volumeMounts",
                "workingDir",
        }
)
public final class Container<R extends DefaultK8sObject> extends AbstractK8sObject<R, Container<R>> {

    public Container(R result, ChangeListener<Container<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("args")
    private List<String> args;
    @JsonProperty("command")
    private List<String> command;
    @JsonProperty("env")
    private List<Environment<Container<R>>> env;
    @JsonProperty("envFrom")
    private List<EnvironmentFromSource<Container<R>>> envFrom;
    @JsonProperty("image")
    private String image;
    @JsonProperty("imagePullPolicy")
    private String imagePullPolicy;
    @JsonProperty("lifecycle")
    private ContainerLifecycle<Container<R>> lifecycle;
    @JsonProperty("livenessProbe")
    private LivenessProbe<Container<R>> livenessProbe;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ports")
    private List<ContainerPort<Container<R>>> ports;
    @JsonProperty("readinessProbe")
    private ReadinessProbe<Container<R>> readinessProbe;
    @JsonProperty("resources")
    private Resources<Container<R>> resources;
    @JsonProperty("securityContext")
    private SecurityContext<Container<R>> securityContext;
    @JsonProperty("stdin")
    private Boolean stdin;
    @JsonProperty("stdinOnce")
    private Boolean stdinOnce;
    @JsonProperty("terminationMessagePath")
    private String terminationMessagePath;
    @JsonProperty("terminationMessagePolicy")
    private String terminationMessagePolicy;
    @JsonProperty("tty")
    private Boolean tty;
    @JsonProperty("volumeDevices")
    private List<VolumeDevice<Container<R>>> volumeDevices;
    @JsonProperty("volumeMounts")
    private List<VolumeMount<Container<R>>> volumeMounts;
    @JsonProperty("workingDir")
    private String workingDir;

    public static final String IMAGE_PULL_POLICY_ALWAYS = "Always";
    public static final String IMAGE_PULL_POLICY_NEVER = "Never";
    public static final String IMAGE_PULL_POLICY_IF_NOT_PRESENT = "IfNotPresent";

    public static final String TERMINATION_MESSAGE_POLICY_FILE = "File";
    public static final String TERMINATION_MESSAGE_POLICY_FALLBACK_TO_LOG_ON_ERROR =
            "FallbackToLogsOnError";


    public Container<R> args(String... arguments) {
        if (this.args == null) {
            this.args = new ArrayList<>();
        }
        this.args.addAll(Arrays.asList(arguments));
        return this;
    }

    public Container<R> commands(String... commands) {
        if (this.command == null) {
            this.command = new ArrayList<>();
        }
        this.command.addAll(Arrays.asList(commands));
        return this;
    }


    public Environment<Container<R>> addEnv() {
        return new Environment<>(this, this::addEnv);
    }

    public Container<R> addEnv(Environment<Container<R>> environment) {
        if (this.env == null) {
            this.env = new ArrayList<>();
        }
        this.env.add(environment);
        return this;
    }

    public EnvironmentFromSource<Container<R>> addEnvSource() {
        return new EnvironmentFromSource<>(this, this::addEnvSource);
    }

    public Container<R> addEnvSource(EnvironmentFromSource<Container<R>> environmentFromSource) {
        if (this.envFrom == null) {
            this.envFrom = new ArrayList<>();
        }
        this.envFrom.add(environmentFromSource);
        return this;
    }

    public Container<R> image(String image) {
        this.image = image;
        return this;
    }

    public Container<R> imagePullPolicyToAlways() {
        this.imagePullPolicy = IMAGE_PULL_POLICY_ALWAYS;
        return this;
    }

    public Container<R> imagePullPolicyToNever() {
        this.imagePullPolicy = IMAGE_PULL_POLICY_NEVER;
        return this;
    }

    public Container<R> imagePullPolicyToIfNotPresent() {
        this.imagePullPolicy = IMAGE_PULL_POLICY_IF_NOT_PRESENT;
        return this;
    }

    public Container<R> lifecycle(ContainerLifecycle<Container<R>> lifecycle) {
        this.lifecycle = lifecycle;
        return this;
    }

    public ContainerLifecycle<Container<R>> lifecycle() {
        return new ContainerLifecycle<>(this, this::lifecycle);
    }

    public Container<R> livenessProbe(LivenessProbe<Container<R>> livenessProbe) {
        this.livenessProbe = livenessProbe;
        return this;
    }

    public LivenessProbe<Container<R>> livenessProbe() {
        return new LivenessProbe<>(this, this::livenessProbe);
    }

    public Container<R> name(String name) {
        this.name = name;
        return this;
    }

    public ContainerPort<Container<R>> addPort() {
        return new ContainerPort<>(this, this::addPort);
    }

    public Container<R> addPort(ContainerPort<Container<R>> port) {
        if (this.ports == null) {
            this.ports = new ArrayList<>();
        }
        this.ports.add(port);
        return this;
    }


    public Container<R> readinessProbe(ReadinessProbe<Container<R>> readinessProbe) {
        this.readinessProbe = readinessProbe;
        return this;
    }

    public ReadinessProbe<Container<R>> readinessProbe() {
        return new ReadinessProbe<>(this, this::readinessProbe);
    }

    public Container<R> resources(Resources<Container<R>> resources) {
        this.resources = resources;
        return this;
    }

    public Resources<Container<R>> resources() {
        return new Resources<>(this, this::resources);
    }

    public Container<R> securityContext(SecurityContext<Container<R>> securityContext) {
        this.securityContext = securityContext;
        return this;
    }

    public SecurityContext<Container<R>> securityContext() {
        return new SecurityContext<>(this, this::securityContext);
    }

    public Container<R> stdin(Boolean stdin) {
        this.stdin = stdin;
        return this;
    }

    public Container<R> stdinOnce(Boolean stdinOnce) {
        this.stdinOnce = stdinOnce;
        return this;
    }

    public Container<R> terminationMessagePath(String terminationMessagePath) {
        this.terminationMessagePath = terminationMessagePath;
        return this;
    }

    public Container<R> terminationMessagePolicyToFile() {
        this.terminationMessagePolicy = TERMINATION_MESSAGE_POLICY_FILE;
        return this;
    }

    public Container<R> terminationMessagePolicyToFallbackToLogsOnError() {
        this.terminationMessagePolicy = TERMINATION_MESSAGE_POLICY_FALLBACK_TO_LOG_ON_ERROR;
        return this;
    }

    public Container<R> tty(Boolean tty) {
        this.tty = tty;
        return this;
    }

    public VolumeDevice<Container<R>> addVolumeDevice() {
        return new VolumeDevice<>(this, this::addVolumeDevice);
    }

    public Container<R> addVolumeDevice(VolumeDevice<Container<R>> volumeDevice) {
        if (this.volumeDevices == null) {
            this.volumeDevices = new ArrayList<>();
        }
        this.volumeDevices.add(volumeDevice);
        return this;
    }

    public VolumeMount<Container<R>> addVolumeMount() {
        return new VolumeMount<>(this, this::addVolumeMount);
    }

    public Container<R> addVolumeMount(VolumeMount<Container<R>> volumeMount) {
        if (this.volumeMounts == null) {
            this.volumeMounts = new ArrayList<>();
        }
        this.volumeMounts.add(volumeMount);
        return this;
    }

    public Container<R> workingDir(String workingDir) {
        this.workingDir = workingDir;
        return this;
    }


    public R buildContainer() {
        listener.apply(this);
        return result;
    }
}
