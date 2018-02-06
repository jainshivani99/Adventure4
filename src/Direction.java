/**
 * A class that defines a direction object with all of its attributes.
 */
public class Direction {

    //the direction name of the next room
    private String directionName;

    //the name of the room you can go to next
    private String room;

    //Empty Constructor
    public Direction() {

    }

    //Getter methods for each attribute of the direction object

    /**
     * Gets the direction name of the next room you can go to
     * @return directionName - the direction name of the next room
     */
    public String getDirectionName() {
        return directionName;
    }

    /**
     * Gets the name of the room you can go to next based off of the user input
     * @return room - the name of the room you can go to next
     */
    public String getRoom() {
        return room;
    }

    //Setter methods for each attribute of the direction object

    /**
     * Sets the direction name of the room you can go to next based off of the user input
     * @param directionName - the direction name you can go to next
     */
    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    /**
     * Sets the name of the room you can go to next based off of the user input
     * @param room - the name of the room you can go to next
     */
    public void setRoom(String room) {
        this.room = room;
    }
}
