package com.shahinnazarov.gradle.utils.generate.impl;

import com.shahinnazarov.gradle.models.enums.ExpressionSourceType;
import com.shahinnazarov.gradle.utils.Constants;
import com.shahinnazarov.gradle.utils.K8sContext;
import com.shahinnazarov.gradle.utils.generate.ExpressionSupplier;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionSupplierImpl implements ExpressionSupplier {
    private static final String REGEX_PATTERN_TEXT = "\\$\\{([A-Za-z0-9._-]+)\\}";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN_TEXT);
    private static final String ENVIRONMENT = "env";
    private static final String SYSTEM = "system";
    private static final String GLOBAL = "global";
    private static final int MAX_DEPTH = 3;

    @Override
    public int countOfExpressions(String value) {
        int count = 0;
        Matcher matcher = PATTERN.matcher(value);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    @Override
    public String applyExpressions(String value) {
        if(value == null) {
            return null;
        }

        Matcher matcher = PATTERN.matcher(value);
        while (matcher.find()) {
            String group = matcher.group(1);
            String groupText = matcher.group();
            String result = getValue(group);
            if (result != null) {
                value = value.replace(groupText, result);
            }
        }
        return value;
    }

    @Override
    public ExpressionSourceType sourceTypeOfFirstExpression(String value) {
        Matcher matcher = PATTERN.matcher(value);
        String group = null;
        if (matcher.find()) {
            group = matcher.group(1);
        }
        return getExpressionType(group);
    }

    private ExpressionSourceType getExpressionType(String group) {
        if (group == null) {
            return ExpressionSourceType.NONE;
        }
        String[] parts = group.split("\\.");
        if (parts.length >= 2) {
            String prefix = parts[0];
            switch (prefix) {
                case ENVIRONMENT:
                    return ExpressionSourceType.ENVIRONMENT;
                case SYSTEM:
                    return ExpressionSourceType.SYSTEM;
                case GLOBAL:
                    return ExpressionSourceType.GLOBAL;
            }
        }
        return ExpressionSourceType.LOCAL;
    }

    public String getValue(String group) {
        String key;
        switch (getExpressionType(group)) {
            case GLOBAL:
                key = group.substring(GLOBAL.length() + 1);
                if (System.getenv(key) != null) {
                    return System.getenv(key);
                } else if (System.getProperty(key) != null) {
                    return System.getProperty(key);
                } else if (getLocalProperty(key) != null) {
                    return getLocalProperty(key);
                } else {
                    return group;
                }
            case ENVIRONMENT:
                key = group.substring(ENVIRONMENT.length() + 1);
                return System.getenv(key);
            case SYSTEM:
                key = group.substring(SYSTEM.length() + 1);
                return System.getProperty(key);
            case LOCAL:
                return getLocalProperty(group);
            default:
            case NONE:
                return group;
        }
    }

    public String getLocalProperty(String id) {
        Properties properties = K8sContext.getPropertiesContext();
        String variableKey = Constants.K8S_PREFIX + "." + Constants.VARIABLE + "." + id;

        if (properties.containsKey(variableKey)) {
            String group = properties.getProperty(variableKey);
            return group;
        } else {
            String resourceReferenceKey = Constants.K8S_PREFIX + "." + id;
            return properties.getProperty(resourceReferenceKey);
        }
    }
}
