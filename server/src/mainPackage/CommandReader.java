package mainPackage;

import coreSources.CoreCommand;

import java.io.*;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CommandReader {
    private CoreCommand coreCommand;
    public void getCommandFromClient(ServerConnection serverConnection) throws ConnectException {
        Logger.info("Получение команды от клиента");
        try{
            Thread.sleep(1000);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteBuffer byteBuffer = ByteBuffer.allocate(65536000);
            int n=0;
            int i=0;
            while((n=serverConnection.getSocketChannel().read(byteBuffer))>0);
            {
                i++;
                Logger.info("Заполнение буфера номер - " + i);
                byteBuffer.flip();
                byteArrayOutputStream.write(byteBuffer.array(), 0, n);
                byteBuffer.clear();
            }
        if(i!=0) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream((byteArrayInputStream));
            setCoreCommand((CoreCommand) objectInputStream.readObject());
            Logger.info("Выполнение успешно");
        }
        } catch (InterruptedException e) {
            Logger.error("Не удалось поспать *Как и мне, когда я писал это(((*");
        }
        catch (ClassNotFoundException e) {
            Logger.error("Возникли беды с распознаванием класса команды");
            throw new ConnectException("Класс не найден... Беда(");
        }
        catch (IOException e) {
            e.printStackTrace();
            Logger.error("Не удалось получить команду");
            serverConnection.closeSocketChannel();
            throw new ConnectException("Возникли беды с чтением команды с клиента");
        }
    }

    public void sendToClient(Object object, SocketChannel socketChannel) throws IOException {
        Logger.info("Отправка клиенту");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] out = byteArrayOutputStream.toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.wrap(out);
        if(socketChannel.isConnected() && byteBuffer.limit()!=0) {
            socketChannel.write(byteBuffer);
        }
        byteBuffer.clear();
        byteArrayOutputStream.flush();
        objectOutputStream.flush();
        Logger.info("Успех");
    }

    public CoreCommand getCoreCommand() {
        return coreCommand;
    }

    public void setCoreCommand(CoreCommand coreCommand) {
        this.coreCommand = coreCommand;
    }
}
