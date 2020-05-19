package com.shahinnazarov.gradle.plugins;

import com.shahinnazarov.gradle.extensions.K8sFile;
import com.shahinnazarov.gradle.tasks.K8sTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.util.Set;

public class K8sPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("K8sFile", K8sFile.class);
        project.getTasks().create("generateK8sFile", K8sTask.class);
        Set<Task> buildTaskSet = project.getTasksByName("build", true);
        buildTaskSet.forEach(buildTask -> buildTask.finalizedBy("generateK8sFile"));
    }
}
