package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.DefaultK8sResource;
import com.shahinnazarov.gradle.utils.K8sContext;
import com.shahinnazarov.gradle.utils.generate.impl.ExpressionSupplierImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public interface ResourceGeneration<E extends DefaultK8sResource<E>> {
    ExpressionSupplier EXPRESSION_SUPPLIER = new ExpressionSupplierImpl();

    E generate(String groupId, Properties properties);

    default String extractId(ContextTypes contextType, String groupId) {
        return contextType.getId(groupId);
    }

    default String getFullKey(String groupId, String key) {
        return K8S_PREFIX + "." + groupId + "." + key;
    }

    default String join(String... keys) {
        String result = "";
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1) {
                result += keys[i];
            } else {
                result += keys[i] + ".";
            }
        }
        return result;
    }

    default Map<String, String> getAsMap(String keyPrefix, Properties properties) {
        Map<String, String> map = new HashMap<>();
        properties.entrySet()
                .stream()
                .filter(entry -> entry.getKey().toString().startsWith(keyPrefix))
                .forEach(
                        entry -> {
                            String key = entry.getKey().toString();
                            if (key.endsWith("." + KEY)) {
                                String valueKey = key.substring(0, key.length() - KEY.length()).concat(VALUE);
                                String value = getFromProperties(properties, valueKey);
                                map.put(entry.getValue().toString(), value);
                            } else if (key.endsWith("." + VALUE)) {
                                //
                            } else {
                                map.put(key.substring(keyPrefix.length() + 1),
                                        EXPRESSION_SUPPLIER.applyExpressions(entry.getValue().toString()));
                            }
                        }
                );
        return map.size() == 0 ? null : map;
    }

    default Map<String, Map<String, String>> getAsMapByGroupId(String keyPrefix, Properties properties, int groupId) {
        Map<String, Map<String, String>> map = new HashMap<>();
        properties.entrySet()
                .stream()
                .filter(entry -> entry.getKey().toString().startsWith(keyPrefix))
                .forEach(
                        entry -> {
                            String key = entry.getKey().toString();
                            String id = key.substring(keyPrefix.length() + 1).split(KEY_SPLITTER_REGEX)[groupId];
                            Map<String, String> parameters = map.getOrDefault(id, new HashMap<String, String>());
                            parameters.put(key, EXPRESSION_SUPPLIER.applyExpressions(entry.getValue().toString()));
                            map.put(id, parameters);
                        }
                );
        return map.size() == 0 ? null : map;
    }

    default String getNamespace(String id) {
        String[] namespaceAndId = id.split("/");
        if (namespaceAndId.length == 2) {
            String namespace = namespaceAndId[0];
            return getFromContext(getFullKey(ContextTypes.NAMESPACE.generateGroupId(namespace), NAME), namespace);
        }
        return "default";
    }

    default String getFromContext(String id, String defaultValue) {
        Properties properties = K8sContext.getPropertiesContext();
        return getFromProperties(properties, id, defaultValue);
    }

    default String getFromContext(String id) {
        return getFromContext(id, null);
    }

    default String getFromProperties(Properties properties, String id, String defaultValue) {
        String value = properties.getProperty(id, defaultValue);
        return EXPRESSION_SUPPLIER.applyExpressions(value);
    }

    default String getFromProperties(Properties properties, String id) {
        return getFromProperties(properties, id, null);
    }

    default boolean containsKey(String id) {
        return getFromContext(id) != null;
    }

    default boolean containsKey(Properties properties, String id) {
        return getFromProperties(properties, id) != null;
    }

    default Integer getFromPropertiesAsInteger(Properties properties, String id) {
        String value = getFromProperties(properties, id);
        if(value != null) {
            return Integer.parseInt(value);
        }
        return null;
    }

    default String expression(String value) {
        String regex = "${}";
        return value;
    }
}
