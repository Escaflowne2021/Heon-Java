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

    private HeonSocket heonSocket;

    public HeonSystemLightDOA(){
       super();
    }

    public HeonSystemLightDOA(String name,String IP, int port) {
        super();
        Name = name;
        this.IP = IP;
        this.port = port;
        heonSocket = new HeonSocket(IP,port);

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

    public void RefreshLight(){

        heonSocket.SendData(this.GetJSON()+ System.lineSeparator());


        
    }





}
