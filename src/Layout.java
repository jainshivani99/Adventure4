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
        }
    }
}
