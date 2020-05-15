package com.shahinnazarov.gradle.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.Namespace;
import com.shahinnazarov.gradle.models.k8s.PersistentVolumeClaim;
import com.shahinnazarov.gradle.models.k8s.Service;
import com.shahinnazarov.gradle.utils.generate.K8sDeploymentGenerationImpl;
import com.shahinnazarov.gradle.utils.generate.K8sNamespaceGenerationImpl;
import com.shahinnazarov.gradle.utils.generate.K8sPersistentVolumeClaimGenerationImpl;
import com.shahinnazarov.gradle.utils.generate.K8sServiceGenerationImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class K8sContext {
    public static K8sContext k8sContext;

    private Map<String, Namespace> namespaceMap;
    private Map<String, PersistentVolumeClaim> persistentVolumeClaimMap;
    private Map<String, Service> serviceMap;
    private Map<String, Deployment> deploymentMap;

    private K8sContext(Properties properties) {
        namespaceMap = new HashMap<>();
        persistentVolumeClaimMap = new HashMap<>();
        serviceMap = new HashMap<>();
        deploymentMap = new HashMap<>();
        generate(properties);
    }

    public static K8sContext getInstance() {
        if (k8sContext != null) {
            return k8sContext;
        }
        throw new RuntimeException("K8sContext has not initialized.");
    }

    public static void initialize(Properties properties) {
        if (k8sContext != null) {
            throw new RuntimeException("K8sContext has already been initialized.");
        }
        k8sContext = new K8sContext(properties);
    }


    private void generate(Properties properties) {
        Map<String, Properties> propertiesMap = new HashMap<>();

        properties
                .entrySet()
                .stream()
                .forEach(
                        entry -> {
                            String groupId = extractGroupId(entry.getKey().toString());
                            propertiesMap.compute(groupId,
                                    (key, value) -> {
                                        if (value == null) {
                                            value = new Properties();
                                        }
                                        value.put(entry.getKey(), entry.getValue());
                                        return value;
                                    }
                            );
                        }
                );

        propertiesMap.forEach((key, value) -> {
            switch (ContextTypes.getContextType(key)) {
                case NAMESPACE:
                    namespaceMap.put(key, new K8sNamespaceGenerationImpl().generate(key, value));
                    break;
                case PERSISTENT_VOLUME_CLAIM:
                    persistentVolumeClaimMap.put(key, new K8sPersistentVolumeClaimGenerationImpl().generate(key, value));
                    break;
                case SERVICE:
                    serviceMap.put(key, new K8sServiceGenerationImpl().generate(key, value));
                    break;
                case DEPLOYMENT:
                    deploymentMap.put(key, new K8sDeploymentGenerationImpl().generate(key, value));
            }
        });
    }

    private String extractGroupId(String key) {
        return ContextTypes.getGroupId(key);
    }

    public Namespace getNamespace(String key) {
        return namespaceMap.get(ContextTypes.NAMESPACE.generateGroupId(key));
    }

    public Collection<Namespace> getNamespaces() {
        return namespaceMap.values();
    }

    public PersistentVolumeClaim getPersistentVolumeClaim(String key) {
        return persistentVolumeClaimMap.get(ContextTypes.PERSISTENT_VOLUME_CLAIM.generateGroupId(key));
    }

    public Collection<PersistentVolumeClaim> getPersistentVolumeClaims() {
        return persistentVolumeClaimMap.values();
    }

    public Deployment getDeployment(String key) {
        return deploymentMap.get(ContextTypes.DEPLOYMENT.generateGroupId(key));
    }

    public Collection<Deployment> getDeployments() {
        return deploymentMap.values();
    }

    public Service getService(String key) {
        return serviceMap.get(ContextTypes.SERVICE.generateGroupId(key));
    }

    public Collection<Service> getServices() {
        return serviceMap.values();
    }

    public String getAsYaml(Object object) {
        ObjectMapper yamlMapper = new ObjectMapper(
                new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        );
        yamlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        yamlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String result = "";
        try {
            result = yamlMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
