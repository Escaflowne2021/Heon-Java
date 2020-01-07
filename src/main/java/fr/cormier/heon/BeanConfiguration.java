package fr.cormier.heon;


import fr.cormier.heonLight.BeanConfigurationHeonPixel;
import fr.cormier.heonLight.HeonLightDOA;
import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.context.annotation.*;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.format.TextStyle;

@Configuration
@ComponentScan("fr.cormier.heon")
@ComponentScan("fr.cormier")
@Import(BeanConfigurationHeonPixel.class)
@PropertySource("classpath:application.properties")
public class BeanConfiguration {









}
