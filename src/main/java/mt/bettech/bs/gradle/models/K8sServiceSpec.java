package mt.bettech.bs.gradle.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class K8sServiceSpec {
    private Map<String, String> selector;
    private String type;
    private List<K8sServiceSpecPort> ports;

    public K8sServiceSpec(Map<String, String> selector, String type, List<K8sServiceSpecPort> ports) {
        this.selector = selector;
        this.type = type;
        this.ports = ports;
    }
}
