package stories;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Step;
import org.jbehave.core.steps.Steps;
import tdd.todo.Task;
import tdd.todo.TodoList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskSteps extends Steps {
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



    @Given("nie ma zadań do zapisu")
    public void emptyToDoListToSaveToFile(){
        list = new TodoList();
    }

    @When("uzytkownik próbuje wykonac zapis do pliku")
    public void tryToSaveEmptyToDoListToFile(){
        message = list.saveToFile();
    }

    @Then("zapis nie jest wykonany i wyświetlany jest komunikat o braku zadań")
    public void showResultOfSaveEmptyToDoListToFile(){

        assertEquals("Nie ma zadań do zapisania!", message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(0, list.getTaskCount());

    }


}
