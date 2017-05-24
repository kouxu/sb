package cn.kosh.framework;

import cn.kosh.framework.web.security.AccountCredentials;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by kosh on 2017/4/30.
 */
public class SpringHolder {
    private static ApplicationContext context;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

    public static String getProperty(String key) {
        return context.getEnvironment().getProperty(key);
    }

    public static String getProperty(String key, String defaultVal) {
        return context.getEnvironment().getProperty(key, defaultVal);
    }

    public static <T> T getProperty(String key, Class<T> clazz) {
        return context.getEnvironment().getProperty(key, clazz);
    }

    public static <T> T getProperty(String key, Class<T> clazz, T defaultVal) {
        return context.getEnvironment().getProperty(key, clazz, defaultVal);
    }

    public static AccountCredentials getCurrentuser() {
        return (AccountCredentials) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
