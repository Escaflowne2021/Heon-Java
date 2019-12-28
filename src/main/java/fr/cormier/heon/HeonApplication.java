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


        HeonSystemLightDOA sys = new HeonSystemLightDOA("maison","172.20.10.11",2000);
        HeonLightDOA light = applicationContext.getBean(HeonLightDOA.class);

        int i =0;
        while (i < 10) {
            light.addPixel(new HeonPixelDOA(0, 0, 255));
            i++;
        }

        HeonLightDOA light2 = applicationContext.getBean(HeonLightDOA.class);
    i=0;

        while (i < 10) {
            light2.addPixel(new HeonPixelDOA(255, 0, 0));
            i++;
        }
        sys.addSystemLight(light);
        sys.addSystemLight(light2);
        sys.RefreshLight();

        base.addSystemLight(sys);
        //base.addSystemLight(sys2);



//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode sysnode = objectMapper.readTree(json);
//        HeonSystemLightDOA sys2 = objectMapper.treeToValue(sysnode,HeonSystemLightDOA.class);
//        System.out.println("Is Sys2: " + sys2.getId());
//        System.out.println(sys2.SearchId("Pixel2").toString());








    }

}
