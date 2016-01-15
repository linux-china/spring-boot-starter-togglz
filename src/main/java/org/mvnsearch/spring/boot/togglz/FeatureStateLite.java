package org.mvnsearch.spring.boot.togglz;

import java.util.HashMap;
import java.util.Map;

/**
 * feature state lite
 *
 * @author linux_china
 */
public class FeatureStateLite {
    private boolean enabled;
    private String strategy;
    private final Map<String, String> param = new HashMap<String, String>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Map<String, String> getParam() {
        return param;
    }
}
