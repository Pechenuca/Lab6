import coreSources.Answer;
import coreSources.CoreCommand;
import exception.ConnectException;
import mainPackage.MyLogger;

import java.io.*;
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
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ConnectionManager() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");

    }

    public void connect(CommandValidation commandValidation) throws ConnectException {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            while (true) {

                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                socket.send(request);

                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);

                String quote = new String(buffer, 0, response.getLength());

                System.out.println(quote);
                System.out.println();

                Thread.sleep(10000);
            }
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу!");
            System.out.println("Желаете попробовать еще раз? Да/Нет");
            Scanner scanner = new Scanner(System.in);
            if ("Да".equals(scanner.nextLine())) {
                connect(commandValidation);
            }
            throw new ConnectException();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutput oo = new ObjectOutputStream(byteArrayOutputStream);
            oo.writeObject(coreCommand);
            oo.close();
            byte[] serializedMessage = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            System.out.println("Возникли беды с отправкой команды");
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
