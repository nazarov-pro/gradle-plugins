import com.shahinnazarov.gradle.extensions.K8sFile;
import com.shahinnazarov.gradle.models.*;
import com.shahinnazarov.gradle.models.enums.K8sImagePullPolicies;
import com.shahinnazarov.gradle.models.enums.K8sPodTemplateSpecRestartPolicies;
import com.shahinnazarov.gradle.models.enums.K8sServiceSpecPortProtocols;

import java.util.*;

public class K8sFileBuilder {
    private K8sFile k8sFile = new K8sFile();

    private static final String RANCHER_PROJECT_ID_KEY = "field.cattle.io/projectId";
    private static final String NODE_SELECTOR_HOST_KEY = "kubernetes.io/hostname";
    private String namespaceName;


    // NAMESPACES
    public K8sFileBuilder addNamespaceForRancher(String name, String rancherId) {
        K8sNamespace k8sNamespace = new K8sNamespace();
        K8sMetadata k8sMetadata = new K8sMetadata();
        namespaceName = name;
        k8sMetadata.setName(name);
        k8sMetadata.setAnnotations(createMapForOneEntry(RANCHER_PROJECT_ID_KEY, rancherId));
        k8sNamespace.setMetadata(k8sMetadata);
        return addNamespace(k8sNamespace);
    }

    public K8sFileBuilder addNamespace(String name, Map<String, String> annotations) {
        K8sNamespace k8sNamespace = new K8sNamespace();
        K8sMetadata k8sMetadata = new K8sMetadata();
        namespaceName = name;
        k8sMetadata.setName(name);
        k8sMetadata.setAnnotations(annotations);
        k8sNamespace.setMetadata(k8sMetadata);
        return addNamespace(k8sNamespace);
    }

    public K8sFileBuilder addNamespace(K8sNamespace k8sNamespace) {
        k8sFile.getNamespaces().add(k8sNamespace);
        return this;
    }

    public K8sFileBuilder setCurrentNamespace(String namespace) {
        namespaceName = namespace;
        return this;
    }


    // SERVICES

    public K8sFileBuilder addService(String name, String selectorKey, String selectorValue, String type,
                                     String protocol, Integer port, Integer targetPort) {
        Map<String, String> selector = createMapForOneEntry(selectorKey, selectorValue);
        K8sServiceSpecPort k8sServiceSpecPort = new K8sServiceSpecPort(
                port, targetPort, K8sServiceSpecPortProtocols.valueOf(protocol)
        );
        return addService(name, namespaceName, selector, type, Collections.singletonList(k8sServiceSpecPort));
    }

    public K8sFileBuilder addService(String name, String selectorKey, String selectorValue, String type,
                                                String protocol, Integer port, Integer targetPort, Integer nodePort) {
        Map<String, String> selector = createMapForOneEntry(selectorKey, selectorValue);
        K8sServiceSpecPort k8sServiceSpecPort = new K8sServiceSpecPort(
                nodePort, port, targetPort, K8sServiceSpecPortProtocols.valueOf(protocol)
        );
        return addService(name, namespaceName, selector, type, Collections.singletonList(k8sServiceSpecPort));
    }

    public K8sFileBuilder addServiceToNamespace(String name, Map<String, String> selector, String type,
                                                K8sServiceSpecPort... ports) {
        return addService(name, namespaceName, selector, type, Arrays.asList(ports));
    }

    public K8sFileBuilder addService(String name, String namespace, Map<String, String> selector,
                                     String type, List<K8sServiceSpecPort> ports) {
        K8sMetadata k8sMetadata = new K8sMetadata();
        k8sMetadata.setName(name);
        k8sMetadata.setNamespace(namespace);

        K8sServiceSpec k8sServiceSpec = new K8sServiceSpec(
                selector, type, ports
        );
        return addService(new K8sService(k8sMetadata, k8sServiceSpec));
    }


    public K8sFileBuilder addService(K8sService k8sService) {
        k8sFile.getServices().add(k8sService);
        return this;
    }


    // DEPLOYMENTS

    public K8sFileBuilder addDeployment(String name, String labelKey, String labelValue,
                                        Integer replicas, String imagePullSecretName,
                                        String containerName, String imageName, String imagePullPolicy,
                                        Integer containerPort, String nodeName, String restartPolicy
                                        ) {
        Map<String, String> labels = createMapForOneEntry(labelKey, labelValue);
        Map<String, String> nodeSelector = createMapForOneEntry(NODE_SELECTOR_HOST_KEY, nodeName);

        K8sServiceSpecPort port = new K8sServiceSpecPort(containerName, containerPort);

        K8sPodTemplateSpecContainer container = new K8sPodTemplateSpecContainer(
                containerName, imageName, K8sImagePullPolicies.valueOf(imagePullPolicy), Collections.singletonList(port), null
        );

        return addDeployment(name, namespaceName, labels, replicas, labels, labels,
                imagePullSecretName, null, Collections.singletonList(container),
                nodeSelector, restartPolicy);
    }

    public K8sFileBuilder addDeployment(String name, String labelKey, String labelValue,
                                        Integer replicas, String imagePullSecretName,
                                        String containerName, String imageName, String imagePullPolicy,
                                        Integer containerPort, String nodeName, String restartPolicy,
                                        String envKey, String envVal
    ) {
        Map<String, String> labels = createMapForOneEntry(labelKey, labelValue);
        Map<String, String> nodeSelector = createMapForOneEntry(NODE_SELECTOR_HOST_KEY, nodeName);

        K8sServiceSpecPort port = new K8sServiceSpecPort(containerName, containerPort);
        Map<String, String> environments = new HashMap<>();
        environments.put(envKey, envVal);
        K8sPodTemplateSpecContainer container = new K8sPodTemplateSpecContainer(
                containerName, imageName, K8sImagePullPolicies.valueOf(imagePullPolicy), Collections.singletonList(port), environments
        );

        return addDeployment(name, namespaceName, labels, replicas, labels, labels,
                imagePullSecretName, null, Collections.singletonList(container),
                nodeSelector, restartPolicy);
    }

    public K8sFileBuilder addDeployment(String name, String namespace, Map<String, String> labels,
                                        Integer replicas, Map<String, String> matchLabels,
                                        Map<String, String> templateLabels, String pullSecretName,
                                        List<K8sPodTemplateSpecVolume> podTemplateSpecVolumes,List<K8sPodTemplateSpecContainer> containers,
                                        Map<String, String> nodeSelector, String restartPolicy
                                        ) {
        K8sMetadata metadata = new K8sMetadata(name, namespace, labels);
        K8sSelector k8sSelector = new K8sSelector(matchLabels);

        K8sMetadata templateMetadata = new K8sMetadata();
        templateMetadata.setLabels(templateLabels);

        K8sPodTemplateSpec k8sPodTemplateSpec = new K8sPodTemplateSpec(
                pullSecretName, podTemplateSpecVolumes, containers, nodeSelector, K8sPodTemplateSpecRestartPolicies.valueOf(restartPolicy)
        );

        K8sPodTemplate podTemplate = new K8sPodTemplate(templateMetadata, k8sPodTemplateSpec);
        K8sDeploymentSpec k8sDeploymentSpec = new K8sDeploymentSpec(
                replicas, k8sSelector, podTemplate
        );
        K8sDeployment k8sDeployment = new K8sDeployment(metadata, k8sDeploymentSpec);
        return addDeployment(k8sDeployment);
    }

    public K8sFileBuilder addDeployment(K8sDeployment k8sDeployment) {
        k8sFile.getDeployments().add(k8sDeployment);
        return this;
    }

    public K8sFile build() {
        return k8sFile;
    }

    private Map<String, String> createMapForOneEntry(String key, String value) {
        Map<String, String> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }
}
