import coreSources.Answer;
import coreSources.CoreCommand;
import coreSources.Factory;
import exception.ConnectException;
import exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws ClassNotFoundException {

        Logger log = LoggerFactory.getLogger("My logger");
        Factory factory = new Factory();
        CommandValidation commandValidation = new CommandValidation();
        Scanner scanner = new Scanner(System.in);
        try {
            ConnectionManager connectManager =
                    new ConnectionManager(8800, InetAddress.getLocalHost());
            String command = "";
            while (!command.trim().toLowerCase().equals("exit")) {
                try {
                    System.out.println("Введите команду");
                    command = scanner.nextLine();
                    connectManager.connect(commandValidation);
                    CoreCommand coreCommand = commandValidation.createCommand(command);
                    connectManager.sendCommand(coreCommand);
                    Answer answer = connectManager.getAnswerFromServer();
                    if (answer != null) {
                        answer.sort();
                    }
                    System.out.println(answer);
                } catch (ConnectException | JAXBException e) {
                    e.printStackTrace();
                } catch (ValidationException e) {
                    System.out.println("Не удалось подключиться к серверу.");
                }finally {
                    connectManager.disconnect();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
