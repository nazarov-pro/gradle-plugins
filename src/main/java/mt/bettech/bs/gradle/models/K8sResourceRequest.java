package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sResourceRequest {
    private String storageSize;

    public K8sResourceRequest(String storageSize) {
        this.storageSize = storageSize;
    }
}
