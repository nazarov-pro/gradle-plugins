package com.shahinnazarov.gradle.utils;

import com.shahinnazarov.gradle.extensions.DockerFile;

public class DockerFileGenerator {

    public String generate(DockerFile dockerFile) {
        StringBuilder result = new StringBuilder();
        if(dockerFile.getStages() != null) {
            dockerFile.getStages().forEach(
                    dockerStage -> {
                        String buildLabel = "";
                        if (dockerStage.getBuildLabel() != null && !dockerStage.getBuildLabel().isEmpty()) {
                            buildLabel = "AS ".concat(dockerStage.getBuildLabel());
                        }

                        result.append(String.format("FROM %s %s\n", dockerStage.getFromImage(), buildLabel));

                        if (dockerStage.getLabels() != null) {
                            dockerStage.getLabels().forEach((k, v) ->
                                    result.append(String.format("LABEL %s=\"%s\"\n", k, v))
                            );
                        }

                        if (dockerStage.getEnvs() != null) {
                            dockerStage.getEnvs().forEach((k, v) ->
                                    result.append(String.format("ENVIRONMENT %s=\"%s\"\n", k, v))
                            );
                        }

                        if (dockerStage.getSteps() != null) {
                            dockerStage.getSteps().forEach(
                                    step -> {
                                        if (step != null) {
                                            step.getSteps().forEach(line -> {
                                                result.append(line.concat("\n"));
                                            });
                                        }
                                    }
                            );
                        }

                        result.append("\n");
                    }
            );
        }
        return result.toString().trim();
    }

}
