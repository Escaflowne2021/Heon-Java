package fr.cormier.socket;

import javax.annotation.security.RunAs;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Objects;
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
    private boolean erreur_on_socket = false;

    private String IP = "127.0.0.1";
    private int port = 2000;
    private InetAddress inetAddress;

    public HeonSocket() {
        System.out.println("Contructeur HeonSocket ");
    }


    public HeonSocket(String IP, int port, String ID) {
        this.IP = IP;
        this.port = port;
        try {
            inetAddress = InetAddress.getByName(IP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        executor = Executors.newScheduledThreadPool(2);

         maTache = new Runnable() {
            public void run() {
                try {
                    if (socket == null || socket.isClosed()|| (erreur_on_socket && inetAddress.isReachable(500))){
                        System.out.println("Connexion en Cours ......" + IP + " id:"+ID);
                        try {
                            socket = new Socket();
                            socket.connect(new InetSocketAddress(IP, port),2000);
                            erreur_on_socket = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                            erreur_on_socket = true;
                            System.out.println("Erreur connexion ......" + IP+ " id:"+ID);
                        }
                    } else {
                        SendData("NONE");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        final ScheduledFuture<?> maTacheFuture = executor.scheduleAtFixedRate(
                maTache, 1, 5, SECONDS);

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
                erreur_on_socket = false;
                //socket.close();
                //System.out.println("Message sent to the server : " + text);
            } else {
                erreur_on_socket = true;
                System.out.println("Send Data - Sys Heon non connecté");
            }
        } catch (IOException e) {
            try {
                e.printStackTrace();
                System.out.println("Send Data - erreur execption");
                socket.close();
                erreur_on_socket = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeonSocket that = (HeonSocket) o;
        return port == that.port &&
                Objects.equals(IP, that.IP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IP, port);
    }

    public boolean isErreur_on_socket() {
        return erreur_on_socket;
    }
}
