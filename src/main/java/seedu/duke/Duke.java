package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.Save;
import seedu.duke.exceptions.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.parser.CommandParser;
import seedu.duke.ui.Ui;

public class Duke {

    private CommandParser parser;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new CommandParser(ui);

        try {
            tasks = new TaskList();
            storage = new Storage(filePath);
            storage.load().execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            } finally {
                ui.showLine();
            }
        }
        Command save = new Save();
        save.execute(tasks, ui, storage);
    }

    public static void main(String[] args) {
        new Duke("data/duke.tasks.txt").run();
    }
}
