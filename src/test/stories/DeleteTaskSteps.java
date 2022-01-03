package stories;
import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTaskSteps extends Steps {
    TodoList list;
    String message;
    Task newTask;

    @BeforeScenario
    public void setUp() {
        list = new TodoList();
        message = "";
    }

    @AfterScenario
    public void tearDown() {
        list = null;
        message="";
        newTask = null;
    }

    ////Scenario: Usuwanie istniejącego zadania z indeksem 0

    @Given("lista z zadaniem z indeksem 0")
    public void givenListaZZadaniemZIndeksem0() {
        list = new TodoList();
        Task t = new Task("Nowe zadanie 0");
        list.addTask(t);
    }


    @When("uzytkownik chce usunac zadanie")
    public void whenUzytkownikChceUsunacZadanie() {
        int index = 0;
        message = list.deleteTask(index);
    }

    @Then("zadanie z indeksem 0 znika z listy i wyswietlony zostaje komunikat powodzenia")
    public void thenZadanieZIndeksem0ZnikaZListyIWyswietlonyZostajeKomunikatPowodzenia() {
        assertEquals(0, list.getTaskCount());
        assertEquals("Pomyślnie usunięto zadanie.", message);
    }

    ////Scenario: Usuwanie zadania z indeksem 0 z pustej listy

    @Given("pusta lista")
    public void givenPustaLista() {
        list = new TodoList();
    }

    @When("uzytkownik chce usunac nieistniejace zadanie")
    public void whenUzytkownikChceUsunacNieistniejaceZadanie() {
        int index = 0;
        assertEquals(0,list.getTaskCount());
        message = list.deleteTask(index);
    }

    @Then("zadanie nie zostaje usuniete i wyswietlone zostajy komunikat o pustej liscie")
    public void thenZadanieNieZostajeUsunieteIWyswietloneZostajyKomunikatOPustejLiscie() {
        assertEquals("Lista zadań jest pusta.", message);
        assertEquals(0, list.getTaskCount());
    }

    ////Scenario: Usuwanie zadania z indeksem -1 z listy zawierającej zadanie z indeksem 0


    @When("uzytkownik chce usunac nieistniejace zadanie z indeksem -1")
    public void whenUzytkownikChceUsunacNieistniejaceZadanieZIndeksem1() {
        int index = -1;
        assertEquals(1,list.getTaskCount());
        message = list.deleteTask(index);
    }

    @Then("zadanie nie zostaje usuniete i zostaje wyswietlony komnikat o nieprawidlowym indeksie")
    public void thenZadanieNieZostajeUsunieteIZostajeWyswietlonyKomnikatONieprawidlowymIndeksie() {
        assertEquals("Nieprawidłowy indeks zadania.", message);
        assertEquals(1,list.getTaskCount());
    }

    ////Scenario: Usuwanie nieistniejącego zadania z indeksem 10 z lity zawierającej zadanie z indeksem 0

    @When("uzytkownik chce usunac nieistniejace zadanie z indeksem 10")
    public void whenUzytkownikChceUsunacNieistniejaceZadanieZIndeksem10() {
        int index = 10;
        assertEquals(1,list.getTaskCount());
        message = list.deleteTask(index);
    }

    @Then("zadanie nie zostaje usuniete i zostaje wyswietlony komunikat o nieistniejacym zadaniu")
    public void thenZadanieNieZostajeUsunieteIZostajeWyswietlonyKomunikatONieistniejacymZadaniu() {
        assertEquals("Brak zadania o takim indeksie.", message);
        assertEquals(1,list.getTaskCount());
    }



}
