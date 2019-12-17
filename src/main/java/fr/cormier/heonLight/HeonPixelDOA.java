package fr.cormier.heonLight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = HeonPixelDOA.class)
public class HeonPixelDOA extends Heon {

    private int R;
    private int G;
    private int B;

    public HeonPixelDOA() {
        super();
    }


    public HeonPixelDOA(int r, int g, int b) {
        this.setR(r);
        this.setG(g);
        this.setB(b);
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



