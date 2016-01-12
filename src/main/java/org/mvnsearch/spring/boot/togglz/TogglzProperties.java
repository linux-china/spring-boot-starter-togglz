package org.mvnsearch.spring.boot.togglz;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
     * feature enum
     */
    private List<Class> featureEnum;
    /**
     * features
     */
    private Map<String,Boolean> features;

    public List<Class> getFeatureEnum() {
        return featureEnum;
    }

    public void setFeatureEnum(List<Class> featureEnum) {
        this.featureEnum = featureEnum;
    }

    public Map<String, Boolean> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Boolean> features) {
        this.features = features;
    }
}
