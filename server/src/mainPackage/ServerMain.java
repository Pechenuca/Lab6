package mainPackage;

import coreSources.Answer;
import exception.ServerValidateException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.ConnectException;

public class ServerMain {
    public static void main(String[] args) throws ConnectException {
        ServerConnection serverConnection = new ServerConnection();
        CommandReader commandReader = new CommandReader();
        CommandProcessing commandProcessing = new CommandProcessing();
        commandProcessing.registerServerCommands();
        Answer answer;
        AnswerSending answerSending = new AnswerSending();
        ServerCommandValidation serverCommandValidation = new ServerCommandValidation();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            MyLogger.info("Экстренное завершнение работы, сохраняю коллекцию");
            commandProcessing.commandExecution.save(commandProcessing.getCollection());
        }));
        serverConnection.connect();
        while (true) {
            int i = 0;
            while (serverConnection.getSocketChannel()!=null) {
                try {
                    commandReader.setCoreCommand(null);
                    if(i == 0) {
                        commandReader.sendToClient(CommandTransform.transFormArray(commandProcessing.getServerCommands()), serverConnection.getSocketChannel());
                        i++;
                    }
                    commandReader.getCommandFromClient(serverConnection);
                    if(commandReader.getCoreCommand()!=null) {
                        try {
                            serverCommandValidation.validateCommand(commandReader.getCoreCommand(), CommandTransform.transFormArray(commandProcessing.getServerCommands()));
                            answer = commandProcessing.processCommand(commandReader.getCoreCommand());
                            commandReader.sendToClient(answer, serverConnection.getSocketChannel());
                            serverConnection.closeSocketChannel();
                        } catch (ServerValidateException | JAXBException e) {
                            commandReader.sendToClient(new Answer("Команда не прошла серверную валидацию"), serverConnection.getSocketChannel());
                        }
                    }
                    //      answerSending.SendAnswer(commandReader,answer,serverConnection.getSocketChannel());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Ожидается команда от клиента.");
                }
            }
            while (serverConnection.getSocketChannel()==null){
                try {
                    MyLogger.info("Ожидаем подключение клиента");
                    Thread.sleep(3000);
                }catch (InterruptedException e){

                }
                serverConnection.connect();
            }

        }


    }
}
