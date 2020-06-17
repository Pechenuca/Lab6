package mainPackage;

import command.Command;
import coreSources.Answer;
import coreSources.Organization;


import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Scanner;


public class CommandManager {
    private final HashMap<String, Command> commandHashMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandHashMap.put(commandName, command);

    }
    Scanner scanner;

    public Answer execute(String commandName, HashMap<String, Command> commandHashMap, Collection collection, Organization organization, String... args) throws JAXBException {
        if (!commandName.equals("")) {
            Command command = commandHashMap.get(commandName);
            if (command == null) {
                throw new IllegalStateException("no command registered for " + commandName);
            }
        }
        return null;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public HashMap<String, Command> getCommandHashMap() {
        return commandHashMap;
    }


}

