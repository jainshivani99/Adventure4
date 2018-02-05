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
    public void getDescriptionTest() {
        assertEquals("You are in the ACM office.  There are lots of friendly ACM people.", myGameLayout.getRooms()[2].getDescription());
    }

    @Test
    public void getItemsTest() {
        String[] expectedResult = {"pizza", "swag"};
        boolean equals = Arrays.equals(expectedResult, myGameLayout.getRooms()[2].getItems());
        assertEquals(equals, true);
    }

    @Test
    public void getDirectionsTest() {
        //String[] expectedResult = {"\"directionName\": \"South\", \"room\": \"SiebelEntry\""};
//        String[] expectedResult = {
//        "          \"directionName\": \"South\",\n" +
//                "          \"room\": \"SiebelEntry\"\n" +
//                "        }\n"};
        String[] expectedResult = {"South", "SiebelEntry"};
        boolean equals = Arrays.equals(expectedResult, myGameLayout.getRooms()[2].getDirections());
        assertEquals(equals, true);
    }

    @Test
    public void getDirectionNameTest() {
        assertEquals("South", myGameLayout.getRooms()[2].getDirections()[0].getDirectionName());
    }

    @Test
    public void getRoomTest() {
        assertEquals("SiebelEntry", myGameLayout.getRooms()[2].getDirections()[0].getRoom());
    }

    @Test
    public void getStartingRoomTest() {
        assertEquals("MatthewsStreet", myGameLayout.getStartingRoom());
    }

    @Test
    public void getEndingRoomTest() {
        assertEquals("Siebel1314", myGameLayout.getEndingRoom());
    }

//    @Test
//    public void getRoomsTest() {
//        String[] expectedResult = {"name\": \"MatthewsStreet\",\r\n      \"description\": \"You are on Matthews, outside the Siebel Center\",\r\n      \"items\": [\"coin\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"East\",\r\n          \"room\": \"SiebelEntry\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"SiebelEntry\",\r\n      \"description\": \"You are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\",\r\n\t  \"items\": [\"sweatshirt\", \"key\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"West\",\r\n          \"room\": \"MatthewsStreet\"\r\n        },\r\n        {\r\n          \"directionName\": \"Northeast\",\r\n          \"room\": \"AcmOffice\"\r\n        },\r\n        {\r\n          \"directionName\": \"North\",\r\n          \"room\": \"SiebelNorthHallway\"\r\n        },\r\n        {\r\n          \"directionName\": \"East\",\r\n          \"room\": \"SiebelEastHallway\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"AcmOffice\",\r\n      \"description\": \"You are in the ACM office.  There are lots of friendly ACM people.\",\r\n      \"items\": [\"pizza\", \"swag\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"South\",\r\n          \"room\": \"SiebelEntry\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"SiebelNorthHallway\",\r\n      \"description\": \"You are in the north hallway.  You can see Siebel 1112 and the door toward NCSA.\",\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"South\",\r\n          \"room\": \"SiebelEntry\"\r\n        }, \r\n        {\r\n          \"directionName\": \"NorthEast\",\r\n          \"room\": \"Siebel1112\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"Siebel1112\",\r\n      \"description\": \"You are in Siebel 1112.  There is space for two code reviews in this room.\",\r\n      \"items\": [\"USB-C connector\", \"grading rubric\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"West\",\r\n          \"room\": \"SiebelNorthHallway\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"SiebelEastHallway\",\r\n      \"description\": \"You are in the east hallway.  You can see Einstein Bros' Bagels and a stairway.\",\r\n      \"items\": [\"bagel\", \"coffee\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"West\",\r\n          \"room\": \"SiebelEntry\"\r\n        },\r\n        {\r\n          \"directionName\": \"South\",\r\n          \"room\": \"Siebel1314\"\r\n        },\r\n        {\r\n          \"directionName\": \"Down\",\r\n          \"room\": \"SiebelBasement\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"Siebel1314\",\r\n      \"description\": \"You are in Siebel 1314.  There are happy CS 126 students doing a code review.\",\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"North\",\r\n          \"room\": \"SiebelEastHallway\"\r\n        }\r\n      ]\r\n    },\r\n    {\r\n      \"name\": \"SiebelBasement\",\r\n      \"description\": \"You are in the basement of Siebel.  You see tables with students working and door to computer labs.\",\r\n      \"items\": [\"pencil\"],\r\n      \"directions\": [\r\n        {\r\n          \"directionName\": \"Up\",\r\n          \"room\": \"SiebelEastHallway\"\r\n        }\r\n      ]\r\n    }";
//        assertEquals(expectedResult, myGameLayout.getRooms());
//    }

}
