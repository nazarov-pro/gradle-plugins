package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.DeploymentSpecification;

import java.util.LinkedHashMap;
import java.util.Map;

public class K8sDeploymentGeneration {
    public String generateDeployment(Map<String, String> properties) {
        String result = "";
        String appName = properties.get("name");
        DeploymentSpecification<Deployment> deployment = Deployment.instance()
                .metadata()
                .name(appName)
                .labels(getLabels(properties))
                .annotations(getAnnotations(properties))
                .buildMetadata()
                .spec();

        String strategy = properties.get("strategy");
        if (strategy != null) {
            switch (strategy.toLowerCase()) {
                case "rollingupdate":
                    String maxSurge = properties.get("strategy.rollingUpdate.maxSurge");
                    String maxUnavailable = properties.get("strategy.rollingUpdate.maxUnavailable");
                    deployment.deploymentStrategy()
                            .rollingUpdate().maxSurge(maxSurge)
                            .maxUnavailable(maxUnavailable)
                            .buildRollingUpdate()
                            .buildDeploymentStrategy();
                    break;
                case "recreate":
                    deployment.deploymentStrategy().recreate().buildDeploymentStrategy();
                    break;
                default:
                    //log
            }
        }



        Integer replicas = Integer.parseInt(properties.getOrDefault("replicas", "1"));
        deployment.selector().addMatchLabel("app", appName).buildSelector()
                .replicas(replicas)
                .buildSpecification()
                .buildDeployment();
        return result;
    }

    public Map<String, String> getLabels(Map<String, String> map) {
        Map<String, String> labels = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            String key = map.get(String.format("label[%d].key", i));
            if (key == null) {
                break;
            }
            labels.put(key, map.get(String.format("label[%d].value", i)));
        }
        return labels;
    }

    public Map<String, String> getAnnotations(Map<String, String> map) {
        Map<String, String> annotations = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            String key = map.get(String.format("annotation[%d].key", i));
            if (key == null) {
                break;
            }
            annotations.put(key, map.get(String.format("annotation[%d].value", i)));
        }
        return annotations;
    }
}
