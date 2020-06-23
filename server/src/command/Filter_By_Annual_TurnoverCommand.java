package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import java.util.HashMap;

public class Filter_By_Annual_TurnoverCommand extends Command{
    private final CommandExecutor commandExecutor;

    public Filter_By_Annual_TurnoverCommand(CommandExecutor commandExecutor) {
        setDescription("вывести элементы, значение поля annual turnover которых содержит заданную подстроку");
        setArgs(" annual turnover");
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        Logger.info("Выполнение команды FILTER");
        if (arg.length != 1) {
            Logger.error("Беды с выполнением");
            throw new ArgException();

        }
        else {
           return commandExecutor.filterTurnover(collection, arg[0]);
        }
    }
}
