package mainPackage;

import coreSources.CoreCommand;
import coreSources.Factory;
import exception.ServerValidateException;

import java.util.ArrayList;

public class ServerCommandValidation {
    ArrayList<CoreCommand> coreCommands;

    Factory factory = new Factory();

    boolean CheckOrganization(String commandName) {
        for(CoreCommand coreCommand1 : coreCommands) {
            if(commandName.equals(coreCommand1.getName())) {
                return coreCommand1.isNeedOrganization();
            }
        }
        throw new Serve
    }

    void validateCommand(CoreCommand coreCommand, ArrayList<CoreCommand> coreCommands) {
        boolean isValidate = false;
        try {
            for(CoreCommand coreCommand1 : coreCommands) {
                if(coreCommand1.getName().equals((coreCommand.getName()))) {
                    isValidate = true;
                    if(coreCommand1.isNeedArg()) {
                        switch (coreCommand1.getTypeOfArg().toLowerCase().trim()) {
                            case "float":
                                Float.parseFloat(coreCommand.getArg());
                                break;
                            case "int":
                                Integer.parseInt(coreCommand.getArg());
                                break;
                            case "double":
                                Double.parseDouble(coreCommand.getArg());
                                break;
                            case "long":
                                Long.parseLong(coreCommand.getArg());
                                break;

                        }
                    }else if(coreCommand.getArg()!=null) {
                        throw new ServerValidateException();
                    }
                }
                if(coreCommand1.isNeedOrganization() &&
                        !ServerOrganizationValidation.ValidateOrganization(coreCommand.getOrganization())) {

                }
            }

        }
    }
}
