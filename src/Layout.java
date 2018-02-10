/**
 * A class that defines a layout object with all of its attributes.
 */
public class Layout {

    //the name of the room you are starting in
    private String startingRoom;

    //the name of the room you are ending in
    private String endingRoom;

    //an array of rooms that are available for you to go to
    private Room[] rooms;

    //the name of the player in the game
    private Player player;

    //an array of monsters that are playing the game
    private Monster[] monsters;

    //Empty Constructor
    public Layout() {

    }

    //Getter methods for each attribute of the layout object

    /**
     * Gets the name of the room you are starting in
     * @return startingRoom - the name of the room you are currently in
     */
    public String getStartingRoom() {
        return startingRoom;
    }

    /**
     * Gets the name of the room you are ending in
     * @return endingRoom - the name of the room you are ending in
     */
    public String getEndingRoom() {
        return endingRoom;
    }

    /**
     * Gets an array of rooms you can go to
     * @return rooms - an array of rooms you can go to
     */
    public Room[] getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster[] getMonsters() {
        return monsters;
    }
    //Setter methods for each attribute of the direction object

    /**
     * Sets the room that you start in
     * @param startingRoom - the name of the room you start in
     */
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    /**
     * Sets the room that you end in
     * @param endingRoom - the name of the room you end in
     */
    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    /**
     * Sets the array of rooms in the game
     * @param rooms - the array of rooms in the game
     */
    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }


}
