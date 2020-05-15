package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.utils.generate.K8sApplicationGeneration;
import com.shahinnazarov.gradle.utils.generate.K8sNamespaceGeneration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConversion {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream =
                PropertyConversion.class.getClassLoader().getResourceAsStream("k8s.template.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        K8sNamespaceGeneration k8sNamespaceGeneration = new K8sNamespaceGeneration();
        String generatedYamlContent = k8sNamespaceGeneration.generate(properties);
        System.out.println(generatedYamlContent);

        K8sApplicationGeneration k8sApplicationGeneration = new K8sApplicationGeneration();
        System.out.println(k8sApplicationGeneration.generate(properties));;
    }
}
