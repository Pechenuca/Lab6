package main.command.collectionhandlers;

import main.command.Command;
import main.command.ExecutionContext;

public class ShowCommand extends Command {

    public ShowCommand() {
        commandKey = "show";
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public Object execute(ExecutionContext context) {
        return context.collectionManager().getSerializableList();
    }
}