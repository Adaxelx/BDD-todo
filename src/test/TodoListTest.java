import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import tdd.todo.Task;
import tdd.todo.TodoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        assertEquals(1, list.getTaskCount());
    }

    @Test
    public void shouldNotAddOneTaskToListIfTaskIsNull(){
        String message = list.addTask(null);

        assertEquals("Nieprawidłowe zadanie.",message);

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
        assertEquals("Brak zadania o takim numerze!", result);
    }

    @Test
    public void shouldNotGetOneTaskIfGiveInvalidTaskNumber()
    {
        String result = list.getOneTask(-1);
        assertEquals("Brak zadania o takim numerze!", result);
    }

    @Test
    public void shouldUpdateTaskIfGiveProperTaskNumber() {
        Task task = new Task("test");
        boolean stateBefore = task.getState();
        list.addTask(task);
        int taskNumber = 0;

        String resultMessage = list.updateTask(taskNumber);
        boolean resultName = task.getState();

        String expectedMessage = "Zadanie zostało zaktualizowane!";
        assertEquals(expectedMessage, resultMessage);
        assertEquals(!stateBefore, resultName);
    }

    @Test
    public void shouldUpdateTaskTwoTimes() {
        Task task = new Task("test");
        boolean stateBefore = task.getState();
        list.addTask(task);
        int taskNumber = 0;

        String resultMessage = list.updateTask(taskNumber);
        boolean resultName = task.getState();

        String expectedMessage = "Zadanie zostało zaktualizowane!";
        assertEquals(expectedMessage, resultMessage);
        assertEquals(!stateBefore, resultName);

        stateBefore = task.getState();

        resultMessage = list.updateTask(taskNumber);
        resultName = task.getState();

        assertEquals(expectedMessage, resultMessage);
        assertEquals(!stateBefore, resultName);
    }

    @Test
    public void shouldNotUpdateTaskIfGiveInvalidTaskNumber() {
        Task task = new Task("test");
        list.addTask(task);
        int taskNumber = -1;

        String result = list.updateTask(taskNumber);

        String expected = "Podano nieprawidłowy numer zadania!";
        assertEquals(expected, result);
    }

    @Test
    public void shouldNotUpdateTaskIfGiveNotExistTaskNumber() {
        Task task = new Task("test");
        list.addTask(task);
        int taskNumber = 2;

        String result = list.updateTask(taskNumber);

        String expected = "Zadanie o podanym numerze nie istnieje!";
        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnErrorIfListIsEmptyToSavingToFile() {
        String result = list.saveToFile();

        String expected = "Nie ma zadań do zapisania!";

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnMessagesWithPath() {
        Task task = new Task("test");
        list.addTask(task);

        String dir = System.getProperty("user.dir");
        dir += "\\savedTasksFiles\\TodoList.txt";
        try {
            Files.delete(Path.of(dir));
        } catch (IOException e) {}
        String result = list.saveToFile();

        String expected = "Pomyślnie zapisano do pliku o ścieżce: " + dir;
        assertEquals(expected, result);
    }

    @Test
    public void shouldSaveTaskInFile() {
        Task task = new Task("test");
        list.addTask(task);

        String dir = System.getProperty("user.dir");
        dir += "\\savedTasksFiles\\TodoList.txt";

        try {
            Files.delete(Path.of(dir));
        } catch (IOException e) {}
        list.saveToFile();

        String result = "";
        Path path = Path.of(dir);
        try {
            result = Files.readString(path);
        } catch (IOException e) {
            assertEquals(1,0);
        }

        String expected = "Lista zadań\n" +
                "\n" +
                "Zadanie 1\n" +
                "Status: niewykonane\n" +
                "Opis: test";

        assertEquals(expected, result);
    }
}