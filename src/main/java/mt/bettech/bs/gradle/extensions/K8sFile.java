package mt.bettech.bs.gradle.extensions;

import mt.bettech.bs.gradle.models.K8sDeployment;
import mt.bettech.bs.gradle.models.K8sNamespace;
import mt.bettech.bs.gradle.models.K8sPersistentVolumeClaim;
import mt.bettech.bs.gradle.models.K8sService;
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
