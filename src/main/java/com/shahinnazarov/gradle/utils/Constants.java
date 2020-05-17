package com.shahinnazarov.gradle.utils;

public final class Constants {
    public static final String K8S_PREFIX = "k8s";
    public static final String VARIABLE = "var";
    public static final String YAML_RESOURCE_SEPARATOR = "---";

    public static final String STRING_ARRAY_SPLITTER_REGEX = "\\|";
    public static final String KEY_SPLITTER_REGEX = "\\.";

    public static final String KEY = "key";
    public static final String VALUE = "value";
    public static final String NAME = "name";
    public static final String LABELS = "labels";
    public static final String ANNOTATIONS = "annotations";

    public static final String STORAGE_CLASS_NAME = "storageClass";
    public static final String RESOURCES_REQUESTS = "resources.requests";
    public static final String RESOURCES_LIMITS = "resources.limits";
    public static final String ACCESS_MODES = "accessModes";

    public static final String TYPE = "type";
    public static final String SELECTOR = "selector";
    public static final String PORTS = "ports";
    public static final String PROTOCOL = "protocol";
    public static final String TARGET_PORT = "targetPort";
    public static final String NODE_PORT = "nodePort";
    public static final String PORT = "port";

    public static final String CONTAINERS = "containers";
    public static final String IMAGE = "image";
    public static final String ENV = "env";
    public static final String MOUNTS = "mounts";
    public static final String REPLICAS = "replicas";
    public static final String RESTART = "restart";
    public static final String SELECTED_NODE = "selectedNode";
    public static final String IMAGE_PULL_SECRET = "imagePullSecret";
    public static final String VOLUMES = "volumes";
    public static final String CONTAINER_PORT = "containerPort";
    public static final String MOUNT_PATH = "mountPath";
    public static final String PATH = "path";
    public static final String READINESS_PROBE = "readinessProbe";
    public static final String LIVENESS_PROBE = "livenessProbe";
    public static final String STRATEGY = "strategy";
    public static final String ROLLING_UPDATE = "rollingUpdate";
    public static final String RECREATE = "recreate";
    public static final String MAX_SURGE = "maxSurge";
    public static final String MAX_UNAVAILABLE = "maxUnavailable";

    public static final String RESOURCES = "resources";
    public static final String REQUESTS = "requests";
    public static final String LIMITS = "limits";


    public static final String VOLUME_CLAIMS = "volumeClaims";
    public static final String SERVICE_NAME = "serviceName";
}
