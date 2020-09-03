package network;

import command.Command;
import command.DescriptorCommand;
import command.ExitCommand;
import command.HelpCommand;

import coreSources.Factory;
import coreSources.Organization;
import exception.NoSuchCommandException;
import managers.CommandManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.IHandlerInput;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class CommandReader {

    private static final Logger LOG = LogManager.getLogger(CommandReader.class);

    private final ClientChannel channel;
    private final IHandlerInput userInputHandler;
    private final CommandManager commandManager;
    private final Factory orgFactory;

    public CommandReader(ClientChannel socket, CommandManager commandManager, IHandlerInput userInput) {
        this.channel = socket;
        this.userInputHandler = userInput;
        this.commandManager = commandManager;
        this.orgFactory = new Factory();
    }

    /**
     * Функция для чтения команд от пользователя
     */
    public void startInteraction() throws IOException, ArrayIndexOutOfBoundsException, NoSuchCommandException, JAXBException {
        String commandStr;
        commandStr = userInputHandler.readWithMessage("Write Command: ");
        Command command = commandManager.getCommand(commandStr);

        if (command instanceof HelpCommand || command instanceof DescriptorCommand)
            userInputHandler.printLn((String) command.execute(null));
        else if (command instanceof ExitCommand)
            finishClient();
        else {
            checkForInputs(command);
            channel.sendCommand(command);
        }
    }
    /**
     * Функция для проверки, нужны ли еще входные данные для отправки команды
     * @param command - команда
     */
    public void checkForInputs(Command command) throws JAXBException {
        if (command.requireOrgInput()) {
            Organization organization = orgFactory.generateOrgByInput(userInputHandler);
            command.addOrgInput(organization);
        }
    }
    /**
     * Функция для отключения клиента
     */
    public void finishClient() {
        LOG.info("Finishing client");
        channel.disconnect();
        System.exit(0);
    }
}