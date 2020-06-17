package command;

import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import java.util.HashMap;

public class HelpCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public HelpCommand(ConsoleCommands consoleCommands){
        setDescription("вывести справку по доступным командам");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        consoleCommands.help(commandMap);
    }
}