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

    @Override
    public void ReplaceME(Heon heon) {
        //Rien a faire
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
        temp.ReplaceME(heon);
//
    }



    @PostConstruct
    public void PostContruct(){
        System.out.println("Post Contruction");
        this.data = new LinkedHashSet<>();
    }

}
