package mainPackage;

import command.Command;
import coreSources.CoreCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandTransform {
    public static ArrayList<CoreCommand> transFormArray(HashMap<String, Command> commandHashMap) {
        ArrayList<CoreCommand> coreCommands = new ArrayList<CoreCommand>();
        for (Map.Entry<String,Command> entry: commandHashMap.entrySet()) {
            CoreCommand coreCommand = new CoreCommand(entry.getKey());
            coreCommand.setNeedArg(entry.getValue().isNeedArg());
            coreCommand.setNeedOrganization(entry.getValue().isNeedOrganization());
            coreCommand.setTypeOfArg(entry.getValue().getTypeOfArg());
            coreCommands.add(coreCommand);
        }
        return coreCommands;
    }
}
