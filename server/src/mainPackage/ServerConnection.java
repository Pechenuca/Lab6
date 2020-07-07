package mainPackage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerConnection {
    private ServerSocketChannel serverSocketChannel;
    private SocketChannel socketChannel;
    private int port;

    public ServerConnection() throws ConnectException {
        try {
            int port = 1337;
            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.configureBlocking(false);
            MyLogger.info("Каналы связи переведены в неблокирующий режим");
        } catch (IOException e) {
            e.printStackTrace();
            MyLogger.error("Соединение не успешно");
            throw new ConnectException("Беды с подлючением");
        }
    }

    public void connect() throws ConnectException {
        try {
            MyLogger.info("Попытка соединения с клиентом");
            socketChannel = serverSocketChannel.accept();
            MyLogger.info("Соединение успешно");
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
            }
        } catch (IOException e) {
            MyLogger.error("Беды с соединением");
            throw new ConnectException("Возникли беды с подключением");
        }
    }

    public void closeSocketChannel() {
        try {
            MyLogger.info("Закрытие сокета");
            socketChannel.close();
            socketChannel = null;
            MyLogger.info("Успех");
        } catch (IOException e) {
            MyLogger.error("Беды с закрытием сокета");
        }
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
