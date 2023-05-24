package Model;

import Model.Data.*;

import java.net.Socket;
import java.util.List;

import Model.Logic.ClientCommunicationHandler;


public class GuestModel implements Model {
    public String[] playersScores;
    Socket socket;
    Tile[][] tileBoard = new Tile[15][15];
    Tile[][] tempBoard = new Tile[15][15];
    Player player;
    ClientCommunicationHandler communicationHandler;


    @Override
    public void setPlayerProperties(String name) {
        this.player.setName(name);
    }

    @Override
    public void passTurn(int id) {

    }

    @Override
    public int getCurrentPlayerScore() {
        return player.getScore();
    }

    @Override
    public List<Tile> getCurrentPlayerHand() {
        return player.getHand();
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

