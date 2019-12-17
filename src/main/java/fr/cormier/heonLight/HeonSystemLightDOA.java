package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(as = HeonSystemLightDOA.class)
public class HeonSystemLightDOA extends Heon{


    private String Name; //Nom du la pièce ou systeme
    //private Set<Heon> heonLightDOASet = new HashSet<>();

    public HeonSystemLightDOA(){
       super();
    }

    public HeonSystemLightDOA(String name) {
        super();
        Name = name;
    }



    public void addSystemLight(HeonLightDOA light){
        data.add(light);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }



}
