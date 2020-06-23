package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import java.util.HashMap;


public class Print_Descending extends Command {
    private final CommandExecutor commandExecutor;

    public Print_Descending(CommandExecutor commandExecutor, CommandExecutor commandExecutor1) {
        this.commandExecutor = commandExecutor1;
        setDescription("вывести значения поля в порядке возрастания");

    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        Logger.info("Выполнение команды PRINT_DESCENDING");
        if (arg.length > 0)
            throw new ArgException();
        else {
            return commandExecutor.print_descending(collection);
        }
    }
}
