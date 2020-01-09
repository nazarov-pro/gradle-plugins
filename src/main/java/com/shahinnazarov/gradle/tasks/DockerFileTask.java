package com.shahinnazarov.gradle.tasks;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.utils.DockerFileContainer;
import com.shahinnazarov.gradle.utils.DockerFileGenerator;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DockerFileTask extends DefaultTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Getter
    @OutputFile
    private File outputFile;

    @TaskAction
    public void apply() {
        DockerFile dockerFile = getDockerFile();
        log.info("Apply initialized : {}", dockerFile.getDefaultFileName());
        DockerFileGenerator dockerFileGenerator = new DockerFileGenerator();
        String content = dockerFileGenerator.generate(dockerFile);
        try {
            if(outputFile == null) {
                outputFile = new File(dockerFile.getDefaultFileName());
            }
            Path resolve = outputFile.toPath();
            Files.write(resolve, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DockerFile getDockerFile() {
        String name = getProject().getName();
        String version = getProject().getVersion().toString();
        String fileName = String.format("%s-%s.jar", name, version);
        DockerFile dockerFile = getProject().getExtensions()
                .findByType(DockerFile.class);

        if (dockerFile == null || dockerFile.getStages() == null) {
            dockerFile = new DockerFileContainer().getForJavaDockerFileForGradle(fileName);
        }
        return dockerFile;
    }

}
