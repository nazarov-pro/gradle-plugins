package mt.bettech.bs.gradle.models;

import lombok.Data;

@Data
public class K8sPodTemplateSpecVolumeClaim {
    private String claimName;

    public K8sPodTemplateSpecVolumeClaim(String claimName) {
        this.claimName = claimName;
    }
}
