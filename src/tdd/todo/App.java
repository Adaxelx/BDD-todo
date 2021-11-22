package tdd.todo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private final TodoList todoList = new TodoList();
    private final Scanner scanner;
    private int status = 1;

    public App(){
        scanner = new Scanner(System.in);
    }

    public App(InputStream inputStream){
        setSystemSetIn(inputStream);
        scanner = new Scanner(System.in);
    }

    public void run(){
        while(status == 1){
            start();
        }
    }

    private void start(){
        String operationsList = "\nDostępne operacje\n"
                + "1. Wyświetl wszystkie zadania\n"
                + "2. Wyświetl zadanie\n"
                + "3. Dodaj nowe zadanie\n"
                + "4. Zaktualizuj zadanie\n"
                + "5. Usuń zadanie\n"
                + "6. Zakończ\n";

        System.out.println(operationsList);
        int operationNumber = getInput();
        executeOperation(operationNumber);
    }

    public int getInput() {
        int operationNumber;

        try {
            System.out.print(("Podaj numer operacji, którą chcesz wykonać: "));
            operationNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            operationNumber = -1;
        } finally {
            setSystemSetIn(System.in);
        }
        return operationNumber;
    }

    public void setSystemSetIn(InputStream inputStream){
        if(inputStream == null){
            inputStream = System.in;
        }

        System.setIn(inputStream);
    }

    private void executeOperation(int number){
        switch (number) {
            case 1 -> getAllTasks();
            case 2 -> getTask();
            case 3 -> addTask();
            case 4 -> updateTask();
            case 5 -> deleteTask();
            case 6 -> exit();
            default -> System.out.println("Taka opcja nie istnieje!");
        }
    }

    private void getAllTasks(){
        ArrayList<Task> tasksList = todoList.getTasks();

        if(tasksList.size() == 0){
            System.out.println("Brak zadań na liście!");
        } else {
            System.out.println("Lista zadań");
            for(int i =0; i < tasksList.size(); i++){
                String state;
                if(tasksList.get(i).getState()){
                    state = "wykonane";
                } else {
                    state = "niewykonane";
                }
                System.out.println("\nZadanie " + (i + 1) + "\nStatus: " + state + "\nOpis: "
                        + tasksList.get(i).getDescription());
            }
        }
    }

    private void getTask() {
        int taskNumber;

        if(todoList.getTaskCount() == 0){
            System.out.println("Brak zadań na liście!");
        } else {
            try {
                System.out.print(("Podaj numer zadania, które chcesz wyświetlić: "));
                taskNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                taskNumber = -1;
            }
            System.out.println(todoList.getOneTask(taskNumber - 1));
        }
    }

    private void addTask() {
        System.out.print(("Wpisz treść zadania: "));
        scanner.nextLine();
        String description = scanner.nextLine();
        Task task = new Task(description);
        System.out.println(todoList.addTask(task));
    }

    private void updateTask() {
        int taskNumber;

        if(todoList.getTaskCount() == 0){
            System.out.println("Brak zadań na liście!");
        } else {
            try {
                System.out.print(("Podaj numer zadania, które chcesz zaktualizować: "));
                taskNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                taskNumber = -1;
            }
            System.out.println(todoList.updateTask(taskNumber - 1));

        }
    }

    private void deleteTask() {
        int taskNumber;

        try {
            System.out.print(("Podaj numer zadania, które chcesz usunąć: "));
            taskNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            taskNumber = -1;
        }

        System.out.println(todoList.deleteTask(taskNumber - 1));
    }

    public void exit() {
        status = 0;
        scanner.close();
        System.out.println("ToDo lista została zamknięta!");
    }

    public int getStatus(){
        return status;
    }
}
