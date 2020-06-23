package command;

import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;

public class Script extends Command {
    private final CommandExecutor commandExecutor;

    public Script(CommandExecutor commandExecutor, CommandExecutor commandExecutor1) {
        this.commandExecutor = commandExecutor1;
        setDescription("Добавить новый элемент в коллецию");
        setArgs(" fileName");
        setNeedArg(true);
        setTypeOfArg("string");
        setNeedOrg(false);

    }

    @Override
    public Answer execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) throws JAXBException {
        String path;
        String filePath = System.getenv("INPUT_PATH");
        Logger.info("Выполнение команды SCRIPT");
        if(filePath == null) {
            path =  "server\\src\\Files\\";
        } else {
            path = filePath;
        }
        if (arg.length != 1 ) {
            throw new ArgException();
        }

        else {
            File file = new File(path + arg[0]);
            return commandExecutor.executeScript(file, commandManager, collection);
        }
    }
}
