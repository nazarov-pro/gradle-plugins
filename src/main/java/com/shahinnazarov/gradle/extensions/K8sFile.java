package com.shahinnazarov.gradle.extensions;

import com.shahinnazarov.gradle.models.K8sDeployment;
import com.shahinnazarov.gradle.models.K8sNamespace;
import com.shahinnazarov.gradle.models.K8sPersistentVolumeClaim;
import com.shahinnazarov.gradle.models.K8sService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class K8sFile {
    private List<K8sNamespace> namespaces = new ArrayList<>();
    private List<K8sPersistentVolumeClaim> persistentVolumeClaims = new ArrayList<>();
    private List<K8sService> services = new ArrayList<>();
    private List<K8sDeployment> deployments = new ArrayList<>();

}
