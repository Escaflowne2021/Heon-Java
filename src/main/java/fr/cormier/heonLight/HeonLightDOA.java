package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.heon.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@JsonDeserialize(as = HeonLightDOA.class)
public class HeonLightDOA extends Heon {

    @Value("${nb_pixel_by_light}")
    private long PixelByLight;
    private long numero;

    @Autowired
    private HeonlDataBaseDOA heonPixelDataBase;


    @JsonIgnore
    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();


    private String ID_SYS;
    //private Set<Heon> heonPixelDOASet = new HashSet<>();


    public HeonLightDOA(){
        super();
    }

    public HeonLightDOA(String ID_SYS){
        super();
        this.ID_SYS = ID_SYS;
    }


    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }


    public String getSys() {
        return ID_SYS;
    }


    public void setSys(String ID_SYS) {
        this.ID_SYS = ID_SYS;
    }

    @Override
    public void ReplaceME(Heon heon) {

        HeonLightDOA H = (HeonLightDOA)heon;
        System.out.println("Modif Light Sys "+H.ID_SYS);
        this.data = H.data;
        this.heonPixelDataBase =  applicationContext.getBean(HeonlDataBaseDOA.class);
        HeonSystemLightDOA sys = ((HeonSystemLightDOA)heonPixelDataBase.SearchId(H.ID_SYS));
        sys.RefreshLight();


    }


    @Override
    public void AddMe(Heon heon) {
        HeonPixelDOA pixel = (HeonPixelDOA) heon;
        if (data.size() < PixelByLight) data.add(pixel);
    }

    @Override
    public void AddMe() {
        System.out.println("Add Pixel is: "+this.id);
        this.AddMe( (new HeonPixelDOA(0, 0, 0)));

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
