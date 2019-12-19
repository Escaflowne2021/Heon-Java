package fr.cormier.heon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cormier.heonLight.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

@SpringBootApplication

public class HeonApplication {



    public static void main(String[] args) throws IOException {

        SpringApplication.run(HeonApplication.class, args);
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        HeonlDataBaseDOA base = applicationContext.getBean(HeonlDataBaseDOA.class);


        HeonSystemLightDOA sys = new HeonSystemLightDOA("maison","192.168.0.44",2000);
        HeonLightDOA light = new HeonLightDOA();
        light.addPixel(new HeonPixelDOA(0,0,255));
        light.addPixel(new HeonPixelDOA(0,0,255));
        light.addPixel(new HeonPixelDOA(0,0,255));
        light.addPixel(new HeonPixelDOA(255,255,255));

        sys.addSystemLight(light);
        HeonLightDOA light2 = new HeonLightDOA();
        light2.addPixel(new HeonPixelDOA(100,300,400));
        HeonPixelDOA p = new HeonPixelDOA(40,50,70);
        p.setId("Pixel2");
        light2.addPixel(p);
        light2.addPixel(new HeonPixelDOA(40,50,70));
        light2.addPixel(new HeonPixelDOA(100,3000,0));
        light2.setId("essai");
        sys.addSystemLight(light2);


        HeonSystemLightDOA sys2 = new HeonSystemLightDOA("chambre","192.168.0.45",3000);
        HeonLightDOA light3 = new HeonLightDOA();
        light3.addPixel(new HeonPixelDOA(0,0,255));
        light3.addPixel(new HeonPixelDOA(0,0,255));
        light3.addPixel(new HeonPixelDOA(0,0,255));
        light3.addPixel(new HeonPixelDOA(255,255,255));

        HeonLightDOA light5 = new HeonLightDOA();
        light5.addPixel(new HeonPixelDOA(0,0,255));
        sys2.addSystemLight(light5);
        sys2.addSystemLight(light3);

        HeonLightDOA light4 = new HeonLightDOA();
        light4.addPixel(new HeonPixelDOA(100,300,400));
        HeonPixelDOA p2 = new HeonPixelDOA(40,50,70);
        p2.setId("Pixel2");
        light4.addPixel(p);
        light4.addPixel(new HeonPixelDOA(40,50,70));
        light4.addPixel(new HeonPixelDOA(100,3000,0));
        light4.setId("essai");

        sys2.addSystemLight(light4);
        base.addSystemLight(sys);
        base.addSystemLight(sys2);



//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode sysnode = objectMapper.readTree(json);
//        HeonSystemLightDOA sys2 = objectMapper.treeToValue(sysnode,HeonSystemLightDOA.class);
//        System.out.println("Is Sys2: " + sys2.getId());
//        System.out.println(sys2.SearchId("Pixel2").toString());








    }

}
