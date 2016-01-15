package org.mvnsearch.spring.boot.togglz;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.togglz.core.Feature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration properties for Togglz
 *
 * @author linux_china
 */
@ConfigurationProperties(prefix = "spring.togglz")
public class TogglzProperties {

    /**
     * feature enums
     */
    private Class<? extends Feature>[] featureEnums;
    /**
     * features
     */
    private Map<String, FeatureStateLite> features = new HashMap<>();

    public Class<? extends Feature>[] getFeatureEnums() {
        return featureEnums;
    }

    public void setFeatureEnums(Class<? extends Feature>[] featureEnums) {
        this.featureEnums = featureEnums;
    }

    public Map<String, FeatureStateLite> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, FeatureStateLite> features) {
        this.features = features;
    }
}
