package com.shahinnazarov.gradle.tasks;

import com.shahinnazarov.gradle.extensions.TestExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class TestTask extends DefaultTask {

    @TaskAction
    public void greet() {
        TestExtension extension = getProject().getExtensions()
                .findByType(TestExtension.class);

        if(extension == null) {
            extension = new TestExtension();
        }

        String message = extension.getMessage();
        System.out.println("Hello world, " + message);
    }
}
