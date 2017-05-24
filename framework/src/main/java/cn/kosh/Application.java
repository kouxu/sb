package cn.kosh;

import cn.kosh.framework.SpringHolder;
import cn.kosh.framework.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"cn.kosh"}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class Application {

    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        ApplicationContext context = SpringApplication.run(Application.class, args);
        SpringHolder.setApplicationContext(context);
    }

}
