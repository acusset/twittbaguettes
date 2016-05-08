package com.esgi.twittbaguettes;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * springbaguettes
 *
 * @author Antoine on 07/05/2016.
 *         -- Description du fichier --
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
public class SpringBaguettesApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBaguettesApplication.class, args);
    }

}