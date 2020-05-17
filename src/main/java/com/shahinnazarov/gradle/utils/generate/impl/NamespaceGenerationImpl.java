package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.Namespace;
import com.shahinnazarov.gradle.utils.generate.ResourceGeneration;

import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class NamespaceGenerationImpl implements ResourceGeneration<Namespace> {
    private ContextTypes CONTEXT_TYPE = ContextTypes.NAMESPACE;

    @Override
    public Namespace generate(String groupId, Properties properties) {
        String name = getFromProperties(properties, getFullKey(groupId, NAME), extractId(CONTEXT_TYPE, groupId));

        return Namespace.instance()
                .metadata()
                .name(name)
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .buildNamespace();
    }
}
