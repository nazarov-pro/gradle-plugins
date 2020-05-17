package com.shahinnazarov.gradle.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.*;
import com.shahinnazarov.gradle.utils.generate.impl.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class K8sContext {
    public static K8sContext k8sContext;

    private final Map<String, Namespace> namespaceMap;
    private final Map<String, PersistentVolumeClaim> persistentVolumeClaimMap;
    private final Map<String, Service> serviceMap;
    private final Map<String, Deployment> deploymentMap;
    private final Map<String, StatefulSet> stringStatefulSetMap;

    private static Properties PROPERTIES_CONTEXT;

    private K8sContext(Properties properties) {
        namespaceMap = new HashMap<>();
        persistentVolumeClaimMap = new HashMap<>();
        serviceMap = new HashMap<>();
        deploymentMap = new HashMap<>();
        stringStatefulSetMap = new HashMap<>();
        PROPERTIES_CONTEXT = properties;
        generate(properties);
    }

    public static Properties getPropertiesContext() {
        return PROPERTIES_CONTEXT;
    }

    public static K8sContext getInstance() {
        if (k8sContext != null) {
            return k8sContext;
        }
        throw new RuntimeException("K8sContext has not initialized.");
    }

    public static void initialize(Properties properties) {
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
                    namespaceMap.put(key, new NamespaceGenerationImpl().generate(key, value));
                    break;
                case PERSISTENT_VOLUME_CLAIM:
                    persistentVolumeClaimMap.put(key, new PersistentVolumeClaimGenerationImpl().generate(key, value));
                    break;
                case SERVICE:
                    serviceMap.put(key, new ServiceGenerationImpl().generate(key, value));
                    break;
                case DEPLOYMENT:
                    deploymentMap.put(key, new DeploymentGenerationImpl().generate(key, value));
                    break;
                case STATEFUL_SET:
                    stringStatefulSetMap.put(key, new StatefulSetGenerationImpl().generate(key, value));
                    break;
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

    public StatefulSet getStatefulSet(String key) {
        return stringStatefulSetMap.get(ContextTypes.STATEFUL_SET.generateGroupId(key));
    }

    public Collection<StatefulSet> getStatefulSets() {
        return stringStatefulSetMap.values();
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

    public String getAsYaml() {
        StringBuilder result = new StringBuilder();
        String separator = Constants.YAML_RESOURCE_SEPARATOR + "\n\n";

        getNamespaces().forEach(namespace -> {
            result.append(getAsYaml(namespace));
            result.append(separator);
        });

        getPersistentVolumeClaims().forEach(persistentVolumeClaim -> {
            result.append(getAsYaml(persistentVolumeClaim));
            result.append(separator);
        });

        getServices().forEach(service -> {
            result.append(getAsYaml(service));
            result.append(separator);
        });

        getDeployments().forEach(deployment -> {
            result.append(getAsYaml(deployment));
            result.append(separator);
        });


        getStatefulSets().forEach(statefulSet -> {
            result.append(getAsYaml(statefulSet));
            result.append(separator);
        });

        if(result.length() > separator.length()) {
            return result.substring(0, (result.length() - separator.length()));
        }

        return result.toString();
    }

}
