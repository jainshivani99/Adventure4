import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.deploy.util.ArrayUtil;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class PlayAdventure {

    private static Layout myGameLayoutJson;

    public static void setUp(){
        Gson gson = new Gson();
        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        HttpResponse<String> stringHttpResponse = null;
        try {
            stringHttpResponse = Unirest.get(url).asString();
        }

        catch (UnirestException e) {
            System.out.println("Could not get content from URL as String.");
            System.exit(-1);
        }

        if (stringHttpResponse.getStatus() == 200) {
            String json = stringHttpResponse.getBody();
            myGameLayoutJson = gson.fromJson(json, Layout.class);
        }

    }

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.println("");

        PlayAdventure.setUp();

        Room[] myRooms = myGameLayoutJson.getRooms();
        Room currentRoom = null;
        for (Room roomObj : myRooms) {
            if (roomObj.getName().equals(myGameLayoutJson.getStartingRoom())) {
                currentRoom = roomObj;
            }
        }
        PlayAdventure.printCurrentRoom(currentRoom);
        ArrayList<String> myItems = new ArrayList<String>();

        String userInput = myScan.nextLine();
        String userCommand = userInput.toLowerCase();
        if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("exit")) {
            return;
        }

        else if (userInput.equalsIgnoreCase("list")) {
            PlayAdventure.printItemsList(myItems);
        }

        else if (userCommand.startsWith("go")) {
            PlayAdventure.goDirection(currentRoom, direction);
        }

        else if (userCommand.startsWith("take")) {
            PlayAdventure.takeItem(currentRoom, item, myItems);
        }

        else if (userCommand.startsWith("drop")) {
            PlayAdventure.dropItem(currentRoom, item, myItems);
        }

        else {
            System.out.println("I don't understand" + userInput);
            PlayAdventure.printCurrentRoom(currentRoom);
        }

    }

    public static void printCurrentRoom(Room currentRoom) {
        System.out.println(currentRoom.getDescription());
        if (currentRoom.getName().equals(myGameLayoutJson.getStartingRoom())) {
            System.out.println("Your journey begins here");
        } else if (currentRoom.getName().equals(myGameLayoutJson.getEndingRoom())) {
            System.out.println("You have reached your final destination");
            return;
        }
        System.out.println("This room contains" + Arrays.toString(currentRoom.getItems()));
        System.out.println("From here you can go:" + Arrays.toString(currentRoom.getDirections()));
    }

    public static void printItemsList(ArrayList<String> myItems) {
        System.out.println(Arrays.toString(myItems.toArray()));
    }

    public static void goDirection(Room currentRoom, String direction) {
        Direction[] currentRoomDirections = currentRoom.getDirections();
        for (Direction directionObj : currentRoomDirections) {
            if (directionObj.getDirectionName().equals(direction)) {
                currentRoom = directionObj.getRoom();
                PlayAdventure.printCurrentRoom(currentRoom);
            } else {
                System.out.println("I can't go" + direction);
                PlayAdventure.printCurrentRoom(currentRoom);
            }
        }
    }

    public static void takeItem(Room currentRoom, String item, ArrayList<String> myItems) {
        String[] currentRoomItems = currentRoom.getItems();
        for (String itemObj : currentRoomItems) {
            if (itemObj.equals(item)) {
                myItems.add(itemObj);
                currentRoomItems = ArrayUtils.removeElement(currentRoomItems, itemObj);
                PlayAdventure.printCurrentRoom(currentRoom);
            } else {
                System.out.println("I can't take" + item);
                PlayAdventure.printCurrentRoom(currentRoom);
            }
        }
    }

    public static void dropItem(Room currentRoom, String item, ArrayList<String> myItems) {
        String[] currentRoomItems = currentRoom.getItems();
        for (String itemObj : myItems) {
            if (itemObj.equals(item)) {
                myItems.remove(itemObj);
                currentRoomItems = ArrayUtils.add(currentRoomItems, itemObj);
                PlayAdventure.printCurrentRoom(currentRoom);
            } else {
                System.out.println("I can't drop" + item);
                PlayAdventure.printCurrentRoom(currentRoom);
            }
        }
    }
}
