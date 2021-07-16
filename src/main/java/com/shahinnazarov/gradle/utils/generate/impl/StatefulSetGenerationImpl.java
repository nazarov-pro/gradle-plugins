package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.*;
import com.shahinnazarov.gradle.utils.converter.AccessModeConverter;
import com.shahinnazarov.gradle.utils.generate.ResourceGeneration;
import com.shahinnazarov.gradle.utils.generate.ResourceGenerationHelper;
import com.shahinnazarov.gradle.utils.helpers.ContainerGenerationHelper;
import com.shahinnazarov.gradle.utils.helpers.PodVolumeGenerationHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class StatefulSetGenerationImpl implements ResourceGeneration<StatefulSet>, ContainerGenerationHelper, PodVolumeGenerationHelper {
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
                .restartPolicy(getFromProperties(properties, getFullKey(groupId, RESTART)))
                .dnsPolicy(getFromProperties(properties, getFullKey(groupId, DNS_POLICY)))
                .hostNetwork(getFromPropertiesAsBoolean(properties, getFullKey(groupId, HOST_NETWORK)))
                .buildPodTemplateSpec()
                .buildPodTemplate()
                .buildSpec()
                .build();
        String pullSecret = getFromProperties(properties, getFullKey(groupId, IMAGE_PULL_SECRET));
        if (pullSecret != null && !pullSecret.isEmpty()) {
            statefulSet.spec().podTemplate().spec().addImagePullSecret()
                    .name(pullSecret)
                    .buildImagePullSecret()
                    .buildPodTemplateSpec()
                    .buildPodTemplate()
                    .buildSpec()
                    .build();
        }

        Map<String, String> nodeSelector = getAsMap(getFullKey(groupId, NODE_SELECTOR), properties);
        String selectedNode = getFromProperties(properties, getFullKey(groupId, SELECTED_NODE));
        if (nodeSelector == null && selectedNode != null) {
            nodeSelector = new HashMap<>(1);
            nodeSelector.put(KUBERNETES_HOSTNAME, selectedNode);
        }

        if(nodeSelector != null) {
            statefulSet.spec().podTemplate()
                    .spec()
                    .nodeSelector(nodeSelector)
                    .buildPodTemplateSpec()
                    .buildPodTemplate()
                    .buildSpec()
                    .build();
        }


        configureStrategy(groupId, properties, statefulSet);
        configureSelectors(groupId, properties, statefulSet);
        addVolumes(groupId, properties, () -> statefulSet.spec().podTemplate().spec().addVolume());
        addContainers(groupId, properties, () -> statefulSet.spec().podTemplate().spec().addContainer());
        configureVolumeClaims(groupId, properties, statefulSet);
        return statefulSet;
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
                Integer rollingUpdatePartition = getFromPropertiesAsInteger(properties, join(strategyKey, ROLLING_UPDATE, PARTITION));

                statefulSet.spec().updateStrategy()
                        .rollingUpdate()
                        .partition(rollingUpdatePartition)
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
