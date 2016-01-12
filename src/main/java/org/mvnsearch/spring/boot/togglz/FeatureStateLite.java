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
    private String strategyId;
    private final Map<String, String> parameters = new HashMap<String, String>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
