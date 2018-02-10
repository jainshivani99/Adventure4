import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.ArrayUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;


import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class PlayAdventure {

    private static Layout myGameLayoutJson;

    public static void setUp(){
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader
                    ("C:\\Users\\jains_000\\IdeaProjects\\adventure-jainshivani99\\Shivani4.json"));
            myGameLayoutJson = gson.fromJson(br, Layout.class);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
//        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
//        HttpResponse<String> stringHttpResponse = null;
//        try {
//            stringHttpResponse = Unirest.get(url).asString();
//        }
//
//        catch (UnirestException e) {
//            System.out.println("Could not get content from URL as String.");
//            System.exit(-1);
//        }
//
//        if (stringHttpResponse.getStatus() == 200) {
//            String json = stringHttpResponse.getBody();

    }


    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.println("");

        if (args.length > 1) {
            String filePath = args[1];
            String fileContents = Data.getFileContentsAsString(filePath);
            Gson gson = new Gson();
            myGameLayoutJson = gson.fromJson(fileContents, Layout.class);
        } else {
            PlayAdventure.setUp();
        }

        Room[] myRooms = myGameLayoutJson.getRooms();
        Room currentRoom = null;
        for (Room roomObj : myRooms) {
            if (roomObj.getName().equals(myGameLayoutJson.getStartingRoom())) {
                currentRoom = roomObj;
            }
        }
        PlayAdventure.printCurrentRoom(currentRoom);
        ArrayList<Item> myItems = new ArrayList<Item>();

        while (true) {
            String userInput = myScan.nextLine();
            String userCommand = userInput.toLowerCase();
            if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("exit")) {
                System.exit(0);
            } else if (userInput.equalsIgnoreCase("list")) {
                PlayAdventure.printItemsList(myItems);
            }

            // invalid: goEast valid: go East
            else if (userCommand.startsWith("go ")) {
                String direction = userInput.substring(3);
                currentRoom = PlayAdventure.goDirection(currentRoom, direction);
            }
            //Valid: "take apple"
            //Invalid: "takeApple"
            else if (userCommand.startsWith("take ")) {
                String itemName = userInput.substring(5);
                Item thisItem = new Item();
                thisItem.setName(itemName);
                PlayAdventure.takeItem(currentRoom, thisItem, myItems);
            } else if (userCommand.startsWith("drop ")) {
                String itemName = userInput.substring(5);
                Item thisItem = new Item();
                thisItem.setName(itemName);
                PlayAdventure.dropItem(currentRoom, thisItem, myItems);
            } else {
                System.out.println("I don't understand " + userInput);
                PlayAdventure.printCurrentRoom(currentRoom);
            }
        }
    }

    public static void printCurrentRoom(Room currentRoom) {
        System.out.println(currentRoom.getName());
        System.out.println(currentRoom.getDescription());
        if (currentRoom.getName().equals(myGameLayoutJson.getStartingRoom())) {
            System.out.println("Your journey begins here");
        } else if (currentRoom.getName().equals(myGameLayoutJson.getEndingRoom())) {
            System.out.println("You have reached your final destination");
            System.exit(0);
        }
        Item[] currentRoomItems = currentRoom.getItems();
        System.out.println("This room contains ");
        for (int index = 0; index < currentRoomItems.length; index++) {
            System.out.print(currentRoomItems[index].getName() + ", ");
        }
        System.out.println();

        String[] currentMonstersInRoom = currentRoom.getMonstersInRoom();
        System.out.println("The monsters in the room are " + Arrays.toString(currentMonstersInRoom));

        //if all the monsters in the room have been defeated then
        Direction[] currentRoomDirections = currentRoom.getDirections();
        for (Direction directionObj : currentRoomDirections) {
            System.out.println("From here, you can go: " + directionObj.getDirectionName());
        }
    }

    public static void printItemsList(ArrayList<Item> myItems) {
        System.out.println(Arrays.toString(myItems.toArray()));
    }

    public static Room goDirection(Room currentRoom, String direction) {
        Direction[] currentRoomDirections = currentRoom.getDirections();
        for (Direction directionObj : currentRoomDirections) {
            if (directionObj.getDirectionName().equalsIgnoreCase(direction)) {
                //gets the name of the next room the user can go to as a String
                String nextRoomName = directionObj.getRoom();

                //Objective: update currentRoom
                //currentRoom should be the room object of the nextRoomName you can go to
                Room[] allRooms = myGameLayoutJson.getRooms();
                for (Room roomObj : allRooms) {
                    if (roomObj.getName().equals(nextRoomName)) {
                        currentRoom = roomObj;
                        break;
                    }
                }
                PlayAdventure.printCurrentRoom(currentRoom);
                return currentRoom;
            }
        }
        System.out.println("I can't go " + direction);
        PlayAdventure.printCurrentRoom(currentRoom);
        return currentRoom;
    }

    public static void takeItem(Room currentRoom, Item thisItem, ArrayList<Item> myItems) {
        Item[] currentRoomItems = currentRoom.getItems();
        for (Item itemObj : currentRoomItems) {
            if (itemObj.getName().equalsIgnoreCase(thisItem.getName())) {
                myItems.add(itemObj);
                currentRoomItems = ArrayUtils.removeElement(currentRoomItems, itemObj);
                currentRoom.setItems(currentRoomItems);
                PlayAdventure.printCurrentRoom(currentRoom);
                return;
            }
        }
        System.out.println("I can't take " + thisItem);
        PlayAdventure.printCurrentRoom(currentRoom);
    }

    public static void dropItem(Room currentRoom, Item thisItem, ArrayList<Item> myItems) {
        Item[] currentRoomItems = currentRoom.getItems();
        for (Item itemObj : myItems) {
            if (itemObj.getName().equalsIgnoreCase(thisItem.getName())) {
                myItems.remove(itemObj);
                currentRoomItems = ArrayUtils.add(currentRoomItems, itemObj);
                currentRoom.setItems(currentRoomItems);
                PlayAdventure.printCurrentRoom(currentRoom);
                return;
            }
        }
        System.out.println("I can't drop " + thisItem);
        PlayAdventure.printCurrentRoom(currentRoom);
    }
}
