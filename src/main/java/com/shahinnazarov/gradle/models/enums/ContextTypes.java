package com.shahinnazarov.gradle.models.enums;

import com.shahinnazarov.gradle.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ContextTypes {
    NAMESPACE("ns"),
    PERSISTENT_VOLUME_CLAIM("pvc"),
    SERVICE("svc"),
    DEPLOYMENT("deploy"),
    STATEFUL_SET("ss"),
    UNKNOWN("unknown");

    private String prefix;
    private static final String GROUP_ID_SEPARATOR = ".";

    public static ContextTypes getContextType(String groupId) {
        ContextTypes contextType = UNKNOWN;

        for (ContextTypes type : values()) {
            if (groupId.startsWith(type.prefix)) {
                contextType = type;
            }
        }
        return contextType;
    }

    public static String getGroupId(String key) {
        if (key.startsWith(Constants.K8S_PREFIX)) {
            String[] parameters = key.split("\\.");
            if (parameters.length > 2) {
                return parameters[1] + GROUP_ID_SEPARATOR + parameters[2];
            }
        }
        throw new RuntimeException("Please check context key: " + key);
    }

    public String getId(String groupId) {
        String group = getPrefix().concat(GROUP_ID_SEPARATOR);
        if (groupId.startsWith(group)) {
            return groupId.substring(group.length());
        }
        throw new RuntimeException("Extracting id from group id failed. Group id: " + groupId);
    }

    public String generateGroupId(String id) {
        return getPrefix().concat(GROUP_ID_SEPARATOR).concat(id);
    }
}
