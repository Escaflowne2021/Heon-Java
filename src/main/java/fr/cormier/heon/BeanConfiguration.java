package fr.cormier.heon;


import fr.cormier.heonLight.BeanConfigurationHeonPixel;
import fr.cormier.heonLight.HeonLightDOA;
import org.springframework.context.annotation.*;

import java.time.format.TextStyle;

@Configuration
@ComponentScan("fr.cormier.heon")
@Import(BeanConfigurationHeonPixel.class)
@PropertySource("classpath:application.properties")
public class BeanConfiguration {


    @Bean
    @Scope("prototype")
    public HeonLightDOA heonLightDOA(){return new HeonLightDOA();};

}
