package command;


import coreSources.*;
import mainPackage.*;


import java.util.HashMap;

public class ExitCommand extends Command {
        private final CommandExecutor commandExecutor;

        public ExitCommand(CommandExecutor commandExecutor){
            this.commandExecutor = commandExecutor;
            setDescription("завершить программу (без сохранения в файл)");
        }

        @Override
        public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager mySwitch, String... arg) {
            return commandExecutor.exit();
        }
    }


