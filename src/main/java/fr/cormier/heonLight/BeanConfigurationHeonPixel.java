package fr.cormier.heonLight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("fr.cormier.heonLight")
public class BeanConfigurationHeonPixel {

    @Bean
    public HeonlDataBaseDOA heonPixelDataBase() { return new HeonlDataBaseDOA();}


}
