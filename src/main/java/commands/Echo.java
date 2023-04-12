package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Echo implements Command {
    private String input;

    public Echo(String input) {
        this.input = input;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.echo(input);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}
