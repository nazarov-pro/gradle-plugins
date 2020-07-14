package com.shahinnazarov.gradle.utils.helpers;

import com.shahinnazarov.gradle.models.k8s.DefaultK8sObject;
import com.shahinnazarov.gradle.models.k8s.PodVolume;

public interface PodVolumeCreation {
    PodVolume<? extends DefaultK8sObject> podVolume();
}
