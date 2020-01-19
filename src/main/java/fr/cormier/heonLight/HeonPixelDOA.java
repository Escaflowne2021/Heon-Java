package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.heon.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@JsonDeserialize(as = HeonPixelDOA.class)
public class HeonPixelDOA extends Heon {

    private int R;
    private int G;
    private int B;


    protected String ID_SYS;

    public String getID_SYS() {
        return ID_SYS;
    }

    public void setID_SYS(String ID_SYS) {
        this.ID_SYS = ID_SYS;
    }

    @Autowired
    private HeonlDataBaseDOA heonlDataBaseDOA;


    @JsonIgnore
    private ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();


    public HeonPixelDOA() {
        super();
        this.setR(0);
        this.setG(0);
        this.setB(0);
    }

    @Override
    public void ReplaceME(Heon heon) {
        // a complter
    }

    @Override
    public void AddMe(Heon heon, boolean virtual) {

    }

    @Override
    public void AddMe() {
        //a faire
    }

    @Override
    public void AddMe(int nb, boolean virtual) {

    }



    @Override
    public void RemoveMe(Heon h) {
        //Ne sers pas
    }

    @Override
    public void SetAllLight(int r, int g, int b) {
        setRGB(r,g,b);
        RefreshSys();
    }

    public void RefreshSys(){
        System.out.println(ID_SYS);
        applicationContext = ApplicationContextProvider.getApplicationContext();
        heonlDataBaseDOA =  applicationContext.getBean(HeonlDataBaseDOA.class);
       ( (HeonSystemLightDOA)(heonlDataBaseDOA.SearchId(ID_SYS))).RefreshLight();
    }


    public HeonPixelDOA(int r, int g, int b, String ID_SYS) {
        this.setR(r);
        this.setG(g);
        this.setB(b);
        this.ID_SYS = ID_SYS;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {

        R = (r > 255) ? 255 : r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = (g > 255) ? 255 : g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = (b > 255) ? 255 : b;
    }

    public void setRGB(int r, int g, int b) {
        this.setR(r);
        this.setG(g);
        this.setB(b);
    }

    public String toStringLight(){
        return ""+R+","+G+','+B;
    }

    @Override
    public String toString() {
        return "HeonPixelDOA{" +
                "R=" + R +
                ", G=" + G +
                ", B=" + B +
                ", id='" + id + '\'' +
                '}';
    }
}



