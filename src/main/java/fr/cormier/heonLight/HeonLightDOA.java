package fr.cormier.heonLight;

import java.util.HashSet;
import java.util.Set;


public class HeonLightDOA extends Heon {


    private long PixelByLight = 9;
    private Set<HeonPixelDOA> heonPixelDOASet = new HashSet<>();


    public HeonLightDOA(){
        super();
    }

    @Override
    public void SearchId(String id) {
        Heon h = heonPixelDOASet.stream()
                .filter(heonPixelDOA -> heonPixelDOA.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (h == null) {
            heonPixelDOASet.stream().forEach(heonPixelDOA -> heonPixelDOA.SearchId(id));
        } else {
            System.out.println("TROUVEEEEE Pixel:"+h.getId());
        }
    }

    public void addPixel(HeonPixelDOA pixel){
        if (heonPixelDOASet.size() < PixelByLight) heonPixelDOASet.add(pixel);
    }

    public void setPixelByLight(long pixelByLight) {
        PixelByLight = pixelByLight;
    }

    public long getPixelByLight() {
        return PixelByLight;
    }

    public Set<HeonPixelDOA> getHeonPixelDOASet() {
        return heonPixelDOASet;
    }


}
