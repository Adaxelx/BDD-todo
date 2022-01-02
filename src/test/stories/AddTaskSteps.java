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
