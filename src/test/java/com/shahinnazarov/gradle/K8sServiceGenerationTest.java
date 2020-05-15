package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.k8s.Service;
import com.shahinnazarov.gradle.utils.K8sContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class K8sServiceGenerationTest {

    @Test
    public void generateSingle() {
        K8sContext.initialize(getSingleService());
        K8sContext context = K8sContext.getInstance();
        Service service = context.getService("ns01/svc01");

        System.out.println(context.getAsYaml(service));
        Assert.assertEquals(service.getMetadata().getName(), "engine-event-consumer-service");
    }


    public Properties getSingleService() {
        Properties properties = new Properties();
        properties.put("k8s.svc.ns01/svc01.name", "engine-event-consumer-service");
        properties.put("k8s.svc.ns01/svc01.selector.applicationName", "application11");
        properties.put("k8s.svc.ns01/svc01.type", "NodePort");
        properties.put("k8s.svc.ns01/svc01.ports.web.protocol", "TCP");
        properties.put("k8s.svc.ns01/svc01.ports.web.port", "8801");
        properties.put("k8s.svc.ns01/svc01.ports.web.targetPort", "8801");
        properties.put("k8s.svc.ns01/svc01.ports.web.nodePort", "31301");

        properties.put("k8s.svc.ns01/svc01.ports.grpc.protocol", "TCP");
        properties.put("k8s.svc.ns01/svc01.ports.grpc.port", "8808");
        properties.put("k8s.svc.ns01/svc01.ports.grpc.targetPort", "8808");
        properties.put("k8s.svc.ns01/svc01.ports.grpc.nodePort", "31308");
        return properties;
    }

}
