package tdd.todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TodoList {

    private ArrayList<Task> tasks;

    public TodoList(){
        this.tasks = new ArrayList<>();
    }

    public String addTask(Task newTask) {
        if(newTask == null){
            return "Nieprawidłowe zadanie.";
        }
        for(Task task : tasks){
            if(task.getDescription().equals(newTask.getDescription())){
                return "Istnieje zadanie z takim opisem";
            }
        }
        tasks.add(newTask);
        return "Pomyślnie dodano zadanie";
    }

    public String deleteTask(int index) {
        if(tasks.size() == 0) {
            return "Lista zadań jest pusta.";
        }
        if(index < 0) {
            return "Nieprawidłowy indeks zadania.";
        }
        if(index > tasks.size()-1) {
            return "Brak zadania o takim indeksie.";
        }
        tasks.remove(index);
        return "Pomyślnie usunięto zadanie.";
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskCount(){
        return tasks.size();
    }

    public String getOneTask(int i) {
        if (i < tasks.size() && i >= 0) {
            return tasks.get(i).getDescription();
        }
        else {
            return "Brak zadania o takim numerze!";
        }
    }

    public String updateTask(int i){
        if(i > tasks.size() - 1) {
            return "Zadanie o podanym numerze nie istnieje!";
        } else if(i < 0){
            return "Podano nieprawidłowy numer zadania!";
        } else {
            tasks.get(i).updateState();
            return "Zadanie zostało zaktualizowane!";
        }
    }

    public String saveToFile() {
        if (tasks.size() == 0){
            return "Nie ma zadań do zapisania!";
        }
        String path = saving();
        if (path == null){
            return "Zapis nie powiódł się.";
        } else {
            return "Pomyślnie zapisano do pliku o ścieżce: " + path;
        }
    }

    public String saving() {
        String dir = System.getProperty("user.dir");
        Path path = Path.of(dir + "\\savedTasksFiles\\TodoList.txt");

        int n = 0;
        while (Files.exists(path)){
            n += 1;
            String newPath = dir + "\\savedTasksFiles\\TodoList" + n + ".txt";
            path = Path.of(newPath);
        }

        String content  = "Lista zadań";
        for(int i =0; i < tasks.size(); i++){
            String state;
            if(tasks.get(i).getState()){
                state = "wykonane";
            } else {
                state = "niewykonane";
            }
            content += "\n\nZadanie " + (i + 1) + "\nStatus: " + state + "\nOpis: "
                    + tasks.get(i).getDescription();
        }
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            return null;
        }

        return path.toString();
    }
}
