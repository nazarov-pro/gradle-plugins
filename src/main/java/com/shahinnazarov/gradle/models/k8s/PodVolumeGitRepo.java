package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        {
                "directory",
                "repository",
                "sizeLimit",
        }
)
public final class PodVolumeGitRepo<R extends DefaultK8sObject> extends AbstractK8sObject<R, PodVolumeGitRepo<R>> {

    public PodVolumeGitRepo(R result, ChangeListener<PodVolumeGitRepo<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("directory")
    private String directory;
    @JsonProperty("repository")
    private String repository;
    @JsonProperty("revision")
    private String revision;

    public PodVolumeGitRepo<R> directory(String directory) {
        this.directory = directory;
        return this;
    }

    public PodVolumeGitRepo<R> repository(String repository) {
        this.repository = repository;
        return this;
    }

    public PodVolumeGitRepo<R> revision(String revision) {
        this.revision = revision;
        return this;
    }

    public R buildGitRepo() {
        listener.apply(this);
        return result;
    }
}
