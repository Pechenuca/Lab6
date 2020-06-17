package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import java.util.HashMap;

public class ShowCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public ShowCommand(ConsoleCommands consoleCommands){
        setDescription("вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.show((Application.Collection) collection);
        }
    }
}