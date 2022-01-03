package stories;

import org.jbehave.core.annotations.*;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.Assert.*;

public class UpdateTaskSteps {
    TodoList list;
    TodoList listBefore;
    String description;
    Task task;
    String message;

    @BeforeScenario
    public void setUp() {
        list = new TodoList();
        description = "to do sth";
    }

    @Given("lista zadań zawiera zadanie o statusie niezrobione")
    @Alias("lista zadań nie zawiera zadania o ineksie 1")
    public void toDoListContainsUndoneTask(){
        task = new Task(description);
        list.addTask(task);
        listBefore = list;
    }

    @Given("lista zadań zawiera zadanie o statusie zrobione")
    public void toDoListContainsDoneTask(){
        task = new Task(description);
        list.addTask(task);
        list.updateTask(list.getTaskCount() - 1);
    }

    @Given("lista zadań zawiera zadania o nieujemnych indeksach")
    public void toDoListContainsTaskWithNoNegativeId(){
        task = new Task(description);
        list.addTask(task);
        task = new Task(description);
        list.addTask(task);
        listBefore = list;
    }

    @Given("lista zadań jest pusta")
    public void toDoListIsEmpty(){
        listBefore = list;
    }

    @When("zaktualizuję stan zadania")
    public void updateTheTask(){
        list.updateTask(list.getTaskCount() - 1);
    }

    @When("zaktualizuję stan zadania o indeksie 1")
    public void updateNotExistTask(){
        message = list.updateTask(1);
    }

    @When("zaktualizuję stan zadania o indeksie -1")
    public void updateTaskWithNegativeId(){
        message = list.updateTask(-1);
    }

    @When("zaktualizuję stan pierwszego zadania")
    public void updateFirstTask(){
        message = list.updateTask(0);
    }

    @Then("zadanie powinno mieć status zrobione")
    public void taskStateShouldBeDone(){
        assertTrue(task.getState());
    }

    @Then("zadanie powinno mieć status niezrobione")
    public void taskStateShouldBeUndone(){
        assertFalse(task.getState());
    }

    @Then("powinien zostać zwrócony komunikat \"Zadanie o podanym numerze nie istnieje!\"")
    public void shouldReturnCommunicateAboutNotExistTask(){
        assertEquals("Zadanie o podanym numerze nie istnieje!", message);
    }

    @Then("powinien zostać zwrócony komunikat \"Podano nieprawidłowy numer zadania!\"")
    public void shouldReturnCommunicateAboutBadTaskId(){
        assertEquals("Podano nieprawidłowy numer zadania!", message);
    }

    @Then("lista powinna pozostać niezmieniona")
    public void toDoListShouldBeNotChanged(){
        assertEquals(listBefore, list);
    }
}
