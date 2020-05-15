package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.Namespace;

import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public class K8sNamespaceGenerationImpl implements ResourceGeneration<Namespace> {
    private ContextTypes CONTEXT_TYPE = ContextTypes.NAMESPACE;

    @Override
    public Namespace generate(String groupId, Properties properties) {
        String name = extractId(CONTEXT_TYPE, groupId);

        if (properties.containsKey(getFullKey(groupId, NAME))) {
            name = properties.getProperty(getFullKey(groupId, NAME));
        }

        return Namespace.instance()
                .metadata()
                .name(name)
                .labels(getAsMap(getFullKey(groupId, LABELS), properties))
                .annotations(getAsMap(getFullKey(groupId, ANNOTATIONS), properties))
                .buildMetadata()
                .buildNamespace();
    }
}
