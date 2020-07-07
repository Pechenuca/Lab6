package command;

import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.MyLogger;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class UpdateCommand extends Command {
    private final CommandExecutor commandExecutor;

    public UpdateCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
        setArgs(" id");
        setNeedOrg(true);
        setNeedArg(true);
        setTypeOfArg("int");

    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        MyLogger.info("Выполнение команды UPDATE");
        try {
           return commandExecutor.update(collection, Integer.parseInt(arg[0]), organization);
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException | JAXBException e){
            MyLogger.error("Беды с выполнением");
            throw new ArgException();
        }

    }
}