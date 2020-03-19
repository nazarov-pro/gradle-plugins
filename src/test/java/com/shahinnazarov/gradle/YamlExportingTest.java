package com.shahinnazarov.gradle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.Namespace;
import org.junit.Test;

public class YamlExportingTest {

    @Test
    public void test() throws JsonProcessingException {
        Namespace namespace = Namespace
                .instance()
                .metadata()
                .name("Name")
                .namespace("Namespace")
                .addAnnotation("key-ann", "value-ann")
                .addLabel("key-lbl", "value-lbl")
                .buildMetadata();

        ObjectMapper yamlMapper = new ObjectMapper(
                new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        );
        yamlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        System.out.println(yamlMapper.writeValueAsString(namespace));
    }

    @Test
    public void testDeployment() throws JsonProcessingException {
        Deployment deployment = Deployment
                .instance()
                .metadata()
                .namespace("default")
                .name("deployment-01")
                .buildMetadata()
                .spec()
                .replicas(3)
                .selector()
                .addMatchLabel("app", "myapp")
                .buildSelector()
                .podTemplate()
                .metadata().name("pod-name")
                .namespace("space")
                .addLabel("app", "myapp")
                .buildMetadata()
                .spec().imagePullSecret("secret").buildPodTemplateSpec()
                .buildPodTemplate()
                .buildSpecification()
                .buildDeployment()
                ;

        ObjectMapper yamlMapper = new ObjectMapper(
                new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
        );
        yamlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        yamlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        System.out.println(yamlMapper.writeValueAsString(deployment));
    }
}
