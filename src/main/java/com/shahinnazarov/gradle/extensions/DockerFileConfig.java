package com.shahinnazarov.gradle.extensions;

import lombok.Data;

@Data
public class DockerFileConfig {
    private String image;
    private String buildLabel;

    private String runAsUser;
    private Integer runAsUserId;
    private String runInGroup;
    private Integer runInGroupId;

    private String workDir;
    private String args;

    private String outputFileName;

}
