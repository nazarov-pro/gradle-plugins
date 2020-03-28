package com.shahinnazarov.gradle.utils.generate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.shahinnazarov.gradle.models.k8s.Metadata;
import com.shahinnazarov.gradle.models.k8s.Namespace;

import javax.naming.Name;
import java.util.*;

public class K8sNamespaceGeneration {
    public static final String PROPERTY_PREFIX = "k8s.nm";

    public String generate(Properties properties) throws JsonProcessingException {
        String result = "";
        List<Map<String, String>> namespaces = new ArrayList<>();
        ObjectMapper yamlMapper = new ObjectMapper(
                new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        );
        yamlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        yamlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        properties.forEach(
                (keyObject, valueObject) -> {
                    String key = keyObject.toString();
                    String value = valueObject.toString();

                    if (key.startsWith(PROPERTY_PREFIX)) {
                        Integer index =
                                Integer.parseInt(key.substring(PROPERTY_PREFIX.length() + 1, key.indexOf(']')));
                        Map<String, String> namespace;
                        if (namespaces.size() >= index + 1) {
                            namespace = namespaces.get(index);
                            namespace.put(key.substring(key.indexOf(']') + 2), value);
                        } else {
                            namespace = new TreeMap<>();
                            namespace.put(key.substring(key.indexOf(']') + 2), value);
                            namespaces.add(index, namespace);
                        }
                    }
                }
        );

        for (int i = 0; i < namespaces.size(); i++) {
            Map<String, String> namespaceProperties = namespaces.get(i);
            Namespace namespace = Namespace.instance()
                    .metadata()
                    .name(namespaceProperties.get("name"))
                    .labels(getLabels(namespaceProperties))
                    .annotations(getAnnotations(namespaceProperties))
                    .buildMetadata();
            String yamlContent = yamlMapper.writeValueAsString(namespace);
            result = result.concat(yamlContent).concat("---\n");
        }

        return result;
    }

    public Map<String, String> getLabels(Map<String, String> map) {
        Map<String, String> labels = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            String key = map.get(String.format("label[%d].key", i));
            if(key == null) {
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
            if(key == null) {
                break;
            }
            annotations.put(key, map.get(String.format("annotation[%d].value", i)));
        }
        return annotations;
    }
}

