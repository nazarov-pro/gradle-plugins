package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sDeploymentSpec {
    private Integer replicaCount;
    private K8sSelector selector;
    private K8sPodTemplate template;

    public K8sDeploymentSpec(Integer replicaCount, K8sSelector selector, K8sPodTemplate template) {
        this.replicaCount = replicaCount;
        this.selector = selector;
        this.template = template;
    }

    public K8sDeploymentSpec(Integer replicaCount, K8sPodTemplate template) {
        this.replicaCount = replicaCount;
        this.template = template;
    }
}
