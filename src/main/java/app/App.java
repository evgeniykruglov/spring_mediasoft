package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableRetry
@SpringBootApplication
@EnableSwagger2 /* http://localhost:8080/swagger-ui/ */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = SpringApplication.run(App.class);
    }
}