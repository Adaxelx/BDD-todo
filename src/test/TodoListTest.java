import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoListTest {

    TodoList list;

    @BeforeEach
    void setUp() {
        list = new TodoList();
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    public void shouldAddOneTaskToList(){
        Task newTask = new Task("opis zadania");

        String message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie",message);

        Task[] tasks = list.getTasks();

        assertEquals(1,list.getTaskCount());

        assertEquals(newTask.getDescription(),tasks[0].getDescription());
    }

    @Test
    public void shouldGrowDefaultSizeIfMoreTaskAdded(){

        int maxSize = list.getTasks().length;

        for(int i=0;i<maxSize;i++){
            Task newTask = new Task("opis zadania" + i);
            list.addTask(newTask);
        }

        Task newTask = new Task("opis zadania max");

        String message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie",message);

        Task[] tasks = list.getTasks();

        assertEquals(maxSize+1,list.getTaskCount());

        assertEquals(tasks[list.getTaskCount()-1].getDescription(),newTask.getDescription());

        assertEquals(maxSize*2,list.getTasks().length);
    }

    @Test
    public void shouldNotAddTaskIfDescriptionIsDuplicate(){
        Task newTask = new Task("opis zadania");

        String message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie",message);

        Task newTaskCopy = new Task("opis zadania");

        String messageCopy = list.addTask(newTask);

        assertEquals("Istnieje zadanie z takim opisem",messageCopy);

        Task[] tasks = list.getTasks();

        assertEquals(1,list.getTaskCount());
    }

    @Test
    public void shouldNotAddOneTaskToListIfTaskIsNull(){
        String message = list.addTask(null);

        assertEquals("Nieprawidłowe zadanie.",message);

        Task[] tasks = list.getTasks();

        assertEquals(0,list.getTaskCount());
    }

}