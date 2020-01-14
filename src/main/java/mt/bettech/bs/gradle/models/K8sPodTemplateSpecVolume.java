package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sPodTemplateSpecVolume {
    private String name;
    private K8sPodTemplateSpecVolumeClaim persistentVolumeClaim;

    public K8sPodTemplateSpecVolume(String name, K8sPodTemplateSpecVolumeClaim persistentVolumeClaim) {
        this.name = name;
        this.persistentVolumeClaim = persistentVolumeClaim;
    }
}
