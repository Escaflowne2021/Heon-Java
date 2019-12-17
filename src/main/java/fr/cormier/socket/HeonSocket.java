package fr.cormier.socket;

import java.io.*;
import java.net.Socket;


public class HeonSocket {

    private Socket socket;


    public HeonSocket() {
        System.out.println("Contructeur HeonSocket "+this.hashCode());


    }

    public HeonSocket(String IP, int port){
        try {
            socket = new Socket(IP,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void SendData(String text) {
        OutputStreamWriter osw;
        try {

            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(text.getBytes());
            bos.flush();
            System.out.println("Message sent to the server : " + text);
        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
