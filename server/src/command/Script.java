package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;

public class Script extends Command {
    private final ConsoleCommands consoleCommands;

    public Script(ConsoleCommands consoleCommands) {
        setDescription("Добавить новый элемент в коллецию");
        setArgs(" fileName");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager, String... arg) throws JAXBException {
        String path;
        String filePath = System.getenv("INPUT_PATH");
        if(filePath == null) {
            path =  "src\\ConsoleApp\\resources\\";
        } else {
            path = filePath;
        }
        if (arg.length != 1 ) throw new ArgException();
        else {
            File file = new File(path + arg[0]);
            consoleCommands.executeScript(file, commandManager, (Application.Collection) collection);
        }
    }
}
