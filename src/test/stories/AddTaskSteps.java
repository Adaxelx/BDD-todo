package stories;

import org.jbehave.core.annotations.*;
import tdd.todo.Task;
import tdd.todo.TodoList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskSteps {
    TodoList list;
    String message;
    Task newTask;

    @BeforeScenario
    void setUp() {
        list = new TodoList();
        message = "";
    }

    @AfterScenario
    void tearDown() {
        list = null;
        message="";
        newTask = null;
    }

    @Given("istnieje zadanie z opisem test1")
    public void taskWithDescription(){
        list = new TodoList();
        newTask = new Task("test1");
        list.addTask(newTask);
    }

    @When("uzytkownik sprobuje dodac zadanie z opisem=<description>")
    public void addTaskToList(@Named("description") String description){
        newTask = new Task(description);
        message = list.addTask(newTask);
    }

    @Then("zostanie zwrocona wiadomosc=<expectedMessage> i długość listy będzie wynosić <length>")
    public void showResultOfAdd(@Named("expectedMessage") String expectedMessage,@Named("length")int length){

        assertEquals(expectedMessage, message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(length, list.getTaskCount());

        if(length !=1){
            assertEquals(newTask.getDescription(), tasks.get(length-1).getDescription());
        }
    }
}
