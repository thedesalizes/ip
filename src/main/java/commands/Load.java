package commands;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Load implements Command{
    private String filePath;
    public Load(String filePath)  {
        this.filePath = filePath;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String task;
            int count = 0;
            while ((task = reader.readLine()) != null) {
                // process the line.
                // assumes saved tasks are correct
                String[] taskArgs = task.split(",", 3);
                switch (taskArgs[0]) {
                    case "E": {
                        Command addTask = new ImportTask("event", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute(tasks, ui, storage);
                        break;
                    }
                    case "D": {
                        Command addTask = new ImportTask("deadline", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute(tasks, ui, storage);
                        break;
                    }
                    case "T": {
                        Command addTask = new ImportTask("todo", taskArgs[1], taskArgs[2], tasks);
                        addTask.execute(tasks, ui, storage);
                        break;
                    }
                    default:
                        System.out.println("Unrecognised task format");
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            ui.showErrorMessage(e);
        } catch (IOException e) {
            ui.showErrorMessage(e);
        }
        ui.showLoadComplete();
    }

    public boolean isExit() {
        return false;
    }
}
