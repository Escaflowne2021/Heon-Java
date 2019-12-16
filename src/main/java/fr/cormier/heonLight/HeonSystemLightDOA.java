package fr.cormier.heonLight;

import java.util.HashSet;
import java.util.Set;

public class HeonSystemLightDOA extends Heon{


    private String Name; //Nom du la pièce ou systeme
    private Set<HeonLightDOA> heonLightDOASet = new HashSet<>();

    public HeonSystemLightDOA(){
       super();
    }

    public HeonSystemLightDOA(String name) {
        super();
        Name = name;
    }

    public Set<HeonLightDOA> getHeonLightDOASet() {
        return heonLightDOASet;
    }

    public void addSystemLight(HeonLightDOA light){
        heonLightDOASet.add(light);
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


    @Override
    public void SearchId(String id) {


         Heon h = heonLightDOASet.stream()
                 .filter(heonLightDOA -> heonLightDOA.getId().equals(id))
                 .findFirst()
                 .orElse(null);
         if (h == null) {
             heonLightDOASet.stream().forEach(heonLightDOA -> heonLightDOA.SearchId(id));
         } else {
             System.out.println("TROUVEEEEE:"+h.getId());
         }
                //.forEach(heonLightDOA -> System.out.println("trouve :"+heonLightDOA.getId())).;



       heonLightDOASet.forEach((HeonLightDOA light)-> {
           System.out.println(light.id);
       });
    }


}
