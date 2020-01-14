package mt.bettech.bs.gradle.models;

import mt.bettech.bs.gradle.models.enums.K8sPodTemplateSpecRestartPolicies;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class K8sPodTemplateSpec {
    private String imagePullSecret;
    private List<K8sPodTemplateSpecVolume> volumes;
    private List<K8sPodTemplateSpecContainer> containers;
    private Map<String, String> nodeSelector;
    private K8sPodTemplateSpecRestartPolicies restartPolicy;

    public K8sPodTemplateSpec(String imagePullSecret, List<K8sPodTemplateSpecVolume> volumes,
                              List<K8sPodTemplateSpecContainer> containers, Map<String, String> nodeSelector,
                              K8sPodTemplateSpecRestartPolicies restartPolicy) {
        this.imagePullSecret = imagePullSecret;
        this.volumes = volumes;
        this.containers = containers;
        this.nodeSelector = nodeSelector;
        this.restartPolicy = restartPolicy;
    }
}
