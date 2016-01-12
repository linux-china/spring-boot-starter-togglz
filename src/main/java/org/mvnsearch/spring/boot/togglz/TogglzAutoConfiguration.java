package org.mvnsearch.spring.boot.togglz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.context.StaticFeatureManagerProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

/**
 * togglz auto configuration
 *
 * @author linux_china
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableConfigurationProperties(TogglzProperties.class)
@ConditionalOnProperty("spring.togglz.featureEnum")
public class TogglzAutoConfiguration {
    @Autowired
    private TogglzProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public FeatureManager featureManager() {
        FeatureManagerBuilder builder = new FeatureManagerBuilder();
        Class<Feature>[] clazzList = new Class[properties.getFeatureEnum().size()];
        builder.featureEnums(properties.getFeatureEnum().toArray(clazzList))
                .stateRepository(new EnvironmentStateRepository(properties)).userProvider(new NoOpUserProvider());
        FeatureManager featureManager = builder.build();
        StaticFeatureManagerProvider.setFeatureManager(featureManager);
        return featureManager;
    }

    @Bean
    public TogglzOperationEndpoint switchOperationEndpoint() {
        return new TogglzOperationEndpoint();
    }

    @Bean
    public TogglzEndpoint togglzEndpoint(TogglzProperties properties, FeatureManager featureManager) {
        return new TogglzEndpoint(properties);
    }

}
