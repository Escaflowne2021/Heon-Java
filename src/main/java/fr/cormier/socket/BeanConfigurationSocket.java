package fr.cormier.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("fr.cormier.socket")
public class BeanConfigurationSocket {

    @Bean
    @Scope("prototype")
    public HeonSocket heonSocket(){ return new HeonSocket();}

}
