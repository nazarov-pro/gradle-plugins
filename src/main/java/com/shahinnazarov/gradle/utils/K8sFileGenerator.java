package com.shahinnazarov.gradle.utils;

import com.shahinnazarov.gradle.extensions.K8sFile;
import com.shahinnazarov.gradle.models.*;
import com.shahinnazarov.gradle.models.enums.K8sPodTemplateSpecRestartPolicies;

import java.util.concurrent.atomic.AtomicBoolean;

public class K8sFileGenerator {

    public String generate(K8sFile k8sFile) {
        StringBuilder result = new StringBuilder();
        if (k8sFile.getNamespaces() != null) {
            for (K8sNamespace item : k8sFile.getNamespaces()) {
                result.append(generateApiVersion(item.getApiVersion()));
                result.append(generateKind(item.getKind()));
                result.append(generateMetadata(item.getMetadata()));
                result.append(separator());
            }
        }

        if (k8sFile.getPersistentVolumeClaims() != null) {
            k8sFile.getPersistentVolumeClaims().forEach(
                    item -> {
                        result.append(generateApiVersion(item.getApiVersion()));
                        result.append(generateKind(item.getKind()));
                        result.append(generateMetadata(item.getMetadata()));
                        result.append(generatePersistentVolumeSpec(item.getSpec()));
                        result.append(separator());
                    }
            );
        }

        if (k8sFile.getServices() != null) {
            k8sFile.getServices().forEach(
                    item -> {
                        result.append(generateApiVersion(item.getApiVersion()));
                        result.append(generateKind(item.getKind()));
                        result.append(generateMetadata(item.getMetadata()));
                        result.append(generateServiceSpec(item.getSpec()));
                        result.append(separator());
                    }
            );
        }

        if (k8sFile.getDeployments() != null) {
            k8sFile.getDeployments().forEach(
                    item -> {
                        result.append(generateApiVersion(item.getApiVersion()));
                        result.append(generateKind(item.getKind()));
                        result.append(generateMetadata(item.getMetadata()));
                        result.append(generateDeploymentSpec(item.getSpec()));
                        result.append(separator());
                    }
            );
        }


        return result.toString().

                trim();

    }


    public String generateApiVersion(String version) {
        return String.format("apiVersion: %s\n", version);
    }

    public String generateKind(String kind) {
        return String.format("kind: %s\n", kind);
    }

    public String generateName(String name) {
        return String.format("name: %s\n", name);
    }

    public String generateProtocol(String name) {
        return String.format("protocol: %s\n", name);
    }

    public String generatePort(Integer port) {
        return String.format("port: %d\n", port);
    }

    public String generateTargetPort(Integer port) {
        return String.format("targetPort: %d\n", port);
    }

    public String generateNodePort(Integer port) {
        return String.format("nodePort: %d\n", port);
    }

    public String generateContainerPort(Integer port) {
        return String.format("containerPort: %d\n", port);
    }

    public String generateReplicas(Integer replicas) {
        return String.format("replicas: %d\n", replicas);
    }

    public String generateNamespace(String namespace) {
        return String.format("namespace: %s\n", namespace);
    }

    public String generateMetadata() {
        return "metadata:\n";
    }

    public String generateSpec() {
        return "spec:\n";
    }

    public String generateAnnotations() {
        return "annotations:\n";
    }

    public String generateLabels() {
        return "labels:\n";
    }

    public String generateMatchLabels() {
        return "matchLabels:\n";
    }

    public String generateMatchExpressions() {
        return "matchExpressions:\n";
    }


    public String generateAccessModes() {
        return "accessModes:\n";
    }

    public String generateResources() {
        return "resources:\n";
    }

    public String generateSelector() {
        return "selector:\n";
    }

    public String generateTemplate() {
        return "template:\n";
    }


    public String generatePorts() {
        return "ports:\n";
    }

    public String generateKeyValue(String key, String value) {
        return String.format("%s: %s\n", key, value);
    }

    public String generateRequests() {
        return "requests:\n";
    }

    public String generateImagePullSecrets() {
        return "imagePullSecrets:\n";
    }

    public String generateVolumes() {
        return "volumes:\n";
    }

    public String generatePersistentVolumeClaim() {
        return "persistentVolumeClaim:\n";
    }

    public String generateContainers() {
        return "containers:\n";
    }

    public String generateEnv() {
        return "env:\n";
    }

    public String generateVolumeMounts() {
        return "volumeMounts:\n";
    }

    public String generateNodeSelector() {
        return "nodeSelector:\n";
    }


    public String generateStorageRequest(String storageSize) {
        return "storage: ".concat(storageSize).concat("\n");
    }

    public String generateStorageClass(String storageClass) {
        return "storageClassName: ".concat(storageClass).concat("\n");
    }

    public String generatePersistentVolumeClaim(String name) {
        return "claimName: ".concat(name).concat("\n");
    }

    public String generateImage(String name) {
        return "image: ".concat(name).concat("\n");
    }

    public String generateType(String name) {
        return "type: ".concat(name).concat("\n");
    }

    public String generateRestartPolicy(K8sPodTemplateSpecRestartPolicies restartPolicies) {
        return "restartPolicy: ".concat(restartPolicies.getType()).concat("\n");
    }


    public String generateValue(String name) {
        return "value: ".concat(name).concat("\n");
    }

    public String generateMountPath(String mountPath) {
        return "mountPath: ".concat(mountPath).concat("\n");
    }

    public String generateListItem(String item) {
        return "- ".concat(item);
    }

    public String generateListItem(String item, boolean skipDash) {
        return skipDash ? item : generateListItem(item);
    }

    public String generateMetadata(K8sMetadata metadata) {
        return generateMetadata(metadata, 0);
    }

    public String generateMetadata(K8sMetadata metadata, Integer tabSize) {
        StringBuilder result = new StringBuilder(addTab(generateMetadata(), tabSize));

        if (metadata == null) {
            return "";
        }

        if (metadata.getName() != null) {
            result.append(addTab(generateName(metadata.getName()), tabSize + 1));
        }

        if (metadata.getNamespace() != null) {
            result.append(addTab(generateNamespace(metadata.getNamespace()), tabSize + 1));
        }

        if (metadata.getAnnotations() != null) {
            result.append(addTab(generateAnnotations(), tabSize + 1));
            metadata.getAnnotations().forEach(
                    (k, v) -> {
                        result.append(addTab(String.format("%s: %s\n", k, v), tabSize + 2));
                    }
            );
        }

        if (metadata.getLabels() != null) {
            result.append(addTab(generateLabels(), tabSize + 1));
            metadata.getLabels().forEach(
                    (k, v) -> {
                        result.append(addTab(String.format("%s: %s\n", k, v), tabSize + 2));
                    }
            );
        }

        return result.toString();
    }

    public String generatePersistentVolumeSpec(K8sPersistentVolumeClaimSpec persistentVolumeClaimSpec) {
        StringBuilder result = new StringBuilder(generateSpec());
        if (persistentVolumeClaimSpec == null) {
            return "";
        }

        if (persistentVolumeClaimSpec != null) {
            if (persistentVolumeClaimSpec.getAccessModes() != null) {
                result.append(addTab(generateAccessModes()));
                persistentVolumeClaimSpec.getAccessModes().forEach(am -> {
                    result.append(addTab(generateListItem(am.getName().concat("\n")), 2));
                });
            }

            if (persistentVolumeClaimSpec.getResource() != null) {
                result.append(addTab(generateResources()));
                if (persistentVolumeClaimSpec.getResource().getRequest() != null) {
                    result.append(addTab(generateRequests(), 2));

                    if (persistentVolumeClaimSpec.getResource().getRequest().getStorageSize() != null) {
                        result.append(addTab(generateStorageRequest
                                        (persistentVolumeClaimSpec.getResource().getRequest().getStorageSize()),
                                3));
                    }
                }
            }

            if (persistentVolumeClaimSpec.getStorageClassName() != null) {
                result.append(generateStorageClass(persistentVolumeClaimSpec.getStorageClassName()));
            }
        }
        return result.toString().trim();
    }

    public String generateServiceSpec(K8sServiceSpec k8sServiceSpec) {
        StringBuilder result = new StringBuilder(generateSpec());
        if (k8sServiceSpec == null) {
            return "";
        }

        if (k8sServiceSpec.getSelector() != null) {
            result.append(addTab(generateSelector()));
            k8sServiceSpec.getSelector().forEach((k, v) ->
                    result.append(addTab(generateKeyValue(k, v), 2)));
        }

        if (k8sServiceSpec.getType() != null) {
            result.append(addTab(generateType(k8sServiceSpec.getType())));
        }

        if (k8sServiceSpec.getPorts() != null) {
            result.append(addTab(generatePorts()));

            k8sServiceSpec.getPorts().forEach(p -> {
                        boolean selected = false;

                        if (p.getName() != null) {
                            result.append(addTab(generateListItem(generateName(p.getName()), selected), 2));
                            selected = true;
                        }

                        if (p.getProtocol() != null) {
                            result.append(addTab(generateListItem(generateProtocol(p.getProtocol().getName()), selected), 2));
                            selected = true;
                        }

                        if (p.getPort() != null) {
                            result.append(addTab(generateListItem(generatePort(p.getPort()), selected), 2));
                            selected = true;
                        }

                        if (p.getTargetPort() != null) {
                            result.append(addTab(generateListItem(generateTargetPort(p.getTargetPort()), selected), 2));
                            selected = true;
                        }

                        if (p.getNodePort() != null) {
                            result.append(addTab(generateListItem(generateNodePort(p.getNodePort()), selected), 2));
                        }

                    }
            );

        }

        return result.toString().trim();
    }

    public String generateSelector(K8sSelector selector) {
        StringBuilder result = new StringBuilder(addTab(generateSelector()));
        if (selector == null) {
            return "";
        }

        if (selector.getMatchLabels() != null) {
            result.append(addTab(generateMatchLabels(), 2));
            selector.getMatchLabels().forEach((k, v) -> {
                result.append(addTab(generateKeyValue(k, v), 3));
            });
        }

        if (selector.getMatchExpressions() != null) {
            result.append(addTab(generateMatchExpressions(), 2));
            selector.getMatchExpressions().forEach(exp -> {
                result.append(addTab(String.format("%s\n", exp), 3));
            });
        }

        return result.toString();
    }

    public String generatePodTemplate(K8sPodTemplate podTemplate) {
        StringBuilder result = new StringBuilder(addTab(generateTemplate()));

        if (podTemplate == null) {
            return "";
        }

        if (podTemplate.getMetadata() != null) {
            result.append(generateMetadata(podTemplate.getMetadata(), 2));
        }

        if (podTemplate.getSpec() != null) {
            result.append(addTab(generatePodTemplateSpec(podTemplate.getSpec()), 2));
        }

        return result.toString().trim();
    }

    public String generatePodTemplateSpec(K8sPodTemplateSpec podTemplateSpec) {
        StringBuilder result = new StringBuilder(generateSpec());

        if (podTemplateSpec == null) {
            return "";
        }

        if (podTemplateSpec.getImagePullSecret() != null) {
            result.append(addTab(generateImagePullSecrets(), 3));
            result.append(addTab(generateListItem(generateName(podTemplateSpec.getImagePullSecret())
            ), 4));
        }

        if (podTemplateSpec.getVolumes() != null) {
            result.append(addTab(generateVolumes(), 3));
            podTemplateSpec.getVolumes().forEach(
                    podTemplateSpecVolume -> {
                        boolean selected = false;

                        if (podTemplateSpecVolume.getName() != null) {
                            result.append(addTab(generateListItem(generateName(podTemplateSpecVolume.getName()),
                                    selected), 4));
                            selected = true;
                        }

                        if (podTemplateSpecVolume.getPersistentVolumeClaim() != null) {
                            result.append(addTab(generatePersistentVolumeClaim(), 4));
                            result.append(addTab(generatePersistentVolumeClaim(
                                    podTemplateSpecVolume.getPersistentVolumeClaim().getClaimName()), 5));
                        }

                    }
            );
        }

        if (podTemplateSpec.getContainers() != null) {
            result.append(addTab(generateContainers(), 3));
            podTemplateSpec.getContainers().forEach(container -> {
                AtomicBoolean selected = new AtomicBoolean(false);

                if (container.getName() != null) {
                    result.append(addTab(generateListItem(generateName(container.getName()),
                            selected.get()), 4));
                    selected.set(true);
                }

                if (container.getImage() != null) {
                    result.append(addTab(generateListItem(generateImage(container.getImage()),
                            selected.get()), 4));
                    selected.set(true);
                }

                if (container.getPorts() != null) {
                    result.append(addTab(generatePorts(), 4));
                    container.getPorts().forEach(port -> {
                        selected.set(false);
                        if(port.getName() != null) {
                            result.append(addTab(generateListItem(generateName(port.getName()), selected.get()), 5));
                            selected.set(true);
                        }
                        if(port.getContainerPort() != null) {
                            result.append(addTab(generateListItem(generateContainerPort(port.getContainerPort()),
                                    selected.get()), 5));
                        }
                    });
                }

                if (container.getEnvironments() != null) {
                    result.append(addTab(generateEnv(), 4));
                    container.getEnvironments().forEach((k, v) -> {
                        result.append(addTab(generateListItem(generateName(k)), 5));
                        result.append(addTab(generateValue(v), 5));
                    });
                }

                if (container.getVolumeMounts() != null) {
                    result.append(addTab(generateVolumeMounts(), 4));
                    container.getVolumeMounts().forEach(vm -> {
                        selected.set(false);


                        if (vm.getName() != null) {
                            result.append(addTab(generateListItem(generateName(vm.getName()),
                                    selected.get()), 5));
                            selected.set(true);
                        }

                        if (vm.getMountPath() != null) {
                            result.append(addTab(generateListItem(generateMountPath(vm.getMountPath()),
                                    selected.get()), 5));
                        }
                    });
                }

            });
        }

        if (podTemplateSpec.getNodeSelector() != null) {
            result.append(addTab(generateNodeSelector(), 3));

            podTemplateSpec.getNodeSelector().forEach((k, v) -> {
                result.append(addTab(generateKeyValue(k, v), 4));
            });
        }

        if (podTemplateSpec.getRestartPolicy() != null) {
            result.append(addTab(generateRestartPolicy(podTemplateSpec.getRestartPolicy()), 3));
        }

        return result.toString().trim();
    }

    public String generateDeploymentSpec(K8sDeploymentSpec k8sDeploymentSpec) {
        StringBuilder result = new StringBuilder(generateSpec());
        if (k8sDeploymentSpec == null) {
            return "";
        }

        if (k8sDeploymentSpec.getReplicaCount() != null) {
            result.append(addTab(generateReplicas(k8sDeploymentSpec.getReplicaCount())));
        }

        if (k8sDeploymentSpec.getSelector() != null) {
            result.append(addTab(generateSelector(k8sDeploymentSpec.getSelector())));
        }

        if (k8sDeploymentSpec.getTemplate() != null) {
            result.append(addTab(generatePodTemplate(k8sDeploymentSpec.getTemplate())));
        }

        return result.toString().trim();
    }

    public String addTab(String data) {
        return addTab(data, 1);
    }

    public String addTab(String data, int size) {
        String tabs = "";
        for (int i = 0; i < size; i++) {
            tabs += "\t";
        }
        return tabs.concat(data);
    }


    public String separator() {
        return "\n---\n";
    }

}
