import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import tdd.todo.Task;
//import tdd.todo.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        assertEquals("Pomyślnie dodano zadanie", message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(1, list.getTaskCount());

        assertEquals(newTask.getDescription(), tasks.get(0).getDescription());
    }

    @Test
    public void shouldGrowDefaultSizeIfMoreTaskAdded(){

        int numberOfTasks = 5;

        for(int i=0; i<numberOfTasks; i++){
            Task newTask = new Task("opis zadania" + i);
            list.addTask(newTask);
        }

        Task newTask = new Task("opis zadania max");

        String message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie", message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(numberOfTasks+1, list.getTaskCount());

        assertEquals(tasks.get(list.getTaskCount()-1).getDescription(), newTask.getDescription());
    }

    @Test
    public void shouldNotAddTaskIfDescriptionIsDuplicate(){
        Task newTask = new Task("opis zadania");

        String message = list.addTask(newTask);

        assertEquals("Pomyślnie dodano zadanie",message);

        Task newTaskCopy = new Task("opis zadania");

        String messageCopy = list.addTask(newTaskCopy);

        assertEquals("Istnieje zadanie z takim opisem", messageCopy);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(1, list.getTaskCount());
    }

    @Test
    public void shouldNotAddOneTaskToListIfTaskIsNull(){
        String message = list.addTask(null);

        assertEquals("Nieprawidłowe zadanie.",message);

        ArrayList<Task> tasks = list.getTasks();

        assertEquals(0, list.getTaskCount());
    }

    @Test
    public void shouldDeleteTask() {
        Task newTask = new Task("opis zadania");
        list.addTask(newTask);
        assertEquals(1, list.getTaskCount());
        String message = list.deleteTask(0);
        assertEquals(0, list.getTaskCount());
        assertEquals("Pomyślnie usunięto zadanie.", message);
    }

    @Test
    public void shouldReturnErrorIfListIsEmpty() {
        String message = list.deleteTask(0);
        assertEquals("Lista zadań jest pusta.", message);
    }

    @Test
    public void shouldReturnErrorIfInvalidIndex() {
        Task newTask = new Task("opis zadania");
        list.addTask(newTask);
        String message = list.deleteTask(-1);
        assertEquals("Nieprawidłowy indeks zadania.", message);
    }

    @Test
    public void shouldReturnErrorIfIndexOutOfBounds() {
        Task newTask = new Task("opis zadania");
        list.addTask(newTask);
        String message = list.deleteTask(1);
        assertEquals("Brak zadania o takim indeksie.", message);
    }

    @Test
    public void shouldGetOneTask()
    {
        String taskDescription = "task";
        Task newTask = new Task(taskDescription);
        list.addTask(newTask);
        String result = list.getOneTask(0);
        assertEquals(taskDescription, result);
    }

    @Test
    public void shouldReturnErrorIfTaskNotExists()
    {
        String result = list.getOneTask(10);
        assertEquals("Brak zadania o takim numerze", result);
    }


}