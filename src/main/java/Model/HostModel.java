package Model;

import Model.GameData.*;
import Model.GameLogic.MyServer;

import java.net.Socket;
import java.util.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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
}
