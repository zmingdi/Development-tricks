package com.med.mingdi101.hdf.consult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableSwagger2
public class Hdf 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Hdf.class, args);
    }
}
