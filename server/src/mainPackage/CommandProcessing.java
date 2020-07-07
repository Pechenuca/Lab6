package mainPackage;

import command.*;
import coreSources.Answer;
import coreSources.CoreCommand;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;

public class CommandProcessing {
    private Command command;
    private CoreCommand coreCommand;
    private static final String DEFAULT_PATH = "server\\src\\file\\file.xml";
    CommandExecutor commandExecution = new CommandExecutor();
    CommandManager commandManager = new CommandManager();
    File collectionFile;
    Collection collection;
    public CommandProcessing(){
        String filePath = System.getenv("INPUT_PATH");
        if(filePath == null){
            filePath = DEFAULT_PATH;
        }
        collectionFile = new File(filePath);
        collection = new Collection(collectionFile);
    }
    public Answer processCommand(CoreCommand coreCommand) throws JAXBException {
        MyLogger.info("Обработка команд на сервере");
        if(coreCommand.getArg()==null) {
            return commandManager.execute(coreCommand.getName(), commandManager.getCommandHashMap(), collection, coreCommand.getOrganization());
        }else{
            return commandManager.execute(coreCommand.getName(), commandManager.getCommandHashMap(), collection, coreCommand.getOrganization(),coreCommand.getArg());
        }
    }
    public void registerServerCommands(){
        MyLogger.info("Регистрация команд на сервере");
        Command filter = new Filter_By_Annual_TurnoverCommand(commandExecution);
        Command help = new HelpCommand(commandExecution);
        Command info = new InfoCommand(commandExecution);
        Command exit = new ExitCommand(commandExecution);
        Command add = new AddCommand(commandExecution);
        Command show = new ShowCommand(commandExecution);
        Command remove = new Remove_By_IdCommand(commandExecution);
        Command update = new UpdateCommand(commandExecution);
        Command clear = new ClearCommand(commandExecution);
        Command addIfMax = new Add_If_MaxCommand(commandExecution);
        Command removeGreater = new RemoveGreaterCommand(commandExecution);
        Command removeLower = new Remove_By_IdCommand(commandExecution);
        Command countGreaterThanSalary = new Count_Greater_Than_Official_AddressCommand(commandExecution);
        Command printfields = new Print_Descending(commandExecution);
        Command execute = new Script(commandExecution);

        commandManager.register("filter_contains_name", filter);
        commandManager.register("execute_script", execute);
        commandManager.register("count_greater_than_salary", countGreaterThanSalary);
        commandManager.register("print_field_ascending_person", printfields);
        commandManager.register("help", help);
        commandManager.register("info", info);
        commandManager.register("exit", exit);
        commandManager.register("add", add);
        commandManager.register("show", show);
        commandManager.register("update", update);
        commandManager.register("remove_by_id", remove);
        commandManager.register("clear", clear);
        commandManager.register("add_if_max", addIfMax);
        commandManager.register("remove_greater", removeGreater);
        commandManager.register("remove_lower", removeLower);


    }

    public Collection getCollection() {
        return collection;
    }

    public HashMap<String,Command> getServerCommands(){
        return commandManager.getCommandHashMap();
    }
    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public CoreCommand getCoreCommand() {
        return coreCommand;
    }

    public void setCoreCommand(CoreCommand coreCommand) {
        this.coreCommand = coreCommand;
    }
}
