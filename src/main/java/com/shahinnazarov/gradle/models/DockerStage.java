package com.shahinnazarov.gradle.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Data
public class DockerStage {
    private String fromImage;
    private String buildLabel;
    private Map<String, String> labels;
    private Map<String, String> envs;
    private List<DockerStageStep> steps;

    @AllArgsConstructor
    @Getter
    public enum DockerImages {
        UBUNTU("ubuntu", "18.04"),
        OPENJDK("openjdk", "8-jre-alpine");

        private String name;
        private String defaultVersion;

        public static final String LATEST = "latest";

        public String getImageWithDefaultVersion() {
            return getName() + ":" + getDefaultVersion();
        }

        public String getImageWithLatestVersion() {
            return getName() + ":" + LATEST;
        }

        public String getImageWithParticularVersion(String version) {
            return getName() + ":" + version;
        }
    }

}
