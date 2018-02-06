import java.util.Scanner;

public class PlayAdventure {

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.println("");

        Layout myGameLayout = new Layout();
        Room[] myRooms = myGameLayout.getRooms();
        for (Room roomObj : myRooms) {
            if (myGameLayout.getStartingRoom().equals(roomObj)) {
                System.out.println(roomObj.getDescription());
                System.out.println("Your journey begins here");
                System.out.println("This room contains" + roomObj.getItems());
                System.out.println("This room contains" + roomObj.getItems());

            }
        }

    }
}
