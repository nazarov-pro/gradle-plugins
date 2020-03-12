package com.shahinnazarov.gradle.tasks;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.utils.DockerFileGenerator;
import com.shahinnazarov.gradle.utils.DockerFileContainer;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class DockerFileTask extends DefaultTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Getter
    @OutputFile
    private File outputFile;

    @Setter
    @Getter
    @Optional
    @OutputFile
    private File buildInfoOutputFile;

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

            Path dockerFilePath = outputFile.toPath();
            Files.deleteIfExists(dockerFilePath);
            Files.write(dockerFilePath, content.getBytes());

            if(buildInfoOutputFile != null) {
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                Properties properties = new Properties();
                properties.setProperty("timestamp", timestamp);
                properties.setProperty("applicationName", getProject().getName());
                properties.setProperty("version", getProject().getVersion().toString());
                properties.setProperty("group", getProject().getGroup().toString());

                Path buildInfoFilePath = buildInfoOutputFile.toPath();
                Files.deleteIfExists(buildInfoFilePath);
                StringBuilder buildInfoContent = new StringBuilder();
                properties.forEach((k,v) -> buildInfoContent.append(String.format("%s=%s\n", k, v)));
                Files.write(buildInfoFilePath, buildInfoContent.toString().getBytes());
            }

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
