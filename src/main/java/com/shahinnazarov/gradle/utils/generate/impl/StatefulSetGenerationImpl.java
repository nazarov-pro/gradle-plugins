package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.*;
import com.shahinnazarov.gradle.utils.converter.AccessModeConverter;
import com.shahinnazarov.gradle.utils.generate.ResourceGeneration;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class StatefulSetGenerationImpl implements ResourceGeneration<StatefulSet> {
    private ContextTypes CONTEXT_TYPE = ContextTypes.STATEFUL_SET;

    @Override
    public StatefulSet generate(String groupId, Properties properties) {
        String name = getFromProperties(properties, getFullKey(groupId, NAME),
                extractId(CONTEXT_TYPE, groupId));

        StatefulSet statefulSet = StatefulSet
                .instance()
                .metadata()
                .name(name)
                .namespace(getNamespace(CONTEXT_TYPE.getId(groupId)))
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .spec()
                .serviceName(getFromProperties(properties, getFullKey(groupId, SERVICE_NAME)))
                .replicas(getFromPropertiesAsInteger(properties, getFullKey(groupId, REPLICAS)))
                .podTemplate()
                .spec()
                .addImagePullSecret()
                .name(getFromProperties(properties, getFullKey(groupId, IMAGE_PULL_SECRET)))
                .buildImagePullSecret()
                .restartPolicy(getFromProperties(properties, getFullKey(groupId, RESTART)))
                .buildPodTemplateSpec()
                .buildPodTemplate()
                .buildSpec()
                .build();

        String nodeSelector = getFromProperties(properties, getFullKey(groupId, SELECTED_NODE));
        if (nodeSelector != null) {
            statefulSet.spec().podTemplate()
                    .spec().addNodeSelector(KUBERNETES_HOSTNAME, nodeSelector)
                    .buildPodTemplateSpec()
                    .buildPodTemplate()
                    .buildSpec()
                    .build();
        }

        configureStrategy(groupId, properties, statefulSet);
        configureSelectors(groupId, properties, statefulSet);
        addVolumes(groupId, properties, statefulSet);
        addContainers(groupId, properties, statefulSet);
        configureVolumeClaims(groupId, properties, statefulSet);
        return statefulSet;
    }

    private void addVolumes(String groupId, Properties properties, StatefulSet statefulSet) {
        String volumesKey = getFullKey(groupId, VOLUMES);
        Map<String, Map<String, String>> volumes = getAsMapByGroupId(volumesKey, properties, 0);

        if (volumes != null) {
            volumes.forEach((id, value) -> {
                String name = value.getOrDefault(join(volumesKey, id, NAME), id);
                PodVolume<PodTemplateSpec<PodTemplate<StatefulSetSpec<StatefulSet>>>> podTemplateSpecPodVolume =
                        statefulSet.spec().podTemplate().spec()
                                .addVolume();

                String type = value.get(join(volumesKey, id, TYPE));

                switch (type) {
                    case "pvc":
                        String pvcName = value.get(join(volumesKey, id, type, NAME));

                        podTemplateSpecPodVolume
                                .name(name)
                                .pvc()
                                .claimName(pvcName)
                                .buildPvc();
                        break;
                }
                podTemplateSpecPodVolume.buildPodVolume()
                        .buildPodTemplateSpec().buildPodTemplate().buildSpec().build();

            });
        }
    }

    private void addContainers(String groupId, Properties properties, StatefulSet statefulSet) {
        String containersKey = getFullKey(groupId, CONTAINERS);
        Map<String, Map<String, String>> containers = getAsMapByGroupId(containersKey, properties, 0);
        if (containers != null) {
            Container<PodTemplateSpec<PodTemplate<StatefulSetSpec<StatefulSet>>>> container =
                    statefulSet.spec().podTemplate()
                            .spec()
                            .addContainer();

            containers.forEach((id, parameters) -> {
                String name = parameters.getOrDefault(join(containersKey, id, NAME), id);
                String image = parameters.get(join(containersKey, id, IMAGE));

                if(image.startsWith(HTTP_PREFIX)) {
                    image = image.substring(HTTP_PREFIX.length());
                } else if(image.startsWith(HTTPS_PREFIX)) {
                    image = image.substring(HTTPS_PREFIX.length());
                }

                Map<String, Map<String, String>> ports = getAsMapByGroupId(join(containersKey, id, PORTS), properties, 0);
                Map<String, Map<String, String>> env = getAsMapByGroupId(join(containersKey, id, ENV), properties, 0);
                Map<String, Map<String, String>> mounts = getAsMapByGroupId(join(containersKey, id, MOUNTS), properties, 0);


                container.name(name)
                        .image(image);

                if (ports != null) {
                    ports.forEach((portId, portProperties) -> {
                        String portName = portProperties.getOrDefault(join(containersKey, id, PORTS, portId, NAME), portId);
                        String containerPort = portProperties.get(join(containersKey, id, PORTS, portId, CONTAINER_PORT));
                        container.addPort()
                                .name(portName)
                                .containerPort(Integer.parseInt(containerPort))
                                .buildPort();
                    });
                }

                if (env != null) {
                    env.forEach((envId, envProperties) -> {
                        String envName = envProperties.getOrDefault(join(containersKey, id, ENV, envId, NAME), envId);
                        String envValue = envProperties.get(join(containersKey, id, ENV, envId, VALUE));

                        container.addEnv()
                                .name(envName)
                                .value(envValue)
                                .buildEnvironment();
                    });
                }

                if (mounts != null) {
                    mounts.forEach((volumeMountId, volumeMountProperties) -> {
                        String volumeName = volumeMountProperties.getOrDefault(join(containersKey, id, MOUNTS, volumeMountId, NAME), volumeMountId);
                        String volumeMountPath = volumeMountProperties.get(join(containersKey, id, MOUNTS, volumeMountId, MOUNT_PATH));

                        container.addVolumeMount()
                                .mountPath(volumeMountPath)
                                .name(volumeName)
                                .buildVolumeMount();
                    });
                }


                String readinessProbeType = getFromProperties(properties, join(containersKey, id, READINESS_PROBE, TYPE),
                        "none");
                switch (readinessProbeType) {
                    case "http":
                        Object readinessProbePort = getFromPropertiesAsIntegerOrString(properties, join(containersKey, id, READINESS_PROBE, PORT));
                        String readinessProbePath = getFromProperties(properties, join(containersKey, id, READINESS_PROBE, PATH));
                        Integer initialDelaySeconds = getFromPropertiesAsInteger(properties, join(containersKey, id, READINESS_PROBE, INITIAL_DELAY));
                        Integer periodSeconds = getFromPropertiesAsInteger(properties, join(containersKey, id, READINESS_PROBE, PERIOD_SECONDS));
                        Integer successThreshold = getFromPropertiesAsInteger(properties, join(containersKey, id, READINESS_PROBE, SUCCESS_THRESHOLD));
                        Integer failureThreshold = getFromPropertiesAsInteger(properties, join(containersKey, id, READINESS_PROBE, FAILURE_THRESHOLD));

                        container.readinessProbe()
                                .httpGet()
                                .port(readinessProbePort)
                                .path(readinessProbePath)
                                .buildHttpGet()
                                .initialDelaySeconds(initialDelaySeconds)
                                .periodSeconds(periodSeconds)
                                .successThreshold(successThreshold)
                                .failureThreshold(failureThreshold)
                                .buildProbe();
                        break;
                }


                String livenessProbeType = getFromProperties(properties, join(containersKey, id, LIVENESS_PROBE, TYPE),
                        "none");

                switch (livenessProbeType) {
                    case "http":
                        Object livenessProbePort = getFromPropertiesAsIntegerOrString(properties, join(containersKey, id, LIVENESS_PROBE, PORT));
                        String livenessProbePath = getFromProperties(properties, join(containersKey, id, LIVENESS_PROBE, PATH));
                        Integer initialDelaySeconds = getFromPropertiesAsInteger(properties, join(containersKey, id, LIVENESS_PROBE, INITIAL_DELAY));
                        Integer periodSeconds = getFromPropertiesAsInteger(properties, join(containersKey, id, LIVENESS_PROBE, PERIOD_SECONDS));
                        Integer successThreshold = getFromPropertiesAsInteger(properties, join(containersKey, id, LIVENESS_PROBE, SUCCESS_THRESHOLD));
                        Integer failureThreshold = getFromPropertiesAsInteger(properties, join(containersKey, id, LIVENESS_PROBE, FAILURE_THRESHOLD));

                        container.livenessProbe()
                                .httpGet()
                                .port(livenessProbePort)
                                .path(livenessProbePath)
                                .buildHttpGet()
                                .initialDelaySeconds(initialDelaySeconds)
                                .periodSeconds(periodSeconds)
                                .successThreshold(successThreshold)
                                .failureThreshold(failureThreshold)
                                .buildProbe();
                        break;
                }

                Map<String, String> requests = getAsMap(join(containersKey, id, RESOURCES_REQUESTS), properties);
                Map<String, String> limits = getAsMap(join(containersKey, id, RESOURCES_LIMITS), properties);

                if (requests != null || limits != null) {
                    container.resources()
                            .requests(requests)
                            .limits(limits)
                            .buildResources();
                }

            });
            container.buildContainer().buildPodTemplateSpec().buildPodTemplate().buildSpec().build();
        }
    }

    private void configureSelectors(String groupId, Properties properties, StatefulSet statefulSet) {
        String selectorKey = getFullKey(groupId, SELECTOR);
        Map<String, String> labels = getAsMap(join(selectorKey, LABELS), properties);
        if (labels != null) {
            labels.forEach((key, value) -> {
                statefulSet
                        .spec()
                        .selector()
                        .addMatchLabel(key, value)
                        .buildSelector()
                        .podTemplate()
                        .metadata()
                        .addLabel(key, value)
                        .buildMetadata()
                        .buildPodTemplate()
                        .buildSpec()
                        .build();
            });
        }
    }

    private void configureStrategy(String groupId, Properties properties, StatefulSet statefulSet) {
        String strategyKey = getFullKey(groupId, STRATEGY);

        String strategyType = getFromProperties(properties, join(strategyKey, TYPE), "none");

        switch (strategyType) {
            case RECREATE:
                statefulSet.spec().updateStrategy()
                        .recreate()
                        .buildUpdateStrategy()
                        .buildSpec()
                        .build();
                break;
            case ROLLING_UPDATE:
                Integer rollingUpdateMaxSurge = getFromPropertiesAsInteger(properties, join(strategyKey, ROLLING_UPDATE, MAX_SURGE));

                statefulSet.spec().updateStrategy()
                        .rollingUpdate()
                        .maxSurge(rollingUpdateMaxSurge)
                        .buildRollingUpdate()
                        .buildUpdateStrategy()
                        .buildSpec()
                        .build();
                break;
        }
    }


    private void configureVolumeClaims(String groupId, Properties properties, StatefulSet statefulSet) {
        String volumeClaimsKey = getFullKey(groupId, VOLUME_CLAIMS);
        Map<String, Map<String, String>> volumeClaims = getAsMapByGroupId(volumeClaimsKey, properties, 0);
        if(volumeClaims != null) {
            volumeClaims.forEach(
                    (id, volumeClaimProperties) -> {
                        String name = volumeClaimProperties.getOrDefault(join(volumeClaimsKey, id, NAME), id);
                        List<String> accessModes = new AccessModeConverter().convert(
                                volumeClaimProperties.get(join(volumeClaimsKey, id, ACCESS_MODES)),
                                STRING_ARRAY_SPLITTER_REGEX
                        );
                        String storageClass = volumeClaimProperties.get(join(volumeClaimsKey, id, STORAGE_CLASS_NAME));
                        Map<String, String> requests = getAsMap(join(volumeClaimsKey, id, RESOURCES_REQUESTS), properties);

                        statefulSet.spec()

                                .addVolumeClaimTemplate()
                                .metadata()
                                .name(name)
                                .buildMetadata()
                                .spec()
                                .accessModes(accessModes)
                                .storageClassName(storageClass)
                                .resources()
                                .requests(requests)
                                .buildResources()
                                .buildPvcSpec()
                                .buildPvc()
                                .buildSpec()
                                .build();
                    }
            );
        }
    }
}
