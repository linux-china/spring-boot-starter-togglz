package org.mvnsearch.spring.boot.togglz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * togglz operation endpoint
 *
 * @author leijuan
 */
@Controller
public class TogglzOperationEndpoint implements MvcEndpoint {
    @Autowired
    private TogglzProperties properties;

    @RequestMapping(value = "/enable/{feature}", method = RequestMethod.GET)
    @ResponseBody()
    public String enable(@PathVariable String feature) {
        FeatureStateLite result = properties.getFeatures().get(feature);
        if(result!=null) {
            result.setEnabled(true);
        }
        return "success";
    }

    @RequestMapping("/disable/{feature}")
    @ResponseBody()
    public String disable(@PathVariable String feature) {
        FeatureStateLite result = properties.getFeatures().get(feature);
               if(result!=null) {
                   result.setEnabled(true);
               }
        return "success";
    }

    public String getPath() {
        return "switch";
    }

    public boolean isSensitive() {
        return false;
    }

    public Class<? extends Endpoint> getEndpointType() {
        return null;
    }
}
