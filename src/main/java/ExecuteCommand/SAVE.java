package ExecuteCommand;

import Exception.DukeException;
import Storage.*;
import UI.*;
public class SAVE extends Command{
    public static final Storage storage = Storage.getStorage();

    public void execute() throws DukeException {
        storage.writeToFile(storage.tasksAddInFile());
        Ui.Echo("The tasks have been saved into ./data/duke.txt");
    }
}