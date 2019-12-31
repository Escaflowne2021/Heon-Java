package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(as = HeonLightDOA.class)
public class HeonLightDOA extends Heon {

    @Value("${nb_pixel_by_light}")
    private long PixelByLight;
    private long numero;
    //private Set<Heon> heonPixelDOASet = new HashSet<>();


    public HeonLightDOA(){
        super();
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    @Override
    public void ReplaceME(Heon heon) {
        //a completer
    }

    @Override
    public void AddMe(Heon heon) {
        HeonPixelDOA pixel = (HeonPixelDOA) heon;
        if (data.size() < PixelByLight) data.add(pixel);
    }

    @Override
    public void AddMe() {
        // afaire
    }


    @Deprecated
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
