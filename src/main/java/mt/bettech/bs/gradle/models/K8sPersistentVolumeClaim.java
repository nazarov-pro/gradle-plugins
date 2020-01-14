package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sPersistentVolumeClaim {
    private String apiVersion = "v1";
    private String kind = "PersistentVolumeClaim";
    private K8sMetadata metadata;
    private K8sPersistentVolumeClaimSpec spec;

    public K8sPersistentVolumeClaim(K8sMetadata metadata, K8sPersistentVolumeClaimSpec spec) {
        this.metadata = metadata;
        this.spec = spec;
    }
}
