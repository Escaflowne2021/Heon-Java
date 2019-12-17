package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(as = HeonLightDOA.class)
public class HeonLightDOA extends Heon {


    private long PixelByLight = 9;
    //private Set<Heon> heonPixelDOASet = new HashSet<>();


    public HeonLightDOA(){
        super();
    }



    public void addPixel(HeonPixelDOA pixel){
        if (data.size() < PixelByLight) data.add(pixel);
    }

    public void setPixelByLight(long pixelByLight) {
        PixelByLight = pixelByLight;
    }

    public long getPixelByLight() {
        return PixelByLight;
    }

    @Override
    public String toString() {
        return "HeonLightDOA{" +
                "PixelByLight=" + PixelByLight +
                ", id='" + id + '\'' +
                ", Light =" + data.size() +
                '}';
    }
}
