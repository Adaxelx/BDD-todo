package tdd.todo;

public class Task {

    private final String description;
    private boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getState(){
        return done;
    }

    public void updateState(){
        done = !done;
    }
}
