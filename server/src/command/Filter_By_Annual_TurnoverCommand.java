package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;

import java.util.HashMap;

public class Filter_By_Annual_TurnoverCommand extends Command{
    private final CommandExecutor commandExecutor;

    public Filter_By_Annual_TurnoverCommand(CommandExecutor commandExecutor) {
        setDescription("вывести элементы, значение поля annual turnover которых содержит заданную подстроку");
        setArgs(" annual turnover");
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, Organization organization, String... arg) {
        if (arg.length != 1) throw new ArgException();
        else {
           return commandExecutor();
        }
    }
}
