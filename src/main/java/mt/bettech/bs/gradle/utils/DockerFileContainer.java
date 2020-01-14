package mt.bettech.bs.gradle.utils;

import mt.bettech.bs.gradle.extensions.DockerFile;
import mt.bettech.bs.gradle.models.DockerStage;
import mt.bettech.bs.gradle.models.DockerStageStep;

import java.util.*;

public class DockerFileContainer {

    public static DockerFile getForJavaDockerFileForGradle(String appFileName) {
        DockerFile dockerFile = new DockerFile();
        DockerStage stage = new DockerStage();
        stage.setFromImage(DockerStage.DockerImages.OPENJDK.getImageWithDefaultVersion());
        stage.setBuildLabel("main");
        String copyExecutableFileCommand = String.format("COPY /build/libs/%s /app/application.jar",
                appFileName);
        String setWorkDir = "WORKDIR /app";
        String executeJar = "ENTRYPOINT exec java -jar application.jar";
        DockerStageStep step = new DockerStageStep();
        step.setSteps(Arrays.asList(copyExecutableFileCommand, setWorkDir, executeJar));
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
