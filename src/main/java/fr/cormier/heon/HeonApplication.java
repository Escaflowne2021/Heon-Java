package fr.cormier.heon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cormier.heonLight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

@SpringBootApplication
public class HeonApplication extends SpringBootServletInitializer {

    @Autowired
    private ServletContext servletContext;

    public static void main(String[] args) throws IOException {
        System.out.println("DEMARRAGE MAIN");
        SpringApplication.run(HeonApplication.class, args);

    }

}
