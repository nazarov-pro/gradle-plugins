package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.Service;
import com.shahinnazarov.gradle.utils.generate.ResourceGeneration;
import com.shahinnazarov.gradle.utils.generate.ResourceGenerationHelper;

import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class ServiceGenerationImpl implements ResourceGeneration<Service>, ResourceGenerationHelper {
    private ContextTypes CONTEXT_TYPE = ContextTypes.SERVICE;

    @Override
    public Service generate(String groupId, Properties properties) {
        String name = getFromProperties(properties, getFullKey(groupId, NAME), extractId(CONTEXT_TYPE, groupId));

        Service service = Service
                .instance()
                .metadata()
                .name(name)
                .namespace(getNamespace(CONTEXT_TYPE.getId(groupId)))
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .spec()
                .selector(getAsMap(getFullKey(groupId, SELECTOR), properties))
                .type(getFromProperties(properties, getFullKey(groupId, TYPE)))
                .build()
                .buildService();

        addPorts(groupId, properties, service);
        return service;
    }

    private void addPorts(String groupId, Properties properties, Service service) {
        String portsKey = getFullKey(groupId, PORTS);
        Map<String, Map<String, String>> ports = getAsMapByGroupId(portsKey, properties, 0);
        if (ports != null) {
            ports.forEach((id, parameters) -> {
                String name = parameters.getOrDefault(join(portsKey, id, NAME), id);
                String protocol = parameters.get(join(portsKey, id, PROTOCOL));

                String port = parameters.get(join(portsKey, id, PORT));
                Integer portNumber = null;
                if (port != null) {
                    portNumber = Integer.parseInt(port);
                }

                String targetPort = parameters.get(join(portsKey, id, TARGET_PORT));
                Integer targetPortNumber = null;
                if (targetPort != null) {
                    targetPortNumber = Integer.parseInt(targetPort);
                }

                String nodePort = parameters.get(join(portsKey, id, NODE_PORT));
                Integer nodePortNumber = null;
                if (nodePort != null) {
                    nodePortNumber = Integer.parseInt(nodePort);
                }
                service.spec()
                        .addPort()
                        .name(name)
                        .protocol(protocol)
                        .nodePort(nodePortNumber)
                        .port(portNumber)
                        .targetPort(targetPortNumber)
                        .buildPort().build().buildService();
            });
        }
    }
}
