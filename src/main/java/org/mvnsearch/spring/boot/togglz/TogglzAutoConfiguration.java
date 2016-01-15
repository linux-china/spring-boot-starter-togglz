package org.mvnsearch.spring.boot.togglz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.activation.ActivationStrategyProvider;
import org.togglz.core.activation.DefaultActivationStrategyProvider;
import org.togglz.core.context.StaticFeatureManagerProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import java.util.List;

/**
 * togglz auto configuration
 *
 * @author linux_china
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableConfigurationProperties(TogglzProperties.class)
@ConditionalOnProperty("spring.togglz.featureEnums")
public class TogglzAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(FeatureManager.class)
    protected static class FeatureManagerConfiguration {
        @Autowired
        private TogglzProperties properties;
        @Autowired
        private ActivationStrategyProvider activationStrategyProvider;
        @Autowired
        private UserProvider userProvider;

        @Bean
        public FeatureManager featureManager() {
            FeatureManagerBuilder builder = new FeatureManagerBuilder();
            builder.featureEnums(properties.getFeatureEnums()).activationStrategyProvider(activationStrategyProvider)
                    .stateRepository(new EnvironmentStateRepository(properties)).userProvider(userProvider);
            FeatureManager featureManager = builder.build();
            StaticFeatureManagerProvider.setFeatureManager(featureManager);
            return featureManager;
        }
    }

    @Configuration
    @ConditionalOnMissingBean(ActivationStrategyProvider.class)
    protected static class ActivationStrategyProviderConfiguration {
        @Autowired(required = false)
        private List<ActivationStrategy> activationStrategies;

        @Bean
        public ActivationStrategyProvider activationStrategyProvider() {
            DefaultActivationStrategyProvider provider = new DefaultActivationStrategyProvider();
            if (activationStrategies != null && activationStrategies.size() > 0) {
                // https://github.com/togglz/togglz/pull/149
                // provider.addActivationStrategies(activationStrategies);
                for (ActivationStrategy activationStrategy : activationStrategies) {
                    provider.addActivationStrategy(activationStrategy);
                }
            }
            return provider;
        }
    }

    @Configuration
    @ConditionalOnMissingBean(UserProvider.class)
    protected static class UserProviderConfiguration {
        @Bean
        public UserProvider userProvider() {
            return new NoOpUserProvider();
        }
    }

    @Bean
    public TogglzOperationEndpoint switchOperationEndpoint() {
        return new TogglzOperationEndpoint();
    }

    @Bean
    public TogglzEndpoint togglzEndpoint(TogglzProperties properties) {
        return new TogglzEndpoint(properties);
    }

}
