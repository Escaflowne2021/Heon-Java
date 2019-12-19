package fr.cormier.socket;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;


public class HeonSocket {

    private Socket socket;
    private HashMap<String,Socket> socketHashMap = new HashMap<String,Socket>();

    public HeonSocket() {
        System.out.println("Contructeur HeonSocket ");
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
            //socket.close();
            System.out.println("Message sent to the server : " + text);
        } catch (IOException e){
            e.printStackTrace();
        }

    }


}
