


package fr.cormier.heon;

import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.Properties;

public class ServletInitializer extends SpringBootServletInitializer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        String configLocation = System.getProperty("global.appconf.dir");
        String configPath = configLocation + File.separator + "springApplication"  + File.separator + "application.yml"; //set the configpath of this application instance exclusively
        System.out.println("Configpath: " + configPath);
        System.out.println("Starting to run Spring boot app...");
        if(configLocation != null && !configLocation.isEmpty()) {
            Properties props = new Properties();
            props.setProperty("spring.config.location", configPath); //set the config file to use
            application.application().setDefaultProperties(props);
        }else{
            System.out.println("No global.appconf.dir property found, starting with default on classpath");
        }

        return application.sources(HeonApplication.class);
    }



}



