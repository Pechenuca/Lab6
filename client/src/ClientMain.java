import coreSources.Answer;
import coreSources.CoreCommand;
import coreSources.Factory;
import exception.ConnectException;
import exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws ClassNotFoundException, SocketException, UnknownHostException {
        ConnectionManager connectionManager = new ConnectionManager();
        CommandValidation commandValidation = new CommandValidation();
        if (args.length < 2) {
            System.out.println("Syntax: QuoteClient <hostname> <port>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            InetAddress address = InetAddress.getByName(hostname);
            connectionManager.connect(commandValidation);

        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}