package ru.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootConfiguration
@PropertySource(value={"classpath:application.properties"})
public class AppProperties {
    private String BASE_URL = "http://localhost:8080";
}
