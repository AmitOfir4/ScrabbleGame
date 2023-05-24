package Model;

import Model.Data.*;
import Model.Logic.*;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



public class HostModel implements Model {
    private final AtomicBoolean challengeActivated = new AtomicBoolean(false);
    public ArrayList<Word> currentPlayerWords;
    public int currentPlayerIndex = 0;
    public boolean gameIsOver = false;
    public System.Logger hostLogger = System.getLogger("HostLogger");
    HostCommunicationHandler communicationHandler = new HostCommunicationHandler();
    MyServer communicationServer;
    Socket gameSocket;
    Board board;
    Tile.Bag bag;
    Player player;
    Map<String, String> playerToSocketID = new HashMap<>();
    ExecutorService executor = Executors.newSingleThreadExecutor();
    String challengeInfo;
    private List<Player> players;


    private HostModel() {
        //Game data initialization
        board = Board.getBoard();
        bag = Tile.Bag.getBag();
        players = new ArrayList<>();
        player = new Player();
        //for testing
        System.out.println("Pick host port number : ");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        communicationServer = new MyServer(port, communicationHandler);
        System.out.println("Server local ip: " + communicationServer.ip() + "\n" + "Server public ip: " + communicationServer.getPublicIp() + "\n" + "Server port: " + port);
    }
    public static HostModel getModel() {
        return HostModelHelper.modelInstance;
    }
    public Player getPlayer() {
        return player;
    }
    private String getChallengeInfo() {
        return challengeInfo;
    }

    private void setChallengeInfo(String challengeInfo) {
        this.challengeInfo = challengeInfo;
    }

    public MyServer getCommunicationServer() {
        return communicationServer;
    }


    public Map<String, String> getPlayerToSocketID() {
        return playerToSocketID;
    }

    public Socket getGameSocket() {
        return gameSocket;
    }

    public void openSocket(String ip, int port) {
        if (validateIpPort(ip, port)) {
            try {
                gameSocket = new Socket(ip, port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Invalid ip or port");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setNextPlayerIndex(int index) {
        currentPlayerIndex = (index + 1) % players.size();
    }







    private boolean validateIpPort(String ip, int port) {
        // Regular expression for IPv4 address
        String ipv4Regex = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
        // Regular expression for port number (1-65535)
        String portRegex = "^([1-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$";
        // Validate IP address
        if (!Pattern.matches(ipv4Regex, ip)) {
            return false;
        }
        // Validate port number
        return Pattern.matches(portRegex, Integer.toString(port));
    }





    @Override
    public void setPlayerProperties(String name) {

    }

    @Override
    public void passTurn(int id) {

    }

    @Override
    public int getCurrentPlayerScore() {
        return 0;
    }

    @Override
    public List<Tile> getCurrentPlayerHand() {
        return null;
    }

    @Override
    public Tile[][] getBoardState() {
        return new Tile[0][];
    }

    @Override
    public boolean isHost() {
        return false;
    }
    private static class HostModelHelper {
        public static final HostModel modelInstance = new HostModel();
    }
}