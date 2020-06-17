package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class AddCommand extends Command {
    private final ConsoleCommands consoleCommands;


    public AddCommand(ConsoleCommands consoleCommands) {
        setDescription("добавить новый элемент в коллекцию");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager, String... arg) throws JAXBException {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.add((Application.Collection) collection);
        }
    }
}
