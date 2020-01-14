package mt.bettech.bs.gradle;

import mt.bettech.bs.gradle.tasks.TestTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class TestPluginTest {

    @Test
    public void test_plugin_should_add_task_to_project() {
        Project project = ProjectBuilder.builder().build();
        project.getPlugins().apply("com.shahinnazarov.gradle.plugin.test");
        assertTrue(project.getTasks().getByName("testTask") instanceof TestTask);
    }

    @Test
    public void test_plugin_should_be_able_to_add_task_to_project() {
        Project project = ProjectBuilder.builder().build();
        Map<String, Object> map = new HashMap<>();
        map.put("type", TestTask.class);
        Task testTask = project.task(map, "testTask");
        assertTrue(testTask instanceof TestTask);
    }
}

