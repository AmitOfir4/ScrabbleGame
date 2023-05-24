package Model.GameData;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
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

    /**
     * The addTilesTo7 function adds tiles to the hand until it has 7 tiles.
     */
    public String completeTilesTo7() {
        while (this.getHand().size() < 7) {
            if (Tile.Bag.getBag().size() == 0)
                break;
            this.getHand().add(Tile.Bag.getBag().getRand());
        }
        return this.socketID;
    }

    /**
     * The create function is used to create a new instance of the Player class.
     *
     * @return A new player object
     */

    public Player create() {
        return new Player();
    }

    /**
     * The isRackEmpty function is used to check if the player's rack is empty and the bag is empty
     * as well.
     *
     * @return True if the player's rack is empty, false otherwise
     */
    public boolean isRackEmpty() {
        return Tile.Bag.getBag().size() == 0 && this.getHand().isEmpty();

    }

    /**
     * The charToTile function takes in a character and returns the tile with that letter from the hand of the player.
     *
     * @param c Find the tile with the corresponding letter
     * @return The tile with the given letter
     */
    public Tile charToTile(char c) {
        for (Tile t : this.getHand()) {
            if (t.letter == c) {
                return t;
            }
        }
        return null;
    }

    /**
     * The getTileLottery function is used to get a random tile from the bag.
     *
     * @return A tile from the bag
     */
    public String getTileLottery() {
        return tileLottery;
    }

    public void setTileLottery() {
        Random rand = new Random();
        int value = rand.nextInt(26) + 'A';
        char c = (char) value;
        tileLottery = String.valueOf(c);
    }

    /**
     * The get_index function returns the index of the current node.
     *
     * @return The _index variable
     */
    public int get_index() {
        return index;
    }

    /**
     * The set_index function sets the index of a player.
     *
     * @param _index Set the index of the player
     * @return Null
     */
    public void set_index(int _index) {
        this.index = _index;
    }

    /**
     * The get_socketID function returns the _socketID of the user.
     *
     * @return The id of the student
     */
    public String get_socketID() {
        return this.socketID;
    }

    /**
     * The set_socketID function sets the value of the _socketID variable.
     * <p>
     * Set the _socketID of a particular row in the database
     */
    public void set_socketID(String socketID) {
        this.socketID = socketID;

    }

    /**
     * The getHand function returns the hand of a player.
     * @return The list of tiles in the hand
     */
    public List<Tile> getHand() {
        return hand;
    }

    public void set_hand(List<Tile> _hand) {
        this.hand = _hand;
    }

    /**
     * The get_score function returns the score of the player.
     * <p>
     *
     * @return The score of the player
     */
    public int get_score() {
        return score;
    }

    /**
     * The set_score function sets the score of a player.
     * <p>
     *
     * @param _score Set the score of the player
     */
    public void setScore(int _score) {
        if (_score >= 0)
            this.score = _score;
        else {
            this.score = 0;
        }
    }

    /**
     * The get_name function returns the name of the person.
     * <p>
     *
     * @return The name of the player
     */
    public String get_name() {
        return name;
    }

    /**
     * The set_name function sets the name of the person.
     *
     * @param name Set the name of the contact
     *             Nothing
     */
    public void set_name(String name) {
        this.name = name;
    }

    /**
     * The updateHand function is used to update the hand of a player
     * when receiving a new hand from the server.
     * <p>
     *
     * @param inObject Update the hand of a player
     */
    public void updateHand(List<Tile> inObject) {
        this.getHand().clear();
        this.getHand().addAll(inObject);
    }
}
