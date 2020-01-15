package com.shahinnazarov.gradle.plugins;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.tasks.DockerFileTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DockerFilePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("DockerFile", DockerFile.class);
        project.getTasks().create("generateDockerFile", DockerFileTask.class);
    }
}
