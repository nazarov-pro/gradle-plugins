package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sPodTemplate {
    private K8sMetadata metadata;
    private K8sPodTemplateSpec spec;

    public K8sPodTemplate(K8sMetadata metadata, K8sPodTemplateSpec spec) {
        this.metadata = metadata;
        this.spec = spec;
    }
}
