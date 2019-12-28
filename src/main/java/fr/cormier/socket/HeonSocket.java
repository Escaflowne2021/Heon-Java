package fr.cormier.socket;

import javax.annotation.security.RunAs;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class HeonSocket {

    private Socket socket;
    private HashMap<String, Socket> socketHashMap = new HashMap<String, Socket>();
    private Runnable maTache;
    private ScheduledExecutorService executor;

    public HeonSocket() {
        System.out.println("Contructeur HeonSocket ");
    }


    public HeonSocket(String IP, int port, String ID) {

        executor = Executors.newScheduledThreadPool(2);

         maTache = new Runnable() {
            public void run() {
                if (socket == null || !socket.isConnected()){
                    System.out.println("Connexion en Cours ......" + IP + " id:"+ID);
                    try {
                        socket = new Socket();
                        socket.connect(new InetSocketAddress(IP, port),2000);

                    } catch (IOException e) {
                        System.out.println("Erreur connexion ......" + IP+ " id:"+ID);
                    }
                }
            }
        };

        final ScheduledFuture<?> maTacheFuture = executor.scheduleAtFixedRate(
                maTache, 5, 5, SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                executor.shutdown();
            }
        });
    }




    public void CloseSocket(){
        try {
            if (socket != null) { socket.close(); }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
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
