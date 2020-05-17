package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.PersistentVolumeClaim;
import com.shahinnazarov.gradle.utils.converter.AccessModeConverter;

import java.util.List;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class PersistentVolumeClaimGenerationImpl implements ResourceGeneration<PersistentVolumeClaim> {
    private ContextTypes CONTEXT_TYPE = ContextTypes.PERSISTENT_VOLUME_CLAIM;

    @Override
    public PersistentVolumeClaim generate(String groupId, Properties properties) {
        String name = getFromProperties(properties, getFullKey(groupId, NAME),
                extractId(CONTEXT_TYPE, groupId));

        List<String> accessModes = new AccessModeConverter().convert(
                getFromProperties(properties, getFullKey(groupId, ACCESS_MODES)),
                STRING_ARRAY_SPLITTER_REGEX
        );

        return PersistentVolumeClaim.
                instance()
                .metadata()
                .name(name)
                .namespace(getNamespace(CONTEXT_TYPE.getId(groupId)))
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .spec()
                .accessModes(accessModes)
                .resources()
                .limits(getAsMap(getFullKey(groupId, RESOURCES_LIMITS), properties))
                .requests(getAsMap(getFullKey(groupId, RESOURCES_REQUESTS), properties))
                .buildResources()
                .storageClassName(getFromProperties(properties, getFullKey(groupId, STORAGE_CLASS_NAME)))
                .buildPvcSpec()
                .buildPersistentVolumeClaim();


    }


}
