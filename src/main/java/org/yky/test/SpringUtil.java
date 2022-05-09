package org.yky.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取ActiveProfile
     *
     * @return
     */
    public static String getActiveProfile() {
        String activeProfile = applicationContext.getEnvironment().getActiveProfiles()[0];
//        if (StringUtils.isBlank(activeProfile)) {
//            activeProfile = System.getProperty("spring.profiles.active");
//        }
        return activeProfile;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return applicationContext.getBean(name, type);
    }

    public static <T> Map<String, T> getBeans(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    public static String getProperty(String key, boolean required) {
        if (required) {
            return applicationContext.getEnvironment().getRequiredProperty(key);
        } else {
            return applicationContext.getEnvironment().getProperty(key);
        }
    }

    public static String resolvePlaceholders(String text, boolean required) {
        if (required) {
            return applicationContext.getEnvironment().resolveRequiredPlaceholders(text);
        } else {
            return applicationContext.getEnvironment().resolvePlaceholders(text);
        }
    }
//
//    public static void publishEvent(ConfigChangeEvent changeEvent) {
//        applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
//        ApolloUtil.printChange(changeEvent);
//    }

    public static void publishNotifyEvent(Object event) {
        applicationContext.publishEvent(event);
    }

}
