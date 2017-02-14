package no.rogfk.guestuser;

import no.rogfk.sms.annotations.EnableSmsWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.Properties;

@EnableSmsWrapper
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);

        // Modify default properties
        Properties properties = new Properties();
        properties.put("spring.mvc.throw-exception-if-no-handler-found", true);
        app.setDefaultProperties(properties);

        // Run spring application
        app.run(args);

    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            container.addErrorPages(error404Page);
        });
    }
}
