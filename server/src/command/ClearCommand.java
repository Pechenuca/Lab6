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

public class ClearCommand extends Command{
    public final CommandExecutor commandExecutor;

    public ClearCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        setDescription("очистить коллекцию");
    }

    @Override
    public Answer execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) throws JAXBException {
        Logger.info("Выполнение команды clear");
        if (arg.length > 0) {
            Logger.error("Выполнение провалено");
            throw new ArgException();
        }
        else {
           return commandExecutor.clear(collection);
        }
    }
}
