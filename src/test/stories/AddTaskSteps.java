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

    @Given("nie ma żadnych zadań")
    public void emptyToDoList(){
        list = new TodoList();
    }

    @When("użytkownik spróbuje dodać zadanie z uzupełnionym opisem test")
    public void addTaskToList(){
        newTask = new Task("test");
        message = list.addTask(newTask);
    }

    @Then("zadanie zostanie dodane i pojawi się na liście zadan zadanie z opisem test")
    public void showResultOfAdd(){

        assertEquals("Pomyślnie dodano zadanie", message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(1, list.getTaskCount());

        assertEquals(newTask.getDescription(), tasks.get(0).getDescription());
    }
}
