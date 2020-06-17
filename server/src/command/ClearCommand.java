package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class ClearCommand extends Command{
    public final ConsoleCommands consoleCommands;

    public ClearCommand(ConsoleCommands consoleCommands) {
        setDescription("очистить коллекцию");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager, String... arg) throws JAXBException {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.clear((Application.Collection) collection);
        }
    }
}
