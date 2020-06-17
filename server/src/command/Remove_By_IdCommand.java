package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import java.util.HashMap;

public class Remove_By_IdCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public Remove_By_IdCommand(ConsoleCommands consoleCommands) {
        setDescription("удалить элемент из коллекции по его id");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager,
                        String... arg) {
        try {
            consoleCommands.remove_by_id(collection, Integer.parseInt(arg[0]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ArgException();
        }
    }
}
