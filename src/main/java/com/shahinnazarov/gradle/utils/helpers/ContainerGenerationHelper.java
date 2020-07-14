package com.shahinnazarov.gradle.utils.helpers;

import com.shahinnazarov.gradle.models.k8s.Container;
import com.shahinnazarov.gradle.utils.generate.ResourceGenerationHelper;

import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public interface ContainerGenerationHelper extends ResourceGenerationHelper {

    default void addContainers(String groupId, Properties properties, ContainerCreation containerCreation) {
        String containersKey = getFullKey(groupId, CONTAINERS);
        Map<String, Map<String, String>> containers = getAsMapByGroupId(containersKey, properties, 0);
        if (containers != null) {
            containers.forEach((id, parameters) -> {
                Container<?> container = containerCreation.container();
                String name = parameters.getOrDefault(join(containersKey, id, NAME), id);
                String image = parameters.get(join(containersKey, id, IMAGE));
                if (image.startsWith(HTTP_PREFIX)) {
                    image = image.substring(HTTP_PREFIX.length());
                } else if (image.startsWith(HTTPS_PREFIX)) {
                    image = image.substring(HTTPS_PREFIX.length());
                }

                Map<String, Map<String, String>> ports = getAsMapByGroupId(join(containersKey, id, PORTS), properties, 0);
                Map<String, Map<String, String>> env = getAsMapByGroupId(join(containersKey, id, ENV), properties, 0);
                Map<String, Map<String, String>> mounts = getAsMapByGroupId(join(containersKey, id, MOUNTS), properties, 0);


                container.name(name).image(image);

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
                        if(envValue != null) {
                            container.addEnv()
                                    .name(envName)
                                    .value(envValue)
                                    .buildEnvironment();
                        } else {
                            if(checkAnyKeyStartWith(envProperties.keySet(), join(containersKey, id, ENV, envId, VALUE_FROM, FIELD_REF))) {
                                envValue = envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, FIELD_REF, FIELD_PATH));
                                if (envValue != null) {
                                    container.addEnv().name(envName)
                                            .valueFrom().fieldRef()
                                            .apiVersion(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, FIELD_REF, API_VERSION)))
                                            .fieldPath(envValue).build()
                                            .buildEnvironmentSource().buildEnvironment();
                                }
                            } else if(checkAnyKeyStartWith(envProperties.keySet(), join(containersKey, id, ENV, envId, VALUE_FROM, RESOURCE_FIELD_REF))) {
                                envValue = envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, RESOURCE_FIELD_REF, RESOURCE));
                                if (envValue != null) {
                                    container.addEnv().name(envName)
                                            .valueFrom().resourceFieldRef()
                                            .containerName(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, RESOURCE_FIELD_REF, CONTAINER_NAME)))
                                            .resource(envValue)
                                            .divisor(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, RESOURCE_FIELD_REF, DIVISOR)))
                                            .buildResourceFieldRef()
                                            .buildEnvironmentSource().buildEnvironment();
                                }
                            } else if(checkAnyKeyStartWith(envProperties.keySet(), join(containersKey, id, ENV, envId, VALUE_FROM, CONFIG_MAP_KEY_REF))) {
                                envValue = envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, CONFIG_MAP_KEY_REF, KEY));
                                if (envValue != null) {
                                    container.addEnv().name(envName)
                                            .valueFrom().configMapKeyRef()
                                            .key(envValue)
                                            .name(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, CONFIG_MAP_KEY_REF, NAME)))
                                            .optional(Optional.ofNullable(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, CONFIG_MAP_KEY_REF, OPTIONAL))).map(Boolean::parseBoolean).orElse(false))
                                            .build()
                                            .buildEnvironmentSource().buildEnvironment();
                                }
                            } else if(checkAnyKeyStartWith(envProperties.keySet(), join(containersKey, id, ENV, envId, VALUE_FROM, SECRET_KEY_REF))) {
                                envValue = envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, SECRET_KEY_REF, KEY));
                                if (envValue != null) {
                                    container.addEnv().name(envName)
                                            .valueFrom().secretKeyRef()
                                            .key(envValue)
                                            .name(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, SECRET_KEY_REF, NAME)))
                                            .optional(
                                                    Optional.ofNullable(envProperties.get(join(containersKey, id, ENV, envId, VALUE_FROM, SECRET_KEY_REF, OPTIONAL)))
                                                            .map(Boolean::parseBoolean).orElse(false)
                                            )
                                            .build()
                                            .buildEnvironmentSource().buildEnvironment();
                                }
                            }
                        }
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
                container.buildContainer();
            });
        }
    }

}
