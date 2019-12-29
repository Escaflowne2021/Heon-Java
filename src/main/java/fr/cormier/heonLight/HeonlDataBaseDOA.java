package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@JsonDeserialize(as = HeonlDataBaseDOA.class)
public class HeonlDataBaseDOA extends Heon{

    public HeonlDataBaseDOA(){
        System.out.println("Contructeur Database");
    }

    public void addSystemLight(HeonSystemLightDOA light){
        data.add(light);
    }

    public void delSystemLight(HeonSystemLightDOA light) {
        light.stopService();
        data.remove(light);

    }

    public void ReplaceHeonNode(Heon heon) {
        Heon temp = this.SearchId(heon.getId());
        if (temp instanceof HeonSystemLightDOA) {

            ((HeonSystemLightDOA) temp).setName(((HeonSystemLightDOA)heon).getName());

        }
    }



    @PostConstruct
    public void PostContruct(){
        System.out.println("Post Contruction");
        this.data = new LinkedHashSet<>();
    }

}
