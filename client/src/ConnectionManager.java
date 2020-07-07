import coreSources.Answer;
import coreSources.CoreCommand;
import exception.ConnectException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionManager {
    InetAddress address;
    private Integer port;
    Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ConnectionManager(Integer port, InetAddress address) {
        this.address = address;
        this.setPort(port);
    }

    public void connect(CommandValidation commandValidation) throws ConnectException {
        try {
            socket = new Socket(address, getPort());
            setObjectInputStream(new ObjectInputStream(socket.getInputStream()));
            System.out.println("Соединение с сервером установлено. Получение списка команд");
            commandValidation.getCommands(getObjectInputStream());
            System.out.println("Команды с сервера получены");
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу!");
            System.out.println("Желаете попробовать еще раз? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            if ("Да".equals(scanner.nextLine())) {
                connect(commandValidation);
            }
            throw new ConnectException();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось получить список команд");
            System.out.println("Желаете попробовать еще раз? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            if ("Да".equals(scanner.nextLine())) {
                connect(commandValidation);
            }

        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException | NullPointerException ignored) {

        }
    }

    public void sendCommand(CoreCommand coreCommand) {
        try {
            setObjectOutputStream(new ObjectOutputStream(socket.getOutputStream()));
            objectOutputStream.writeObject(coreCommand);
        } catch (IOException e) {
            System.out.println("Возникли беды с отправкой команды");
        }
    }
    public Answer getAnswerFromServer() {
        try {
            return (Answer) new ObjectInputStream(socket.getInputStream()).readObject();
        } catch (IOException  | ClassNotFoundException e) {
            return null;
        }
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
}
