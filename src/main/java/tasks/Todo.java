package tasks;

import exceptions.DukeTodoNoDescription;

public class Todo extends Task {

    private static final String PREFIX = "T";

    public Todo(String description) throws DukeTodoNoDescription {
        super(description);
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(description + ",");
        response.append(isDone + "\n");
        return response.toString();
    }
}