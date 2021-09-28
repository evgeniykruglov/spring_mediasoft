package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = SpringApplication.run(App.class);
        applicationContext.getBean(UserController.class);
    }
}