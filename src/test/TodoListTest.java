import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class TodoListTest {

    TodoList list;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        list = new TodoList();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    public void shouldAddOneTaskToList(){
        Task newTask = new Task("opis zadania");

        String message = list.addTask(newTask);

        assertEquals(message,"Pomyślnie dodano zadanie");

        Task[] tasks = list.getTasks();

        assertEquals(tasks.length,1);

        assertEquals(tasks[0].getDescription(),newTask.getDescription());
    }

    @Test
    public void shouldNotAddOneTaskToListIfTaskIsNull(){
        String message = list.addTask(null);

        assertEquals(message,"Nieprawidłowe zadanie.");

        Task[] tasks = list.getTasks();

        assertEquals(tasks.length,0);
    }

}