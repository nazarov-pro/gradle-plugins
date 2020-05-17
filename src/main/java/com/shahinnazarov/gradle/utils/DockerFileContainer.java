package com.shahinnazarov.gradle.utils;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.extensions.DockerFileConfig;
import com.shahinnazarov.gradle.models.DockerStage;
import com.shahinnazarov.gradle.models.DockerStageStep;

import java.util.*;

public class DockerFileContainer {

    public static DockerFile getForJavaDockerFileForGradle(String appFileName) {
        DockerFile dockerFile = new DockerFile();

        DockerStage stage = new DockerStage();
        stage.setFromImage(DockerStage.DockerImages.OPENJDK.getImageWithDefaultVersion());
        stage.setBuildLabel("main");

        String copyExecutableFileCommand = String.format("COPY /libs/%s /app/application.jar", appFileName);
        String setWorkDir = "WORKDIR /app";
        String executeJar = "ENTRYPOINT exec java $JAVA_OPTS -jar application.jar";

        DockerStageStep step = new DockerStageStep();
        step.setSubSteps(Arrays.asList(setWorkDir, copyExecutableFileCommand, executeJar));
        stage.setSteps(Arrays.asList(step));
        dockerFile.setStages(Arrays.asList(stage));
        dockerFile.setDefaultFileName("Dockerfile");
        return dockerFile;
    }

    public static DockerFile getConfiguredDockerFile(DockerFileConfig dockerFileConfig, String appFileName) {
        DockerFile dockerFile = new DockerFile();
        String image = DockerStage.DockerImages.OPENJDK.getImageWithDefaultVersion();
        if(dockerFileConfig.getImage() != null) {
            image = dockerFileConfig.getImage();
        }

        String buildLabel = "main";
        if(dockerFileConfig.getBuildLabel() != null) {
            buildLabel = dockerFileConfig.getBuildLabel();
        }

        String workDir = "/app";
        if(dockerFileConfig.getWorkDir() != null) {
            workDir = dockerFileConfig.getWorkDir();
        }

        String args = "$JAVA_OPTS";
        if(dockerFileConfig.getArgs() != null) {
            args = dockerFileConfig.getArgs();
        }

        String dockerFileName = "Dockerfile";
        if(dockerFileConfig.getOutputFileName() != null) {
            dockerFileName = dockerFileConfig.getOutputFileName();
        }

        DockerStage stage = new DockerStage();
        stage.setFromImage(image);
        stage.setBuildLabel(buildLabel);

        List<String> subSteps = new ArrayList<>();

        subSteps.add(String.format("COPY /libs/%s %s/application.jar", appFileName, workDir));
        subSteps.add(String.format("WORKDIR %s", workDir));

        if(dockerFileConfig.getRunAsUser() != null) {
            String runAsUser = dockerFileConfig.getRunAsUser();

            String runInGroup = runAsUser;
            if(dockerFileConfig.getRunInGroup() != null) {
                runInGroup = dockerFileConfig.getRunInGroup();
            }

            int runAsUserId = 1000;
            if(dockerFileConfig.getRunAsUserId() != null) {
                runAsUserId = dockerFileConfig.getRunAsUserId();
            }

            int runInGroupId = runAsUserId;
            if(dockerFileConfig.getRunInGroupId() != null) {
                runInGroupId = dockerFileConfig.getRunInGroupId();
            }

            subSteps.add(String.format(
               "RUN groupadd -g %d %s && useradd -r -u %d -g %s %s", runInGroupId, runInGroup,
                    runAsUserId, runInGroup, runAsUser
            ));

            subSteps.add(String.format(
                    "USER %s", runAsUser
            ));

        }
        subSteps.add(String.format("ENTRYPOINT exec java %s -jar application.jar", args));

        DockerStageStep step = new DockerStageStep();
        step.setSubSteps(subSteps);

        stage.setSteps(Arrays.asList(step));
        dockerFile.setStages(Arrays.asList(stage));
        dockerFile.setDefaultFileName(dockerFileName);
        return dockerFile;
    }

}
