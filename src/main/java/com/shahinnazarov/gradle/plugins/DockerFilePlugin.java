package com.shahinnazarov.gradle.plugins;

import com.shahinnazarov.gradle.extensions.DockerFile;
import com.shahinnazarov.gradle.extensions.DockerFileConfig;
import com.shahinnazarov.gradle.tasks.DockerFileTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.Set;

public class DockerFilePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("DockerFile", DockerFile.class);
        project.getExtensions().create("DockerFileConfig", DockerFileConfig.class);
        project.getTasks().create("generateDockerFile", DockerFileTask.class);

        Set<Task> buildTaskSet = project.getTasksByName("build", true);
        buildTaskSet.forEach(buildTask -> buildTask.finalizedBy("generateK8sFile"));
    }
}
