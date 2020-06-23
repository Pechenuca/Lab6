package command;


import coreSources.Answer;
import coreSources.Organization;
import exception.ArgException;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import java.util.HashMap;

public class Count_Greater_Than_Official_AddressCommand extends Command {
    private final CommandExecutor commandExecutor;

    public Count_Greater_Than_Official_AddressCommand(CommandExecutor commandExecutor, CommandExecutor commandExecutor1) {
        this.commandExecutor = commandExecutor1;
        setDescription("вывести элементы, значение поля official address которых больше заданного");
        setArgs(" official address");
    }

    @Override
    public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) {
        Logger.info("Выполнение команды COUNT GREATER THAN ADDRESS");
        if (arg.length != 1) {
            Logger.error("Беды с выполнением");
            throw new ArgException();
        }
        else {
            return commandExecutor.count_greater_than_official_address(collection,arg[0]);
        }
    }
}