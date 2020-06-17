package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import java.util.HashMap;

public class Count_Greater_Than_Official_AddressCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public Count_Greater_Than_Official_AddressCommand(ConsoleCommands consoleCommands) {
        setDescription("вывести элементы, значение поля official address которых больше заданного");
        setArgs(" official address");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        if (arg.length != 1) throw new ArgException();
        else {
            consoleCommands.count_greater_than_official_address(collection,arg[0]);
        }
    }
}