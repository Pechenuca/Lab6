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

public class UpdateCommand extends Command {
    private final CommandExecutor commandExecutor;

    public UpdateCommand(CommandExecutor commandExecutor, CommandExecutor commandExecutor1) {
        this.commandExecutor = commandExecutor1;
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
        setArgs(" id");
        setNeedOrg(true);
        setNeedArg(true);
        setTypeOfArg("int");

    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        Logger.info("Выполнение команды UPDATE");
        try {
           return commandExecutor.update(collection, Integer.parseInt(arg[0]), organization);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException | JAXBException e){
            Logger.error("Беды с выполнением");
            throw new ArgException();
        }

    }
}