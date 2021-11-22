import org.junit.jupiter.api.Test;
import tdd.todo.App;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    App app;

    @Test
    public void should_returnNegativeNumber_when_userNotGiveOperationNumberAsInteger(){
        String data = "test";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        app = new App(inputStream);

        int result = app.getInput();

        assertTrue(result < 0);
    }

    @Test
    public void should_returnGivenNumber_when_userGiveOperationNumberAsInteger(){
        String data = "1";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        app = new App(inputStream);

        int result = app.getInput();

        int expected = 1;
        assertEquals(expected, result);
    }

    @Test
    public void should_changeStatusToZero_when_exit(){
        app = new App();
        int statusBefore = app.getStatus();

        app.exit();

        int statusNow = app.getStatus();
        assertNotEquals(statusBefore, statusNow);
        assertEquals(0, statusNow);
    }
}
