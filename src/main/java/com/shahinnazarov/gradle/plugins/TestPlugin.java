package com.shahinnazarov.gradle.plugins;

import com.shahinnazarov.gradle.extensions.TestExtension;
import com.shahinnazarov.gradle.tasks.TestTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class TestPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("testSettings", TestExtension.class);
        project.getTasks().create("testTask", TestTask.class);
    }
}
