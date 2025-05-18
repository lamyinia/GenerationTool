package org.com.javafactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"org.com.javafactory"})
public class JavaFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaFactoryApplication.class, args);
    }

}
