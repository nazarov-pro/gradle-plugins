package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sResource {
    private K8sResourceRequest request;

    public K8sResource(K8sResourceRequest request) {
        this.request = request;
    }
}
