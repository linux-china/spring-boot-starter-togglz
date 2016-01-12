package org.mvnsearch.spring.boot.togglz;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.HashMap;
import java.util.Map;

/**
 * togglz endpoint
 *
 * @author linux_china
 */
public class TogglzEndpoint extends AbstractEndpoint<Map<String, Object>> {
    private TogglzProperties properties;

    public TogglzEndpoint(TogglzProperties properties) {
        super("togglz");
        this.properties = properties;
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> info = new HashMap<>();
        info.put("togglz", properties);
        return info;
    }

}
