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

    //boolean is show if the player is in duel mode
    private static boolean duel = false;

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

        if (args.length > 0) {
            String filePath = args[0];
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
        Player p1 = myGameLayoutJson.getPlayer();
        Item[] myItems = p1.getItems();

        Monster currentMonster = null;
        while (true) {
            String userInput = myScan.nextLine();
            String userCommand = userInput.toLowerCase();

            if (duel == false) {
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
                    String thisItem = userInput.substring(5);
                    PlayAdventure.takeItem(currentRoom, thisItem, myItems, p1);
                } else if (userCommand.startsWith("drop ")) {
                    String thisItem = userInput.substring(5);
                    PlayAdventure.dropItem(currentRoom, thisItem, myItems, p1);
                } else if (userCommand.startsWith("duel ")) {
                    String desiredMonster = userInput.substring(5);
                    duel = PlayAdventure.validateMonster(currentRoom, desiredMonster);
                    if (duel) {
                        Monster[] myMonsters = myGameLayoutJson.getMonsters();
                        for (Monster monsterObj : myMonsters) {
                            if (monsterObj.getName().equalsIgnoreCase(desiredMonster)) {
                                currentMonster = monsterObj;
                            }
                        }
                    }
                } else if (userCommand.equalsIgnoreCase("playerinfo")) {
                    PlayAdventure.printPlayerInfo(p1);
                } else {
                    System.out.println("I don't understand " + userInput);
                    PlayAdventure.printCurrentRoom(currentRoom);
                }
            } else {
                if (userCommand.equalsIgnoreCase("attack")) {
                   PlayAdventure.attack(p1, currentMonster, currentRoom);
                } else if (userCommand.startsWith("attack with ")) {
                    String desiredItem = userInput.substring(12);

                } else if (userCommand.equalsIgnoreCase("disengage")) {
                    duel = false;
                    PlayAdventure.printCurrentRoom(currentRoom);

                } else if (userCommand.equalsIgnoreCase("status")) {
                    System.out.println(p1.getName() + ": " + p1.getHealth());
                    System.out.println(currentMonster.getName() + ": " + currentMonster.getHealth());

                } else if (userCommand.equalsIgnoreCase("list")) {
                    PlayAdventure.printItemsList(myItems);
                } else if (userCommand.equalsIgnoreCase("playerinfo")) {
                    PlayAdventure.printPlayerInfo(p1);

                } else if (userInput.equalsIgnoreCase("quit")
                        || userInput.equalsIgnoreCase("exit")) {
                    System.exit(0);
                } else {
                    System.out.println("I don't understand " + userInput);
                    PlayAdventure.printCurrentRoom(currentRoom);
                }
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
        System.out.println("This room contains: ");
        for (int index = 0; index < currentRoomItems.length; index++) {
            System.out.print(currentRoomItems[index].getName());
            if (index < currentRoomItems.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        String[] currentMonstersInRoom = currentRoom.getMonstersInRoom();
        System.out.println("The monsters in the room are " + Arrays.toString(currentMonstersInRoom));

        //if all the monsters in the room have been defeated then print directions
        if (currentMonstersInRoom.length == 0) {
            Direction[] currentRoomDirections = currentRoom.getDirections();
            for (Direction directionObj : currentRoomDirections) {
                System.out.println("From here, you can go: " + directionObj.getDirectionName());
            }
        }
    }

    public static void printItemsList(Item[] myItems) {
        for (Item itemObj : myItems) {
            System.out.println(itemObj.getName() + ",");
        }
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

    public static void takeItem(Room currentRoom, String thisItem, Item[] myItems, Player p1) {
        Item[] currentRoomItems = currentRoom.getItems();
        for (Item itemObj : currentRoomItems) {
            if (itemObj.getName().equalsIgnoreCase(thisItem)) {
                myItems = ArrayUtils.add(myItems, itemObj);
                p1.setItems(myItems);
                currentRoomItems = ArrayUtils.removeElement(currentRoomItems, itemObj);
                currentRoom.setItems(currentRoomItems);
                PlayAdventure.printCurrentRoom(currentRoom);
                return;
            }
        }
        System.out.println("I can't take " + thisItem);
        PlayAdventure.printCurrentRoom(currentRoom);
    }

    public static void dropItem(Room currentRoom, String thisItem, Item[] myItems, Player p1) {
        Item[] currentRoomItems = currentRoom.getItems();
        for (Item itemObj : myItems) {
            if (itemObj.getName().equalsIgnoreCase(thisItem)) {
                myItems = ArrayUtils.removeElement(myItems, itemObj);
                p1.setItems(myItems);
                currentRoomItems = ArrayUtils.add(currentRoomItems, itemObj);
                currentRoom.setItems(currentRoomItems);
                PlayAdventure.printCurrentRoom(currentRoom);
                return;
            }
        }
        System.out.println("I can't drop " + thisItem);
        PlayAdventure.printCurrentRoom(currentRoom);
    }

    public static boolean validateMonster(Room currentRoom, String desiredMonster) {
        String[] currentMonsters = currentRoom.getMonstersInRoom();
        for (String monsterName : currentMonsters) {
            if (desiredMonster.equalsIgnoreCase(monsterName)) {
                System.out.println("You are now in a duel with " + monsterName);
                return true;
            }
        }
        System.out.println("I can't duel with " + desiredMonster);
        return false;
    }

    public static void attack(Player p1, Monster currentMonster, Room currentRoom) {
        System.out.println("Value of player's health pre-attack: " + p1.getHealth());
        double monsterCurrentHealth = currentMonster.getHealth();
        System.out.println("Value of monster health pre-attack: " + monsterCurrentHealth);
        double playerDamage = p1.getAttack() - currentMonster.getDefense();
        currentMonster.setHealth(monsterCurrentHealth - playerDamage);
        if (currentMonster.getHealth() <= 0) {
            System.out.println(currentMonster.getName() + " is dead");
            duel = false;
            String[] currentRoomMonsters = currentRoom.getMonstersInRoom();
            currentRoomMonsters = ArrayUtils.removeElement(currentRoomMonsters, currentMonster.getName());
            currentRoom.setMonstersInRoom(currentRoomMonsters);
        } else {
            double monsterDamage = currentMonster.getAttack() - p1.getDefense();
            p1.setHealth(p1.getHealth() - monsterDamage);
            if (p1.getHealth() <= 0) {
                System.out.println("Value of player's health post-attack: " + p1.getHealth());
                System.out.println(p1.getName() + " is dead");
                System.exit(0);
            }
        }
        System.out.println("Value of player's health post-attack: " + p1.getHealth());
        System.out.println("Value of monster's health post-attack: " + currentMonster.getHealth());
    }

    public static void printPlayerInfo(Player p1) {
        System.out.println("Player name: " + p1.getName());
        System.out.println("Player level: " + p1.getLevel());
        System.out.println("Player attack: " + p1.getAttack());
        System.out.println("Player defense: " + p1.getDefense());
        System.out.println("Player health: " + p1.getHealth());
    }
}
