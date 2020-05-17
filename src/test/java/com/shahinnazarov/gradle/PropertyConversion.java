package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.utils.K8sContext;
import com.shahinnazarov.gradle.utils.generate.K8sApplicationGeneration;
import com.shahinnazarov.gradle.utils.generate.K8sNamespaceGeneration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConversion {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream =
                PropertyConversion.class.getClassLoader().getResourceAsStream("ss.template.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        K8sContext.initialize(properties);
        System.out.println(K8sContext.getInstance().getAsYaml());
    }
}
