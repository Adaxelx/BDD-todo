package tdd.todo;

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
}
