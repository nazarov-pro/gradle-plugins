package com.shahinnazarov.gradle.utils.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class K8sApplicationGeneration {
    public static final String APP_PREFIX = "k8s.app.";

    public String generate(Properties properties) {
        String result = "";
        Map<String, String> applicationProperties = new HashMap<>();
        properties.entrySet().stream()
                .filter(entry -> entry.getKey().toString().startsWith(APP_PREFIX))
                .forEach(entry ->
                        applicationProperties.put(entry.getKey().toString().substring(APP_PREFIX.length()),
                                entry.getValue().toString())
                );
        if (applicationProperties.get("type") == null) {
            return result;
        }
        switch (applicationProperties.get("type")) {
            case "Deployment":
            case "deploy":
            case "deployment":
                return new K8sDeploymentGeneration().generateDeployment(applicationProperties);

        }
        return result;
    }
}
