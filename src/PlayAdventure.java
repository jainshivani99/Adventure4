import java.util.Scanner;

public class PlayAdventure {
    
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.println("");
        Layout myGameLayout = new Layout();
        System.out.println(myGameLayout.getStartingRoom());
    }
}
