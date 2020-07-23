package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerConnection extends Thread {
    DatagramSocket socket = null;
    BufferedReader in = null;
    String str = null;
    byte[] buffer ;
    DatagramPacket packet;
    InetAddress address;
    int port;
    public ServerConnection() throws ConnectException {
        try {
            socket = new DatagramSocket(8800);
            call();
            MyLogger.info("Каналы связи переведены в неблокирующий режим");
        } catch (IOException e) {
            e.printStackTrace();
            MyLogger.error("Соединение не успешно");
            throw new ConnectException("Беды с подлючением");
        }
    }
    public void call() {
        try {
            while (true) {
                buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                if (packet == null) break;
                System.out.println("Request string for sending to client ");

                try {
                    in = new BufferedReader(new InputStreamReader(System.in));

                } catch (Exception e) {
                    System.out.println(e);
                }
                str = in.readLine();
                buffer = str.getBytes();
                address = packet.getAddress();
                port = packet.getPort();
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                /* Посылается датаграммный пакет */
                socket.send(packet);
            }
            /* Закрывается поток и сокет */
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }
}
