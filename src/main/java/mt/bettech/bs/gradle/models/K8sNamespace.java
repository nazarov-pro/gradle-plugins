package mt.bettech.bs.gradle.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class K8sNamespace {
    private String apiVersion = "v1";
    private String kind = "Namespace";
    private K8sMetadata metadata;

    public K8sNamespace(K8sMetadata metadata) {
        this.metadata = metadata;
    }
}
