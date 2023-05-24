package Model.Logic;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;


public class HostCommunicationHandler implements ClientHandler {
    Map<String, Consumer<String[]>> handlers = new HashMap<>();
    PrintWriter out;
    Scanner in;
    PrintWriter toGameServer;
    Scanner fromGameServer;
    BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
    public ExecutorService executor = Executors.newFixedThreadPool(3);
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

    }

    @Override
    public void close() {

    }
}
