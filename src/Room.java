/**
 * A class that defines a room object with all of its attributes. In this implementation, each room from the JSON file
 * is an object stored as a string.
 */
public class Room {

    //the name of the room you are currently in
    private String name;

    //the description of the room you are currently in
    private String description;

    //an array of items in the room you are currently in
    private Item[] items;

    //an array of directions that you can go from the room you are currently in
    private Direction[] directions;

    //names of monster to look up in the monster array
    private String[] monstersInRoom;

    //Empty Constructor
    public Room() {

    }

    //Getter methods for each attribute of the room object

    /**
     * Gets the name of the room you are currently in
     * @return name - the name of the room you are currently in
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the room you are currently in
     * @return description - the description of the room you are currently in
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the items array of the room you are currently in
     * @return items - an array of items in the room you are currently in
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Gets the directions array of the room you are currently in
     * @return directions - an array of directions that you can go from the room you are currently in
     */
    public Direction[] getDirections() {
        return directions;
    }

    public String[] getMonstersInRoom() {
        return monstersInRoom;
    }
    //Setter methods for each attribute of the room object

    /**
     * Sets the name of the room to the given parameter
     * @param name - the name of the room to set it to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the room to the given parameter
     * @param description - the description of the room to set it to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the array of items in the room to the given parameter
     * @param items - an array of items to set it to
     */
    public void setItems(Item[] items) {
        this.items = items;
    }

    /**
     * Sets the array of Direction objects for the room to the given parameter
     * @param directions - an array of Direction objects to set it to
     */
    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }

    public void setMonstersInRoom(String[] monstersInRoom) {
        this.monstersInRoom = monstersInRoom;
    }


}
