package tdd.todo;

import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskCount(){
        return tasks.size();
    }
}
