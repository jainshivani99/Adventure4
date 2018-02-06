/**
 * A class that defines a layout object with all of its attributes.
 */
public class Layout {
    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    //the name of the room you are starting in
    private String startingRoom;

    //the name of the room you are ending in
    private String endingRoom;

    //an array of rooms that are available for you to go to
    private Room[] rooms;

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
}
