package mt.bettech.bs.gradle.plugins;

import mt.bettech.bs.gradle.extensions.DockerFile;
import mt.bettech.bs.gradle.tasks.DockerFileTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DockerFilePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("DockerFile", DockerFile.class);
        project.getTasks().create("generateDockerFile", DockerFileTask.class);
    }
}
