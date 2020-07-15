package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.StatefulSet;
import com.shahinnazarov.gradle.utils.K8sContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class K8sStatefulSetGenerationTest {

    @Test
    public void generateSingle() {
        K8sContext.initialize(getSingleService());
        K8sContext context = K8sContext.getInstance();
        StatefulSet statefulSet = context.getStatefulSet("ns01/ss01");

        System.out.println(context.getAsYaml(statefulSet));
        Assert.assertEquals(statefulSet.getMetadata().getName(), "app-ss");
    }


    public Properties getSingleService() {
        Properties properties = new Properties();
        properties.put("k8s.ss.ns01/ss01.name", "app-ss");

        properties.put("k8s.ss.ns01/ss01.selector.labels.applicationName", "application11");
        properties.put("k8s.ss.ns01/ss01.replicas", "1");
        properties.put("k8s.ss.ns01/ss01.selectedNode", "appsVm");
        properties.put("k8s.ss.ns01/ss01.restart", "always");
        properties.put("k8s.ss.ns01/ss01.imagePullSecret", "deployment");

        properties.put("k8s.ss.ns01/ss01.volumeClaims.logs.name", "logs");
        properties.put("k8s.ss.ns01/ss01.volumeClaims.logs.accessModes", "rwo");
        properties.put("k8s.ss.ns01/ss01.volumeClaims.logs.resources.requests.storage", "5Gi");

        properties.put("k8s.ss.ns01/ss01.containers.cnt-name.image", "192.168.1.47:30006/engine-event-consumer:latest");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name.ports.http.containerPort", "8801");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name.env.SPRING_PROFILES_ACTIVE.value", "production");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name.env.POD_NAME.valueFrom.fieldRef.fieldPath", "metadata.name");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name.mounts.logs.mountPath", "/logs");

        properties.put("k8s.ss.ns01/ss01.containers.cnt-name2.image", "192.168.1.47:30006/engine-event-consumer:latest");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name2.ports.http.containerPort", "8801");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name2.env.SPRING_PROFILES_ACTIVE.value", "production");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name2.env.POD_NAME.valueFrom.fieldRef.fieldPath", "metadata.name");
        properties.put("k8s.ss.ns01/ss01.containers.cnt-name2.mounts.logs.mountPath", "/logs");
        return properties;
    }

}
