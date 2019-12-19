package fr.cormier.heonLight;

import fr.cormier.socket.BeanConfigurationSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("fr.cormier.heonLight")

public class BeanConfigurationHeonPixel {

    @Bean
    public HeonlDataBaseDOA heonlDataBaseDOA() { return new HeonlDataBaseDOA();}


}
