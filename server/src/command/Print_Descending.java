package command;

import Application.*;

import java.util.HashMap;


public class Print_Descending extends Command {
    private final ConsoleCommands consoleCommands;

    public Print_Descending(ConsoleCommands consoleCommands) {
        setDescription("вывести значения поля в порядке возрастания");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        if (arg.length > 0) throw new ArgException();
        else {
            consoleCommands.print_descending(collection);
        }
    }
}
