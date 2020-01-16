package fr.cormier.heonLight;

import fr.cormier.socket.BeanConfigurationSocket;
import fr.cormier.socket.HeonSocket;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("fr.cormier.heonLight")
@Import(BeanConfigurationSocket.class)
public class BeanConfigurationHeonPixel {

    @Bean
    public HeonlDataBaseDOA heonlDataBaseDOA() { return new HeonlDataBaseDOA();}

    @Bean
    @Scope("prototype")
    public HeonSystemLightDOA heonSystemLightDOA() {return new HeonSystemLightDOA();}

    @Bean
    @Scope("prototype")
    public HeonLightDOA heonLightDOA() {return new HeonLightDOA();}

    @Bean
    @Scope("prototype")
    public HeonVirtualLight heonVirtualLight() {return new HeonVirtualLight();}


    }
