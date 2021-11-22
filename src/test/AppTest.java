import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.todo.App;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    public void should_returnNegativeNumber_when_userNotGiveOperationNumberAsInteger(){
        Integer result = app.getInput();

        assertTrue(result < 0);
    }
}
