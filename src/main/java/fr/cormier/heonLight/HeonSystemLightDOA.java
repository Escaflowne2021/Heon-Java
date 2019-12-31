package fr.cormier.heonLight;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.cormier.heon.ApplicationContextProvider;
import fr.cormier.socket.HeonSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@JsonDeserialize(as = HeonSystemLightDOA.class)
@Component
public class HeonSystemLightDOA extends Heon {


    @JsonIgnore
    ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();;

    private String Name; //Nom du la pièce ou systeme
    private String IP;
    private int port;
    private int nombreLumière = 0;

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
        //applicationContext = ApplicationContextProvider.getApplicationContext();
    }


    public HeonSystemLightDOA(String name,String IP, int port) {
        super();
        Name = name;
        this.IP = IP;
        this.port = port;
        this.heonSocket = new HeonSocket(IP,port,this.id);

    }
    public void stopService(){
        this.heonSocket.CloseSocket();
    }


    @Deprecated
    public void addSystemLight(HeonLightDOA light){
        light.setNumero(++this.nombreLumière);
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

    @Override
    public void ReplaceME(Heon heon) {
        HeonSystemLightDOA H = (HeonSystemLightDOA)heon;
        this.IP = H.IP;
        this.Name = H.Name;
        this.port = H.port;
        this.data = H.data;
        this.stopService();
        this.heonSocket = new HeonSocket(IP,port,this.id);


    }

    @Override
    public void AddMe(Heon heon) {
        HeonLightDOA light = (HeonLightDOA)heon;
        light.setNumero(++this.nombreLumière);
        data.add(light);
    }

    @Override
    public void AddMe() {
        System.out.println("Ajout AddMe "+this.getClass().getName());
        HeonLightDOA light = applicationContext.getBean(HeonLightDOA.class);
        light.setNumero(++this.nombreLumière);
        data.add(light);
        //a faire
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
