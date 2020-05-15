package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.Deployment;
import com.shahinnazarov.gradle.models.k8s.Service;

import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class K8sDeploymentGenerationImpl implements ResourceGeneration<Deployment> {
    private ContextTypes CONTEXT_TYPE = ContextTypes.DEPLOYMENT;

    @Override
    public Deployment generate(String groupId, Properties properties) {
        String name = extractId(CONTEXT_TYPE, groupId);

        if (properties.containsKey(getFullKey(groupId, NAME))) {
            name = properties.getProperty(getFullKey(groupId, NAME));
        }

        Deployment deployment = Deployment
                .instance()
                .metadata()
                .name(name)
                .namespace(getNamespace(CONTEXT_TYPE.getId(groupId)))
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .spec()
                .buildSpecification()
                .buildDeployment();

//        addPorts(groupId, properties, service);
        return deployment;
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
                if (port != null) {
                    targetPortNumber = Integer.parseInt(targetPort);
                }

                String nodePort = parameters.get(join(portsKey, id, NODE_PORT));
                Integer nodePortNumber = null;
                if (port != null) {
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
