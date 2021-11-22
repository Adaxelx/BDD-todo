import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.todo.App;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    public void should_returnNegativeNumber_when_userNotGiveOperationNumberAsInteger(){
        String data = "test";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());

        int result = app.getInput(inputStream);

        assertTrue(result < 0);
    }

    @Test
    public void should_returnGivenNumber_when_userGiveOperationNumberAsInteger(){
        String data = "1";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());

        int result = app.getInput(inputStream);

        int expected = 1;
        assertEquals(expected, result);
    }
}
