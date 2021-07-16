package com.shahinnazarov.gradle.models;

import com.shahinnazarov.gradle.models.enums.K8sPodTemplateSpecDnsPolicies;
import com.shahinnazarov.gradle.models.enums.K8sPodTemplateSpecRestartPolicies;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class K8sPodTemplateSpec {
    private String imagePullSecret;
    private List<K8sPodTemplateSpecVolume> volumes;
    private List<K8sPodTemplateSpecContainer> containers;
    private Map<String, String> nodeSelector;
    private K8sPodTemplateSpecRestartPolicies restartPolicy;

}
