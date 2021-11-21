package tdd.todo;

import java.util.Arrays;

public class TodoList {

    private Task[] tasks;
    private int taskCount;

    public TodoList(){
        this.tasks = new Task[5];
        this.taskCount = 0;
    }

    public String addTask(Task newTask) {
        if(newTask == null){
            return "Nieprawidłowe zadanie.";
        }
        for(int i=0;i<taskCount;i++){
            if(tasks[i].getDescription().equals(newTask.getDescription())){
                return "Istnieje zadanie z takim opisem";
            }
        }
        if(taskCount==tasks.length){
            changeArraySize();
        }
        tasks[taskCount++] = newTask;
        return "Pomyślnie dodano zadanie";
    }

    public Task[] getTasks() {
        return this.tasks;
    }

    public int getTaskCount(){
        return taskCount;
    }

    private void changeArraySize(){
        this.tasks = Arrays.copyOf(this.tasks,this.tasks.length*2);
    }
}
