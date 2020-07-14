package com.shahinnazarov.gradle.utils.helpers;

import com.shahinnazarov.gradle.models.k8s.*;
import com.shahinnazarov.gradle.utils.generate.ResourceGenerationHelper;

import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;
import static com.shahinnazarov.gradle.utils.Constants.NAME;

public interface PodVolumeGenerationHelper extends ResourceGenerationHelper {
    default void addVolumes(String groupId, Properties properties, PodVolumeCreation podVolumeCreation) {
        String volumesKey = getFullKey(groupId, VOLUMES);
        Map<String, Map<String, String>> volumes = getAsMapByGroupId(volumesKey, properties, 0);
        if (volumes != null) {
            volumes.forEach((id, value) -> {
                String name = value.getOrDefault(join(volumesKey, id, NAME), id);
                PodVolume<?> podTemplateSpecPodVolume = podVolumeCreation.podVolume();

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
                podTemplateSpecPodVolume.buildPodVolume();
            });
        }
    }
}
