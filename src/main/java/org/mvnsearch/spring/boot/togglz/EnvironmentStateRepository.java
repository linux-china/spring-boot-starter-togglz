package org.mvnsearch.spring.boot.togglz;

import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

/**
 * environment state repository
 *
 * @author linux_china
 */
public class EnvironmentStateRepository implements StateRepository {
    private TogglzProperties properties;

    public EnvironmentStateRepository(TogglzProperties togglzProperties) {
        this.properties = togglzProperties;
    }

    public FeatureState getFeatureState(Feature feature) {
        return new EnvironmentFeatureState(feature, properties);
    }

    public void setFeatureState(FeatureState featureState) {
        String name = featureState.getFeature().name();
        FeatureStateLite result = properties.getFeatures().get(name);
        if (result != null) {
            result.setEnabled(featureState.isEnabled());
        }
    }
}
