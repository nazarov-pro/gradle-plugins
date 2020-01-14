package mt.bettech.bs.gradle;

import mt.bettech.bs.gradle.tasks.DockerFileTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class DockerFilePluginTest {

    @Test
    public void dockerfile_plugin_should_add_task_to_project() {
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("dockerfile.plugin");
        assertTrue(project.getTasks().getByName("generateDockerFile") instanceof DockerFileTask);
    }

    @Test
    public void dockerfile_plugin_should_be_able_to_add_task_to_project() {
        Project project = ProjectBuilder.builder().build();
        Map<String, Object> map = new HashMap<>();
        map.put("type", DockerFileTask.class);
        Task testTask = project.task(map, "generateDockerFile");
        project.setVersion("1.0.0");
        assertTrue(testTask instanceof DockerFileTask);
        DockerFileTask dockerFileTask = (DockerFileTask) testTask;
        dockerFileTask.apply();
    }
}

