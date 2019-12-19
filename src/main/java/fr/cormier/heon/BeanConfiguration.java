package fr.cormier.heon;


import fr.cormier.heonLight.BeanConfigurationHeonPixel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.format.TextStyle;

@Configuration
@ComponentScan("fr.cormier.heon")
@Import(BeanConfigurationHeonPixel.class)
public class BeanConfiguration {

    @Bean
    public Test test(){return new Test();}

}
