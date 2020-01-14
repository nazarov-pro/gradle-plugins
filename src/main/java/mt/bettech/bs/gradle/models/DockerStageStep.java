package mt.bettech.bs.gradle.models;

import lombok.Data;

import java.util.List;

@Data
public class DockerStageStep {
    private List<String> steps;
}
