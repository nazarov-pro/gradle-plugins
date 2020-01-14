package mt.bettech.bs.gradle.tasks;

import mt.bettech.bs.gradle.extensions.K8sFile;
import mt.bettech.bs.gradle.utils.K8sFileGenerator;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class K8sTask extends DefaultTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Getter
    @OutputFile
    private File outputFile;

    @TaskAction
    public void generateK8sFile() {
        K8sFile k8sFile = getK8sFile();
        log.info("K8s Apply initialized");
        K8sFileGenerator k8sFileGenerator = new K8sFileGenerator();
        String content = k8sFileGenerator.generate(k8sFile);
        try {
            if(outputFile == null) {
                outputFile = new File("deployment");
            }
            Path resolve = outputFile.toPath();
            Files.write(resolve, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private K8sFile getK8sFile() {
        K8sFile k8sFile = getProject().getExtensions()
                .findByType(K8sFile.class);
        return k8sFile;
    }
}
