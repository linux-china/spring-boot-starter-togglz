package org.mvnsearch.spring.boot.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.repository.FeatureState;

import java.util.Map;

/**
 * environment feature state
 *
 * @author linux_china
 */
public class EnvironmentFeatureState extends FeatureState {
    private TogglzProperties properties;

    public EnvironmentFeatureState(Feature feature, TogglzProperties properties) {
        super(feature);
        this.properties = properties;
        FeatureStateLite featureStateLite = properties.getFeatures().get(feature.name());
        if (featureStateLite != null) {
            setStrategyId(featureStateLite.getStrategyId());
            if (!featureStateLite.getParameters().isEmpty()) {
                for (Map.Entry<String, String> entry : featureStateLite.getParameters().entrySet()) {
                    setParameter(entry.getKey(), entry.getValue());
                }
            }
        } else {
            FeatureStateLite stateLite = new FeatureStateLite();
            stateLite.setEnabled(false);
            try {
                EnabledByDefault[] enabledByDefaults = feature.getClass().getDeclaredField(feature.name()).getAnnotationsByType(EnabledByDefault.class);
                if (enabledByDefaults != null) {
                    stateLite.setEnabled(true);
                }
            } catch (Exception ignore) {

            }
            properties.getFeatures().put(feature.name(), stateLite);
        }
    }

    /**
     * Creates a copy of this state object
     */
    public FeatureState copy() {
        FeatureState copy = new EnvironmentFeatureState(getFeature(), this.properties);
        copy.setStrategyId(getStrategyId());
        for (Map.Entry<String, String> entry : getParameterMap().entrySet()) {
            copy.setParameter(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    @Override
    public boolean isEnabled() {
        FeatureStateLite result = properties.getFeatures().get(getFeature().name());
        return result.isEnabled();
    }

    @Override
    public FeatureState setEnabled(boolean enabled) {
        FeatureStateLite result = properties.getFeatures().get(getFeature().name());
        result.setEnabled(enabled);
        return this;
    }
}
