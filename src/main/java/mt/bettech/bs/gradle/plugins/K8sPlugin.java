package mt.bettech.bs.gradle.plugins;

import mt.bettech.bs.gradle.extensions.K8sFile;
import mt.bettech.bs.gradle.tasks.K8sTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class K8sPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("K8sFile", K8sFile.class);
        project.getTasks().create("generateK8sFile", K8sTask.class);
    }
}
