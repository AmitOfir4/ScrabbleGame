package Model.Logic;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {

    public List<Socket> clients;
    System.Logger logger;
    ExecutorService executorService;
    Map<String, Socket> clientsMap = new HashMap<>();
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;


    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        this.stop = false;
        this.clients = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(3);
    }


    private void runServer() throws Exception {
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(1000);
        logger = System.getLogger("MyServer");
        logger.log(System.Logger.Level.INFO, "Server is alive and waiting for clients");
        while (!stop) {
            try {
                Socket aClient = server.accept(); // blocking call
                String clientID = UUID.randomUUID().toString().substring(0, 6);
                clientsMap.put(clientID, aClient);
                ping(clientID);

                logger.log(System.Logger.Level.INFO, "New client connected");

                try {
                    try {
                        ch.handleClient((aClient.getInputStream()), (aClient.getOutputStream()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SocketTimeoutException e) {
                //wait for another client
            }

        }
        if (clientsMap.isEmpty()) {
            //close all threads
            ch.close();
            server.close();
        } else {
            //close all threads
            logger.log(System.Logger.Level.INFO, "Server is shutting down");
        }
    }

    private void ping(String clientID) {
        updateSpecificPlayer(clientID, "ping:" + clientID);
    }


    public void start() {
        new Thread(() -> {
            try {
                runServer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    public void stop() {
        stop = true;
    }


    public void close() {
        stop();
    }

    public String ip() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.getHostAddress().contains(":")) {
                        continue;
                    }
                    return addr.getHostAddress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPublicIp() {
        String ip = null;
        try {
            URL url = new URL("https://api.ipify.org");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            ip = in.readLine();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    public void updateAll(Object s) {
        //foreach socket in map send s
        clientsMap.forEach((id, socket) -> {
            PrintWriter out;
            try {
                out = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                logger.log(System.Logger.Level.ERROR, "Error in update all: getting output stream");
                throw new RuntimeException(e);
            }
            out.println(s);
            out.flush();
        });
    }

    public void updateSpecificPlayer(String id, Object obj) {

        Socket s = clientsMap.get(id);
        PrintWriter out;
        try {
            if (s != null) {
                out = new PrintWriter(s.getOutputStream());
                out.println(obj);
                out.flush();
            }
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "Error in update specific player: getting output stream");
            throw new RuntimeException(e);
        }

    }

    public boolean isRunning() {
        return !stop;
    }
}

