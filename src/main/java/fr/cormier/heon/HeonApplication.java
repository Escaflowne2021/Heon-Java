package fr.cormier.heon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.cormier.heonLight.Heon;
import fr.cormier.heonLight.HeonLightDOA;
import fr.cormier.heonLight.HeonPixelDOA;
import fr.cormier.heonLight.HeonSystemLightDOA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

@SpringBootApplication
public class HeonApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(HeonApplication.class, args);
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        HeonSystemLightDOA sys = new HeonSystemLightDOA("maison","192.168.0.44",2000);
        HeonLightDOA light = new HeonLightDOA();
        light.addPixel(new HeonPixelDOA(100,300,400));
        light.addPixel(new HeonPixelDOA(40,50,70));
        light.addPixel(new HeonPixelDOA(100,3000,0));
        light.addPixel(new HeonPixelDOA(40,50,70));
        light.addPixel(new HeonPixelDOA(100,3000,0));
        light.addPixel(new HeonPixelDOA(40,50,70));
        light.addPixel(new HeonPixelDOA(100,3000,0));
        light.addPixel(new HeonPixelDOA(40,50,70));

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

        String json = sys.GetJSON();
        sys.RefreshLight();
        sys.RefreshLight();
        sys.RefreshLight();
        sys.RefreshLight();


//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode sysnode = objectMapper.readTree(json);
//        HeonSystemLightDOA sys2 = objectMapper.treeToValue(sysnode,HeonSystemLightDOA.class);
//        System.out.println("Is Sys2: " + sys2.getId());
//        System.out.println(sys2.SearchId("Pixel2").toString());








    }

}
