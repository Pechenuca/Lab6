package command.collectionhandlers;

import command.Command;
import command.ExecutionContext;

import java.io.IOException;

public class ClearCommand extends Command {

    public ClearCommand() {
        commandKey = "clear";
        description = "очистить коллекцию";
    }

    @Override
    public Object execute(ExecutionContext context) throws IOException {
        context.collectionManager().clear();
        return "All elements deleted successfully!";
    }
}