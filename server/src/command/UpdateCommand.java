package command;

import Application.ArgException;
import Application.Collection;
import Application.CommandManager;
import Application.ConsoleCommands;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

public class UpdateCommand extends Command {
    private final ConsoleCommands consoleCommands;

    public UpdateCommand(ConsoleCommands consoleCommands) {
        setDescription("обновить значение элемента коллекции, id которого равен заданному");
        setArgs(" id");
        this.consoleCommands = consoleCommands;
    }

    @Override
    public void execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
        try {
            consoleCommands.update((Application.Collection) collection,Integer.parseInt(arg[0]));
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException | JAXBException e){
            throw new ArgException();
        }

    }
}