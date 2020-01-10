package com.mingdi101.annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2
public class AnnotationsTest 
{
    public static void main( String[] args )
    {
        SpringApplication.run(AnnotationsTest.class, args);
    }
}
