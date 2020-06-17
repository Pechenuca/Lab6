package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class Add_If_MaxCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public Add_If_MaxCommand(ConsoleCommands consoleCommands) {
        setDescription("добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) throws JAXBException {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.addIfMax(collection);
        }
    }
}