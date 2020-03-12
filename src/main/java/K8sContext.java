public class K8sContext {
    public static K8sFileBuilder createNamespace(String projectId) {
        return new K8sFileBuilder().addNamespaceForRancher("default", projectId);
    }

    public static K8sFileBuilder createNamespace(String name, String projectId) {
        return new K8sFileBuilder().addNamespaceForRancher(name, projectId);
    }
}
