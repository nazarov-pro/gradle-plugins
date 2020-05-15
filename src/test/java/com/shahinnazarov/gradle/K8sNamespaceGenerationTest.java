package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.k8s.Namespace;
import com.shahinnazarov.gradle.utils.K8sContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Properties;

public class K8sNamespaceGenerationTest {

    @Test
    public void generateSingle() {
        K8sContext.initialize(getSingleNamespace());
        K8sContext context = K8sContext.getInstance();
        Namespace ns01 = context.getNamespace("ns01");
        System.out.println(context.getAsYaml(ns01));
        Assert.assertEquals(ns01.getMetadata().getName(), "engine-consumer");
        Assert.assertTrue(ns01.getMetadata().getAnnotations().size() == 2);
        Assert.assertTrue(ns01.getMetadata().getLabels().size() == 2);
    }

    @Test
    public void generateMultiple() {
        K8sContext.initialize(getMultipleNamespaces());
        K8sContext context = K8sContext.getInstance();
        Collection<Namespace> namespaces = context.getNamespaces();
        System.out.println(context.getAsYaml(namespaces));
        Assert.assertTrue(namespaces.size() == 3);
    }

    public Properties getSingleNamespace() {
        Properties properties = new Properties();
        properties.put("k8s.ns.ns01.name", "engine-consumer");
        properties.put("k8s.ns.ns01.annotations.1.key", "field.cattle.io/projectId");
        properties.put("k8s.ns.ns01.annotations.1.value", "c-qsh5h:p-cmv9");
        properties.put("k8s.ns.ns01.annotations.annotate01", "annotateVal");
        properties.put("k8s.ns.ns01.labels.1.key", "label01");
        properties.put("k8s.ns.ns01.labels.1.value", "label01Value");
        properties.put("k8s.ns.ns01.labels.label02", "labelVal");
        return properties;
    }

    public Properties getMultipleNamespaces() {
        Properties properties = new Properties();
        properties.put("k8s.ns.ns01.name", "engine-consumer");
        properties.put("k8s.ns.ns01.annotations.1.key", "field.cattle.io/projectId");
        properties.put("k8s.ns.ns01.annotations.1.value", "c-qsh5h:p-cmv9");
        properties.put("k8s.ns.ns01.annotations.annotate01", "annotateVal");
        properties.put("k8s.ns.ns01.labels.1.key", "label01");
        properties.put("k8s.ns.ns01.labels.1.value", "label01Value");
        properties.put("k8s.ns.ns01.labels.label02", "labelVal");

        properties.put("k8s.ns.ns02.labels.labelkey", "ns02labelvalue");

        properties.put("k8s.ns.ns03.name", "engine-consumer");
        properties.put("k8s.ns.ns03.annotations.1.key", "field.cattle.io/projectId");
        properties.put("k8s.ns.ns03.annotations.1.value", "c-qsh5h:p-cmv9");

        return properties;
    }
}
