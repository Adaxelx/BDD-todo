package stories;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import tdd.todo.Task;
import tdd.todo.TodoList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTaskSteps {
    TodoList list;
    String newTask;
    ArrayList<Task> newList;

    @BeforeScenario
    public void setUp() {
        list = new TodoList();
        newList = new ArrayList<>();
    }

    @AfterScenario
    public void tearDown() {
        list = null;
        newTask = null;
        newList = null;
    }

    ////Scenario: pobieranie pustej listy

    @Given("lista zadan jest pusta")
    public void givenPustaLista() {
        list = new TodoList();
    }

    @When("pobiorę listę zadań")
    public void whenUzytkownikChcePobracListeZadan() {
        newList = list.getTasks();
    }

    @Then("zostanie zwrócona pusta lista")
    public void thenZostanieZwroconaPustaLista() {
        assertEquals(0, newList.size());
    }

    ////Scenario: pobieranie zadania o indeksie 0

    @Given("lista zadan ma zadanie o indeksie 0")
    public void givenListaZZadaniem() {
        list = new TodoList();
        Task t = new Task("Nowe zadanie");
        list.addTask(t);
    }

    @When("pobiorę zadanie o indeksie 0")
    public void whenUzytkownikChcePobracZadanie() {
        newTask = list.getOneTask(0);
    }

    @Then("zostanie zwrócone zadanie o indeksie 0")
    public void thenZostanieZwroconeZadanieOIndeksie0() {
        assertEquals(newTask, "Nowe zadanie");
    }
}