package Model.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable {
    private int index;
    private String socketID; //socketID of the player
    private String name;
    private int score;
    private List<Tile> hand;
    private String tileLottery;


    public Player() {
        this.name = "Default";
        this.score = 0;
        this.socketID = "";
        this.hand = new ArrayList<>();
    }


    public String completeTilesTo7() {
        while (this.getHand().size() < 7) {
            if (Tile.Bag.getBag().size() == 0)
                break;
            this.getHand().add(Tile.Bag.getBag().getRand());
        }
        return this.socketID;
    }



    public Player create() {
        return new Player();
    }


    public boolean isRackEmpty() {
        return Tile.Bag.getBag().size() == 0 && this.getHand().isEmpty();

    }


    public Tile charToTile(char c) {
        for (Tile t : this.getHand()) {
            if (t.letter == c) {
                return t;
            }
        }
        return null;
    }


    public String getTileLottery() {
        return tileLottery;
    }

    public void setTileLottery() {
        Random rand = new Random();
        int value = rand.nextInt(26) + 'A';
        char c = (char) value;
        tileLottery = String.valueOf(c);
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int _index) {
        this.index = _index;
    }


    public String getSocketID() {
        return this.socketID;
    }


    public void setSocketID(String socketID) {
        this.socketID = socketID;

    }


    public List<Tile> getHand() {
        return hand;
    }

    public void set_hand(List<Tile> _hand) {
        this.hand = _hand;
    }


    public int get_score() {
        return score;
    }


    public void setScore(int _score) {
        if (_score >= 0)
            this.score = _score;
        else {
            this.score = 0;
        }
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void updateHand(List<Tile> inObject) {
        this.getHand().clear();
        this.getHand().addAll(inObject);
    }
}