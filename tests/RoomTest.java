import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import com.google.gson.Gson;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    @Before
    public void setUp() throws Exception {
        URL url = new URL("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
        InputStream inStream = url.openStream();
        InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

    }

    @Test
    public void getNameTest() {
       // assertEquals(41758, myCourseGrades.getCRN());
    }

    @Test
    public void getDescriptionTest() {
        // assertEquals(41758, myCourseGrades.getCRN());
    }

    @Test
    public void getItemsTest() {
        // assertEquals(41758, myCourseGrades.getCRN());
    }

    @Test
    public void getDirectionsTest() {
        // assertEquals(41758, myCourseGrades.getCRN());
    }

    @Test
    public void getDirectionName() {
        // assertEquals(41758, myCourseGrades.getCRN());
    }

    @Test
    public void getRoomTest() {
        // assertEquals(41758, myCourseGrades.getCRN());
    }

}
