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
    public void shouldGrowDefaultSizeIfMoreTaskAdded(){

        int maxSize = list.getDynamicSize();

        for(int i=0;i<maxSize-1;i++){
            Task newTask = new Task("opis zadania" + i);
            list.addTask(newTask);
        }

        Task newTask = new Task("opis zadania max");

        String message = list.addTask(newTask);

        assertEquals(message,"Pomyślnie dodano zadanie");

        Task[] tasks = list.getTasks();

        assertEquals(tasks.length,maxSize+1);

        assertEquals(tasks[maxSize+1].getDescription(),newTask.getDescription());

        assertEquals(list.getDynamicSize(),maxSize*2);
    }

    @Test
    public void shouldNotAddTaskIfDescriptionIsDuplicate(){
        Task newTask = new Task("opis zadania");

        String message = list.addTask(newTask);

        assertEquals(message,"Pomyślnie dodano zadanie");

        Task newTaskCopy = new Task("opis zadania");

        String messageCopy = list.addTask(newTask);

        assertEquals(messageCopy,"Istnieje zadanie z takim opisem");

        Task[] tasks = list.getTasks();

        assertEquals(tasks.length,1);
    }

    @Test
    public void shouldNotAddOneTaskToListIfTaskIsNull(){
        String message = list.addTask(null);

        assertEquals(message,"Nieprawidłowe zadanie.");

        Task[] tasks = list.getTasks();

        assertEquals(tasks.length,0);
    }

}