package mt.bettech.bs.gradle.extensions;

import mt.bettech.bs.gradle.models.DockerStage;
import lombok.Data;

import java.util.List;

@Data
public class DockerFile {
    private String defaultFileName;
    private List<DockerStage> stages;
}
