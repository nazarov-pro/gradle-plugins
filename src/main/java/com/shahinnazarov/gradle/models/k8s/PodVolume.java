package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.TreeNode;

@JsonPropertyOrder(
        {
                "configMap",
                "emptyDir",
                "gitRepo",
                "hostPath",
                "name",
                "persistentVolumeClaim",
                "secret",
        }
)
public final class PodVolume<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolume<R>> {

    public PodVolume(R result, ChangeListener<PodVolume<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("configMap")
    private PodVolumeConfigMap<PodVolume<R>> configMap;
    @JsonProperty("emptyDir")
    private PodVolumeEmptyDir<PodVolume<R>> emptyDir;
    @JsonProperty("gitRepo")
    private PodVolumeGitRepo<PodVolume<R>> gitRepo;
    @JsonProperty("hostPath")
    private PodVolumeHostPath<PodVolume<R>> hostPath;
    @JsonProperty("name")
    private String name;
    @JsonProperty("persistentVolumeClaim")
    private PodVolumePersistentVolumeClaim<PodVolume<R>> persistentVolumeClaim;
    @JsonProperty("secret")
    private PodVolumeSecret<PodVolume<R>> secret;


    public PodVolume<R> name(String name) {
        this.name = name;
        return this;
    }

    public PodVolumePersistentVolumeClaim<PodVolume<R>> pvc() {
        return new PodVolumePersistentVolumeClaim<>(this, this::persistentVolumeClaim);
    }

    public PodVolume<R> persistentVolumeClaim(PodVolumePersistentVolumeClaim<PodVolume<R>> persistentVolumeClaim) {
        this.persistentVolumeClaim = persistentVolumeClaim;
        return this;
    }

    public PodVolumeConfigMap<PodVolume<R>> configMap() {
        return new PodVolumeConfigMap<>(this, this::configMap);
    }

    public PodVolume<R> configMap(PodVolumeConfigMap<PodVolume<R>> configMap) {
        this.configMap = configMap;
        return this;
    }

    public PodVolumeEmptyDir<PodVolume<R>> emptyDir() {
        return new PodVolumeEmptyDir<>(this, this::emptyDir);
    }

    public PodVolume<R> emptyDir(PodVolumeEmptyDir<PodVolume<R>> emptyDir) {
        this.emptyDir = emptyDir;
        return this;
    }

    public PodVolumeGitRepo<PodVolume<R>> gitRepo() {
        return new PodVolumeGitRepo<>(this, this::gitRepo);
    }

    public PodVolume<R> gitRepo(PodVolumeGitRepo<PodVolume<R>> gitRepo) {
        this.gitRepo = gitRepo;
        return this;
    }

    public PodVolumeHostPath<PodVolume<R>> hostPath() {
        return new PodVolumeHostPath<>(this, this::hostPath);
    }

    public PodVolume<R> hostPath(PodVolumeHostPath<PodVolume<R>> hostPath) {
        this.hostPath = hostPath;
        return this;
    }

    public PodVolumeSecret<PodVolume<R>> secret() {
        return new PodVolumeSecret<>(this, this::secret);
    }

    public PodVolume<R> secret(PodVolumeSecret<PodVolume<R>> secret) {
        this.secret = secret;
        return this;
    }

    public R buildPodVolume() {
        listener.apply(this);
        return result;
    }
}
