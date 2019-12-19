package fr.cormier.heon;

import fr.cormier.heonLight.HeonLightDOA;
import fr.cormier.heonLight.HeonPixelDOA;
import fr.cormier.heonLight.HeonSystemLightDOA;
import fr.cormier.heonLight.HeonlDataBaseDOA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class Test {


    HeonlDataBaseDOA heonlDataBaseDOA;

    public Test(){
        System.out.println("Contrsuteur Test");
    }

    @Autowired
    public Test( HeonlDataBaseDOA heonlDataBaseDOA){
        System.out.println("Contrsuteur Test avec data");
        this.heonlDataBaseDOA = heonlDataBaseDOA;

    }

    public void add(HeonSystemLightDOA sys){
        heonlDataBaseDOA.addSystemLight(sys);
    }


}
