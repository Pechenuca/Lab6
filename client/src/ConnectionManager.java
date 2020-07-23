import coreSources.Answer;
import coreSources.CoreCommand;
import exception.ConnectException;
import mainPackage.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class ConnectionManager {
    private Integer port;
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buffer;
    static DatagramPacket packet;
    private String str, str2;
    static BufferedReader br;

    public ConnectionManager(Integer port, InetAddress address) throws UnknownHostException {
        this.address = InetAddress.getByName("localhost");
        this.setPort(port);
    }

    public void connect(CommandValidation commandValidation) throws ConnectException {
        try {
            socket = new DatagramSocket();
            buffer = new byte[256];
            packet = new DatagramPacket(buffer, buffer.length, address, 8800);
            socket.send(packet);
            MyLogger.info("Sending request ");

            socket.receive(packet);
            str = new String(packet.getData());
            System.out.println("Received message : "+str.trim());
            System.out.println("Do you want continue (Yes/No) : ");
            str2 = br.readLine();
            if(str2.equals("No")) {
                break;
            }

        /* Закрывается объект сокет */
            socket.close();
            System.out.println("Соединение с сервером установлено. Получение списка команд");
            commandValidation.getCommands();
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
        } catch (NullPointerException ignored) {

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
            return (Answer) new DatagramPacket(packet).readObject();
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

    public DatagramSocket getSocket() {
        return socket;
    }


}
