package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.Service;
import com.shahinnazarov.gradle.utils.K8sContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class K8sDeploymentGenerationTest {

    @Test
    public void generateSingle() {
        K8sContext.initialize(getSingleService());
        K8sContext context = K8sContext.getInstance();
        Deployment deployment = context.getDeployment("ns01/deploy01");

        System.out.println(context.getAsYaml(deployment));
        Assert.assertEquals(deployment.getMetadata().getName(), "app-deployment");
    }


    public Properties getSingleService() {
        Properties properties = new Properties();
        properties.put("k8s.deploy.ns01/deploy01.name", "app-deployment");
        properties.put("k8s.deploy.ns01/deploy01.selector.labels.applicationName", "application11");
        properties.put("k8s.deploy.ns01/deploy01.replicas", "1");
        properties.put("k8s.deploy.ns01/deploy01.selectedNode", "appsVm");
        properties.put("k8s.deploy.ns01/deploy01.restart", "always");
        properties.put("k8s.deploy.ns01/deploy01.imagePullSecret", "deployment");
        properties.put("k8s.deploy.ns01/deploy01.volumes.logs", "pvc:ns01.mynm-logs");

        properties.put("k8s.deploy.ns01/deploy01.containers.cnt-name.image", "192.168.1.47:30006/engine-event-consumer:latest");
        properties.put("k8s.deploy.ns01/deploy01.containers.cnt-name.ports.http", "8801");
        properties.put("k8s.deploy.ns01/deploy01.containers.cnt-name.env.SPRING_PROFILES_ACTIVE", "production");
        properties.put("k8s.deploy.ns01/deploy01.containers.cnt-name.mounts.logs", "/logs");
        return properties;
    }

}
