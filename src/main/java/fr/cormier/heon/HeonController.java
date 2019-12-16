package fr.cormier.heon;

import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeonController {

    @Autowired
    private HeonlDataBaseDOA heonPixelDataBase;

    @RequestMapping(method = RequestMethod.GET, path = "/heon")
    public void heon(){
        System.out.println("Get a GET heon");

    }

}
