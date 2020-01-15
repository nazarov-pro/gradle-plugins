package com.shahinnazarov.gradle.plugins;

import com.shahinnazarov.gradle.extensions.K8sFile;
import com.shahinnazarov.gradle.tasks.K8sTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class K8sPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("K8sFile", K8sFile.class);
//        project.getExtensions().create("K8sFileBuilder", K8sFileBuilder.class);
        project.getTasks().create("generateK8sFile", K8sTask.class);
    }
}
