import com.google.gson.Gson;
import static org.junit.Assert.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class LayoutTest {

    private Layout myGameLayout;
    private static final String SIEBEL_JSON = "{\n" +
            "  \"startingRoom\": \"MatthewsStreet\",\n" +
            "  \"endingRoom\": \"Siebel1314\",\n" +
            "  \"rooms\": [\n" +
            "    {\n" +
            "      \"name\": \"MatthewsStreet\",\n" +
            "      \"description\": \"You are on Matthews, outside the Siebel Center\",\n" +
            "      \"items\": [\"coin\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"East\",\n" +
            "          \"room\": \"SiebelEntry\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SiebelEntry\",\n" +
            "      \"description\": \"You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\",\n" +
            "\t  \"items\": [\"sweatshirt\", \"key\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"West\",\n" +
            "          \"room\": \"MatthewsStreet\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"Northeast\",\n" +
            "          \"room\": \"AcmOffice\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"North\",\n" +
            "          \"room\": \"SiebelNorthHallway\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"East\",\n" +
            "          \"room\": \"SiebelEastHallway\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"AcmOffice\",\n" +
            "      \"description\": \"You are in the ACM office.  There are lots of friendly ACM people.\",\n" +
            "      \"items\": [\"pizza\", \"swag\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"South\",\n" +
            "          \"room\": \"SiebelEntry\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SiebelNorthHallway\",\n" +
            "      \"description\": \"You are in the north hallway.  You can see Siebel 1112 and the door toward NCSA.\",\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"South\",\n" +
            "          \"room\": \"SiebelEntry\"\n" +
            "        }, \n" +
            "        {\n" +
            "          \"directionName\": \"NorthEast\",\n" +
            "          \"room\": \"Siebel1112\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Siebel1112\",\n" +
            "      \"description\": \"You are in Siebel 1112.  There is space for two code reviews in this room.\",\n" +
            "      \"items\": [\"USB-C connector\", \"grading rubric\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"West\",\n" +
            "          \"room\": \"SiebelNorthHallway\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SiebelEastHallway\",\n" +
            "      \"description\": \"You are in the east hallway.  You can see Einstein Bros' Bagels and a stairway.\",\n" +
            "      \"items\": [\"bagel\", \"coffee\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"West\",\n" +
            "          \"room\": \"SiebelEntry\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"South\",\n" +
            "          \"room\": \"Siebel1314\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"directionName\": \"Down\",\n" +
            "          \"room\": \"SiebelBasement\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Siebel1314\",\n" +
            "      \"description\": \"You are in Siebel 1314.  There are happy CS 126 students doing a code review.\",\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"North\",\n" +
            "          \"room\": \"SiebelEastHallway\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"SiebelBasement\",\n" +
            "      \"description\": \"You are in the basement of Siebel.  You see tables with students working and door to computer labs.\",\n" +
            "      \"items\": [\"pencil\"],\n" +
            "      \"directions\": [\n" +
            "        {\n" +
            "          \"directionName\": \"Up\",\n" +
            "          \"room\": \"SiebelEastHallway\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        final HttpResponse<String> stringHttpResponse = Unirest.get(url).asString();

        if (stringHttpResponse.getStatus() == 200) {
            String json = stringHttpResponse.getBody();
            myGameLayout = gson.fromJson(json, Layout.class);
        } else {
            myGameLayout = gson.fromJson(SIEBEL_JSON, Layout.class);
        }

    }

    @Test
    public void getNameTest() {
        assertEquals("AcmOffice", myGameLayout.getRooms()[2].getName());
    }

    @Test
    public void getNameObj2Test() {
        assertEquals("SiebelNorthHallway", myGameLayout.getRooms()[3].getName());
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void getNameObj3Test() {
        myGameLayout.getRooms()[15].getName();
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("You are in the ACM office.  There are lots of friendly ACM people.", myGameLayout.getRooms()[2].getDescription());
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void getDescriptionObj2Test() {
        myGameLayout.getRooms()[11].getDescription();
    }

//    @Test
//    public void getItemsTest() {
//        String[] expectedResult = {"pizza", "swag"};
//        boolean equals = Arrays.equals(expectedResult, myGameLayout.getRooms()[2].getItems());
//        assertEquals(equals, true);
//    }

    @Test
    public void getDirectionsTest() {
        Direction expectedDirection = new Direction();
        expectedDirection.setDirectionName("South");
        expectedDirection.setRoom("SiebelEntry");
        Direction myDirection = myGameLayout.getRooms()[2].getDirections()[0];
        assertEquals(expectedDirection.getDirectionName(), myDirection.getDirectionName());
        assertEquals(expectedDirection.getRoom(), myDirection.getRoom());
    }

    @Test
    public void getDirectionNameTest() {
        assertEquals("South", myGameLayout.getRooms()[2].getDirections()[0].getDirectionName());
    }

    @Test
    public void getDirectionNameObj2Test() {
        assertEquals("South", myGameLayout.getRooms()[3].getDirections()[0].getDirectionName());
    }

    @Test
    public void getRoomTest() {
        assertEquals("SiebelEntry", myGameLayout.getRooms()[2].getDirections()[0].getRoom());
    }

    @Test
    public void getRoomObj2Test() {
        assertEquals("SiebelEntry", myGameLayout.getRooms()[3].getDirections()[0].getRoom());
    }

    @Test
    public void getStartingRoomTest() {
        assertEquals("MatthewsStreet", myGameLayout.getStartingRoom());
    }

    @Test
    public void getEndingRoomTest() {
        assertEquals("Siebel1314", myGameLayout.getEndingRoom());
    }

    @Test
    public void getRoomsTest() {
        Room expectedRoom = new Room();
        expectedRoom.setName("AcmOffice");
        expectedRoom.setDescription("You are in the ACM office.  There are lots of friendly ACM people.");
//        String[] myItems = {"pizza", "swag"};
//        expectedRoom.setItems(myItems);
        Room[] myRooms = myGameLayout.getRooms();
        assertEquals(expectedRoom.getName(), myRooms[2].getName());
        assertEquals(expectedRoom.getDescription(), myRooms[2].getDescription());
        boolean expected = Arrays.equals(expectedRoom.getItems(), myRooms[2].getItems());
        assertEquals(expected, true);
    }
}
