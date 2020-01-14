package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sService {
    private String apiVersion = "v1";
    private String kind = "Service";
    private K8sMetadata metadata;
    private K8sServiceSpec spec;

    public K8sService(K8sMetadata metadata, K8sServiceSpec spec) {
        this.metadata = metadata;
        this.spec = spec;
    }
}
