package hu.bme.android.todorecyclerview.data;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Todo extends SugarRecord implements Serializable {
    private String todo;
    private boolean done;

    public Todo() {
    }

    public Todo(String todo, boolean done) {
        this.todo = todo;
        this.done = done;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
