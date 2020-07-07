package command;

import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.MyLogger;

import java.util.HashMap;

public class InfoCommand extends Command {
    private final CommandExecutor commandExecutor;

    public InfoCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        setDescription("вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)");
        setNeedOrg(false);
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        MyLogger.info("Выполнение команды INFO");
        if (arg.length > 0) {
            MyLogger.error("Беды с выполнением");
            throw new ArgException();
        } else {
            return commandExecutor.info(collection);
        }
    }
}