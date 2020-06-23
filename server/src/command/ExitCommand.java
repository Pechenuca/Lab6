package command;


import coreSources.Answer;
import coreSources.Organization;
import mainPackage.Collection;
import mainPackage.CommandExecutor;
import mainPackage.CommandManager;
import mainPackage.Logger;

import java.util.HashMap;

public class ExitCommand extends Command {
        private final CommandExecutor commandExecutor;

        public ExitCommand(CommandExecutor commandExecutor){
            this.commandExecutor = commandExecutor;
            setDescription("завершить программу (без сохранения в файл)");
        }

        @Override
        public Answer execute(HashMap<String, Command> commandMap, Collection collection, CommandManager commandManager,
                              Organization organization, String... arg) {
            Logger.info("Выполнение команды EXIT");
            return commandExecutor.exit();
        }
    }


