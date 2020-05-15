package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.k8s.PersistentVolumeClaim;
import com.shahinnazarov.gradle.utils.K8sContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class K8sPersistentVolumeClaimGenerationTest {

    @Test
    public void generateSingle() {
        K8sContext.initialize(getSinglePersistentVolumeClaim());
        K8sContext context = K8sContext.getInstance();
        PersistentVolumeClaim persistentVolumeClaim = context.getPersistentVolumeClaim("ns01/pvc01");

        System.out.println(context.getAsYaml(persistentVolumeClaim));
        Assert.assertEquals(persistentVolumeClaim.getMetadata().getName(), "engine-event-consumer-logs");
    }


    public Properties getSinglePersistentVolumeClaim() {
        Properties properties = new Properties();
        properties.put("k8s.pvc.ns01/pvc01.name", "engine-event-consumer-logs");
        properties.put("k8s.pvc.ns01/pvc01.storageClass", "base-storage");
        properties.put("k8s.pvc.ns01/pvc01.accessModes", "rwo");
        properties.put("k8s.pvc.ns01/pvc01.resource.requests.storage", "5Gi");
        return properties;
    }

}
