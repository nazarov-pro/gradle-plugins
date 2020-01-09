package com.shahinnazarov.gradle.extensions;

import com.shahinnazarov.gradle.models.DockerStage;
import lombok.Data;

import java.util.List;

@Data
public class DockerFile {
    private String defaultFileName;
    private List<DockerStage> stages;
}
