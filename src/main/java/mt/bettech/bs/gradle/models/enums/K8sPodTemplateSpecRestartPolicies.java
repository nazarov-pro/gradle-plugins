package mt.bettech.bs.gradle.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum K8sPodTemplateSpecRestartPolicies {
    Always("Always"),
    OnFailure("OnFailure"),
    Never("Never")
    ;
    private String type;
}
