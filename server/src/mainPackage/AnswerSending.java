package mainPackage;

import coreSources.Answer;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.channels.SocketChannel;

public class AnswerSending {
    public void SendAnswer(CommandReader commandReader, Answer answer, SocketChannel socketChannel) throws ConnectException {
        Logger.info("Answer sending");
        try {
            commandReader.sendToClient(answer, socketChannel);
            Logger.info("Answer was sent successfully");
        } catch (IOException e) {
            Logger.error("Otvet ne udalos' otpravit' ");
            throw new ConnectException("Ошибка при отправке ответа клиенту");
        }
    }
}