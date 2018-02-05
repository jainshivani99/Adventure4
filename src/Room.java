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
    private String[] items;

    //an array of directions that you can go from the room you are currently in
    private Direction[] directions;

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
    public String[] getItems() {
        return items;
    }

    /**
     * Gets the directions array of the room you are currently in
     * @return directions - an array of directions that you can go from the room you are currently in
     */
    public Direction[] getDirections() {
        return directions;
    }

}
