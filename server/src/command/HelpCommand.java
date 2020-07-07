package command;


import coreSources.Answer;
import coreSources.Organization;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.MyLogger;

import java.util.HashMap;

public class HelpCommand extends Command {
    private final CommandExecutor commandExecutor;

    public HelpCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        setDescription("вывести справку по доступным командам");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        MyLogger.info("Выполнение команды Filter");
        return commandExecutor.help(commandMap);
    }

}
