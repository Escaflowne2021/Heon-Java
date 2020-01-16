package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.heon.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

@JsonDeserialize(as = HeonlDataBaseDOA.class)
public class HeonlDataBaseDOA extends Heon{


    //protected Set<Heon> dataVirtualLight = new LinkedHashSet<>();

    @JsonIgnore
    private  ApplicationContext applicationContext;


    public HeonlDataBaseDOA(){
        applicationContext = ApplicationContextProvider.getApplicationContext();
        System.out.println("Contructeur Database");

    }

    public void AddMeVirtualSys(){
        //HeonVirtualLight VLight = applicationContext.getBean(HeonVirtualLight.class);
        //data.add(VLight);
    }

    public void RemoveMeVirtualLight(Heon h){
        data.remove(h);
    }

    @Override
    public void ReplaceME(Heon heon) {
        //Rien a faire
    }

    @Override
    public void AddMe(Heon heon, boolean virtual) {
        HeonSystemLightDOA light = (HeonSystemLightDOA)heon;
        data.add(light);
    }

    @Override
    public void AddMe() {
        HeonSystemLightDOA sys = applicationContext.getBean(HeonSystemLightDOA.class);
        sys.setName("A definir");
        sys.setIP("172.20.10.11");
        sys.setPort(2000);
        data.add(sys);
        sys.startService();
        //Logger.getLogger(HeonlDataBaseDOA.class.getName()).severe("ERREUR AddME non renseigné");
    }

    @Override
    public void AddMe(int nb, boolean virtual) {
        int i =0;
        while ( i < nb){
            i++;
            if (virtual) {
                AddMeVirtualSys();
            } else {
                AddMe();
            }

        }
    }

    @Override
    public void RemoveMe(Heon h) {
        delSystemLight((HeonSystemLightDOA)h);

    }

    @Deprecated
    public void addSystemLight(HeonSystemLightDOA light){
        data.add(light);
    }

    public void delSystemLight(HeonSystemLightDOA light) {
        light.stopService();
        data.remove(light);

    }

    @Deprecated
    public void addLightOnSysHeon(String id){
        HeonSystemLightDOA H = (HeonSystemLightDOA) this.SearchId(id);
        HeonLightDOA light = applicationContext.getBean(HeonLightDOA.class);
        H.AddMe(light,false);
        //System.out.println(H.data.size());
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
