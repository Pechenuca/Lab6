import coreSources.AbstractCommand;
import coreSources.CoreCommand;
import coreSources.Factory;
import coreSources.Organization;
import exception.ValidationException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class CommandValidation {
    List<AbstractCommand> coreCommands;

    Factory factory = new Factory();

    void getCommands(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        coreCommands = (List<AbstractCommand> ) objectInputStream.readObject();
    }
    CoreCommand createCommand(String com) throws ValidationException, JAXBException {
        Organization organization = null;
        String[] com1 = com.trim().toLowerCase().split(" ");
        if (com1.length>1 && CheckWorker(com1[0])){
            CoreCommand coreCommand = new CoreCommand(com1[0],com1[1],factory.createOrganization());
            validateCommand(coreCommand);
            return coreCommand;
        } else if (com1.length>1){
            CoreCommand coreCommand = new CoreCommand(com1[0],com1[1]);
            validateCommand(coreCommand);
            return coreCommand;
        } else if (CheckWorker(com1[0])){
            CoreCommand coreCommand = new CoreCommand(com1[0],factory.createOrganization());
            validateCommand(coreCommand);
            return coreCommand;
        } else {
            CoreCommand coreCommand = new CoreCommand(com1[0]);
            validateCommand(coreCommand);
            return coreCommand;
        }
    }
    boolean CheckWorker(String commandName){
        for (AbstractCommand abstractCommand : coreCommands){
            if (commandName.equals(abstractCommand.getName())){
                return abstractCommand.isNeedOrganization();
            }
        }
        throw new ValidationException();
    }

    void validateCommand(CoreCommand coreCommand){
        boolean isValidate=false;
        try {
            for (AbstractCommand command : coreCommands) {
                if (command.getName().equals(coreCommand.getName())) {
                    isValidate = true;
                    if (command.isNeedArg()) {
                        switch (command.getTypeOfArg().toLowerCase().trim()) {
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

                    }else if(coreCommand.getArg()!=null){
                        throw new ValidationException();
                    }
                    if (command.isNeedOrganization() && !OrganizationValidation.ValidateOrganization(coreCommand.getOrganization())){
                        throw  new ValidationException();
                    }else if(!command.isNeedOrganization() && coreCommand.getOrganization()!=null){
                        throw new ValidationException();
                    }
                }
            }
            if (!isValidate) throw new ValidationException();
        }catch (NumberFormatException e){
            throw new ValidationException();
        }
    }
}