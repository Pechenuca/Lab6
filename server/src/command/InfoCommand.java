package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import java.util.HashMap;

public class InfoCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public InfoCommand(ConsoleCommands consoleCommands){
        setDescription("вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.info((Application.Collection) collection);
        }
    }
}