package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.*;
import com.shahinnazarov.gradle.utils.generate.ResourceGeneration;
import com.shahinnazarov.gradle.utils.generate.ResourceGenerationHelper;
import com.shahinnazarov.gradle.utils.helpers.ContainerGenerationHelper;
import com.shahinnazarov.gradle.utils.helpers.PodVolumeGenerationHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class DeploymentGenerationImpl implements ResourceGeneration<Deployment>, ContainerGenerationHelper, PodVolumeGenerationHelper {
    private final ContextTypes CONTEXT_TYPE = ContextTypes.DEPLOYMENT;

    @Override
    public Deployment generate(String groupId, Properties properties) {
        String name = getFromProperties(properties, getFullKey(groupId, NAME),
                extractId(CONTEXT_TYPE, groupId));

        Deployment deployment = Deployment
                .instance()
                .metadata()
                .name(name)
                .namespace(getNamespace(CONTEXT_TYPE.getId(groupId)))
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .spec()
                .replicas(getFromPropertiesAsInteger(properties, getFullKey(groupId, REPLICAS)))
                .podTemplate()
                .spec()
                .addImagePullSecret()
                .name(getFromProperties(properties, getFullKey(groupId, IMAGE_PULL_SECRET)))
                .buildImagePullSecret()
                .restartPolicy(getFromProperties(properties, getFullKey(groupId, RESTART)))
                .buildPodTemplateSpec()
                .buildPodTemplate()
                .buildSpecification()
                .buildDeployment();

        Map<String, String> nodeSelector = getAsMap(getFullKey(groupId, NODE_SELECTOR), properties);
        String selectedNode = getFromProperties(properties, getFullKey(groupId, SELECTED_NODE));
        if (nodeSelector == null && selectedNode != null) {
            nodeSelector = new HashMap<>(1);
            nodeSelector.put(KUBERNETES_HOSTNAME, selectedNode);
        }

        if(nodeSelector != null) {
            deployment.spec().podTemplate()
                    .spec()
                    .nodeSelector(nodeSelector)
                    .buildPodTemplateSpec()
                    .buildPodTemplate()
                    .buildSpecification()
                    .buildDeployment();
        }

        configureStrategy(groupId, properties, deployment);
        configureSelectors(groupId, properties, deployment);
        addVolumes(groupId, properties, () -> deployment.spec().podTemplate().spec().addVolume());
        addContainers(groupId, properties, () -> deployment.spec().podTemplate().spec().addContainer());
        return deployment;
    }

    private void configureSelectors(String groupId, Properties properties, Deployment deployment) {
        String selectorKey = getFullKey(groupId, SELECTOR);
        Map<String, String> labels = getAsMap(join(selectorKey, LABELS), properties);
        if (labels != null) {
            labels.forEach((key, value) -> {
                deployment
                        .spec()
                        .selector()
                        .addMatchLabel(key, value)
                        .buildSelector()
                        .podTemplate()
                        .metadata()
                        .addLabel(key, value)
                        .buildMetadata()
                        .buildPodTemplate()
                        .buildSpecification()
                        .buildDeployment();
            });
        }
    }

    private void configureStrategy(String groupId, Properties properties, Deployment deployment) {
        String strategyKey = getFullKey(groupId, STRATEGY);

        String strategyType = getFromProperties(properties, join(strategyKey, TYPE), "none");

        switch (strategyType) {
            case RECREATE:
                deployment.spec().deploymentStrategy()
                        .recreate()
                        .buildDeploymentStrategy()
                        .buildSpecification()
                        .buildDeployment();
                break;
            case ROLLING_UPDATE:
                Object rollingUpdateMaxSurge = getFromPropertiesAsIntegerOrString(properties, join(strategyKey, ROLLING_UPDATE, MAX_SURGE));
                Object rollingUpdateMaxUnavailable = getFromPropertiesAsIntegerOrString(properties, join(strategyKey, ROLLING_UPDATE, MAX_UNAVAILABLE));

                deployment.spec().deploymentStrategy()
                        .rollingUpdate()
                        .maxSurge(rollingUpdateMaxSurge)
                        .maxUnavailable(rollingUpdateMaxUnavailable)
                        .buildRollingUpdate()
                        .buildDeploymentStrategy()
                        .buildSpecification()
                        .buildDeployment();
                break;
        }
    }
}
