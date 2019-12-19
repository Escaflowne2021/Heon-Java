package fr.cormier.heonLight;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.socket.HeonSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(as = HeonSystemLightDOA.class)
@Component
public class HeonSystemLightDOA extends Heon{


    private String Name; //Nom du la pièce ou systeme
    private String IP;
    private int port;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    @Autowired
    private HeonSocket heonSocket;

    public HeonSystemLightDOA(){
       super();
    }


    public HeonSystemLightDOA(String name,String IP, int port) {
        super();
        Name = name;
        this.IP = IP;
        this.port = port;

        this.heonSocket = new HeonSocket(IP,port);

    }

    public void addSystemLight(HeonLightDOA light){
        data.add(light);
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
    //protected Set<Heon> data = new LinkedHashSet<>();
    public Set<Heon> getData(){
        return this.data;
    }

    public void setData(Set<Heon> data){
        this.data = data;
    }

    //permet de preparer le Json à l'aduino le plus simplement possible. Le but est d'avoir que les information, des LED.
    public void RefreshLight(){
        //heonSocket = new HeonSocket(IP,port);
        //heonSocket.SendData(this.GetJSON()+ System.lineSeparator());

        StringBuilder arduinoData = new StringBuilder();
        int count = 0;
        for (Heon h : data){
           count++;
           HeonLightDOA light = (HeonLightDOA)h;
           long nombrePixelManquant = light.getPixelByLight() - light.data.size();
           //System.out.println("Nombre de pixel maanquant" + nombrePixelManquant);
           for (Heon hp : light.data){
                HeonPixelDOA pixel = (HeonPixelDOA) hp;
                arduinoData.append(pixel.toStringLight()).append("#");
                //System.out.println(pixel.toStringLight());
                //Bourrage d'information de pixel à 0

            }
            for (int i = 0 ; i < nombrePixelManquant ; i++){
                //System.out.println("Bourrage");
                arduinoData.append("" + 0 + "," + 0 + ',' + 0+"#");
            }

        }

    System.out.println(arduinoData.toString());
        heonSocket.SendData(arduinoData.toString()+System.lineSeparator());



    }





}
