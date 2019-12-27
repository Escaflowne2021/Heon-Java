package fr.cormier.socket;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;


public class HeonSocket {

    private Socket socket;
    private HashMap<String, Socket> socketHashMap = new HashMap<String, Socket>();

    public HeonSocket() {
        System.out.println("Contructeur HeonSocket ");
    }


    public HeonSocket(String IP, int port) {
//

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                while (socket == null || !socket.isConnected()) {
                    System.out.println("Connexion en Cours ......");
                    try {
                        socket = new Socket(IP, port);
                        socket.setSoTimeout(5*1000); //timeOut de 5s
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
    }


    public void SendData(String text) {
        OutputStreamWriter osw;
        try {
            if (socket != null && socket.isConnected()) {
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                bos.write(text.getBytes());
                bos.flush();
                //socket.close();
                System.out.println("Message sent to the server : " + text);
            } else {
                System.out.println("Sys Heon non connecté");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
