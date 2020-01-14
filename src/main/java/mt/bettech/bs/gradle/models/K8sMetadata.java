package mt.bettech.bs.gradle.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class K8sMetadata {
    private String name;
    private String namespace;
    private Map<String, String> labels;
    private Map<String, String> annotations;

    public K8sMetadata(String name, Map<String, String> annotations) {
        this.name = name;
        this.annotations = annotations;
    }


    public K8sMetadata(String name, String namespace, Map<String, String> labels, Map<String, String> annotations) {
        this.name = name;
        this.namespace = namespace;
        this.labels = labels;
        this.annotations = annotations;
    }

    public K8sMetadata(String name, String namespace) {
        this.name = name;
        this.namespace = namespace;
    }

    public K8sMetadata(Map<String, String> labels) {
        this.labels = labels;
    }

    public K8sMetadata(String name, String namespace, Map<String, String> labels) {
        this.name = name;
        this.namespace = namespace;
        this.labels = labels;
    }
}
