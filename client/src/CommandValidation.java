import coreSources.AbstractCommand;
import coreSources.CoreCommand;
import coreSources.Factory;
import coreSources.Organization;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class CommandValidation {
    List<AbstractCommand> coreCommands;

    Factory factory = new Factory();

    void getCommands(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        coreCommands = (List<AbstractCommand>) objectInputStream.readObject();
    }
    CoreCommand createCommand(String command) throws JAXBException {
        Organization organization = null;
        String[] command1 = command.trim().toLowerCase().split(" ");
        if(command1.length>1 && CheckOrganization(command1[0], command1[1], factory.createOrganization())){
            CoreCommand        }
    }
}
