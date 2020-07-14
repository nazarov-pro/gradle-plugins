package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ContextTypes;
import com.shahinnazarov.gradle.models.k8s.DefaultK8sResource;
import com.shahinnazarov.gradle.utils.K8sContext;
import com.shahinnazarov.gradle.utils.generate.impl.ExpressionSupplierImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.shahinnazarov.gradle.utils.Constants.*;

public interface ResourceGeneration<E extends DefaultK8sResource<E>> {
    E generate(String groupId, Properties properties);
}
