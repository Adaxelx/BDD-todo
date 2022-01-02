package stories;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Step;
import org.jbehave.core.steps.Steps;
import tdd.todo.Task;
import tdd.todo.TodoList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveTasksToFileSteps extends Steps {

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

    @Given("nie ma zadań do zapisu")
    public void emptyToDoListToSaveToFile(){
        list = new TodoList();
    }

    @When("uzytkownik spróbuje wykonac zapis do pliku")
    public void tryToSaveEmptyToDoListToFile(){
        message = list.saveToFile();
    }

    @Then("zapis nie jest wykonany i wyświetlany jest komunikat o braku zadań")
    public void showResultOfSaveEmptyToDoListToFile(){

        assertEquals("Nie ma zadań do zapisania!", message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(0, list.getTaskCount());

    }


    @Given("istnieją jakieś zadania na liście")
    public void notEmptyToDoList(){
        list = new TodoList();
        newTask = new Task("test");
        message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie", message);
    }

    @When("użytkownik spróbuje wykonac zapis do pliku")
    public void tryToSaveToDoListToFile(){
        message = list.saveToFile();
    }

    @Then("zapis został wykonany pomyślnie i została wyświetlona ściezka do pliku")
    public void showResultOfSaveToDoListToFile(){

        String dir = System.getProperty("user.dir");
        dir += "\\savedTasksFiles\\TodoList.txt";
        try {
            Files.delete(Path.of(dir));
        } catch (IOException e) {}

        String expected = "Pomyślnie zapisano do pliku o ścieżce: " + dir;
        assertEquals(expected, message);
    }
}
