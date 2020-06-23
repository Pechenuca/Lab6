package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import java.util.HashMap;

public class Remove_By_IdCommand extends Command {
    private final CommandExecutor commandExecutor;

    public Remove_By_IdCommand(CommandExecutor commandExecutor, CommandExecutor commandExecutor1) {
        this.commandExecutor = commandExecutor1;
        setDescription("удалить элемент из коллекции по его id");

    }

    @Override
    public Answer execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        Logger.info("Выполнение команды REMOVE_BY_ID");
        try {
            return commandExecutor.remove_by_id(collection, Integer.parseInt(arg[0]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ArgException();
        }
    }
}
