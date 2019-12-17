package fr.cormier.heonLight;

import fr.cormier.socket.BeanConfigurationSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("fr.cormier.heonLight")
@Import(BeanConfigurationSocket.class)
public class BeanConfigurationHeonPixel {

    @Bean
    public HeonlDataBaseDOA heonPixelDataBase() { return new HeonlDataBaseDOA();}


}
