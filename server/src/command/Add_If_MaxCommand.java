package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class Add_If_MaxCommand extends Command {
    private final CommandExecutor commandExecutor;

    public Add_If_MaxCommand(CommandExecutor commandExecutor) {
        setDescription("добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.commandExecutor = commandExecutor;
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                           Organization organization, String... arg) throws JAXBException {
        Logger.info("Выполнение команды ADD_IF_MAX");
        if (arg.length > 0) throw new ArgException();
        else {
            return commandExecutor.addIfMax(collection, organization);
        }
    }
}