package com.assessment.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring boot application class.
 * @SpringBootApplication annotation tells this is a spring boot project
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.assessment.bookstore.controller", "com.assessment.bookstore.repository", "com.assessment.bookstore.service"})
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

}
