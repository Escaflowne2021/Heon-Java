

package fr.cormier.heon;

import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

public class ServletInitializer extends SpringBootServletInitializer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("DEMARRAGE CONF servlter");

        return application.sources(HeonApplication.class);
    }

}


