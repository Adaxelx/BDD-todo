package stories;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.Assert.assertTrue;

public class UpdateTaskSteps {
    TodoList list;
    String description;
    Task task;

    @BeforeScenario
    public void setUp() {
        System.out.println("hello");
        list = new TodoList();
        description = "to do sth";
    }

    @Given("a todo list contains the undone task")
    public void aToDoListContainsUndoneTask(){
        task = new Task(description);
        list.addTask(task);
    }

    @When("I update the task")
    public void updateTheTask(){
        list.updateTask(list.getTaskCount() - 1);
    }

    @Then("the task state should be done")
    public void taskStateShouldBeDone(){
        assertTrue(task.getState());
    }
}
