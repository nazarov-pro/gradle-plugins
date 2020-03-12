package com.shahinnazarov.gradle.utils;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.models.DockerStage;
import com.shahinnazarov.gradle.models.DockerStageStep;

import java.util.*;

public class DockerFileContainer {

    public static DockerFile getForJavaDockerFileForGradle(String appFileName) {
        DockerFile dockerFile = new DockerFile();
        DockerStage stage = new DockerStage();
        stage.setFromImage(DockerStage.DockerImages.OPENJDK.getImageWithDefaultVersion());
        stage.setBuildLabel("main");
        String copyExecutableFileCommand = String.format("COPY /libs/%s /app/application.jar",
                appFileName);
        String setWorkDir = "WORKDIR /app";
        String executeJar = "ENTRYPOINT exec java -jar application.jar";
        DockerStageStep step = new DockerStageStep();
        step.setSteps(Arrays.asList(setWorkDir, copyExecutableFileCommand, executeJar));
        stage.setSteps(Arrays.asList(step));
        dockerFile.setStages(Arrays.asList(stage));
        dockerFile.setDefaultFileName("Dockerfile");
        return dockerFile;
    }

    private static Map<String, String> getMapForOneEntry(String key, String value) {
        Map<String, String> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }
}
