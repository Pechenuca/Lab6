package mainPackage;

import command.Command;
import coreSources.Answer;
import coreSources.Factory;
import coreSources.Organization;
import exception.ArgException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandExecutor {

    public Answer help(HashMap<String, Command> commandHashMap) {
        String answer = "Справка:";

        for (Map.Entry<String, Command> entry : commandHashMap.entrySet()) {
            answer += entry.getKey() + entry.getValue().getArgs() + ": " + entry.getValue().getDescription();
        }
        Logger.info("Выполнение успешно");
        return new Answer(answer);
    }

    public Answer exit() {
        Logger.info("Выполнение успешно");
        return new Answer("Таки до новых встреч!");
    }

    public Answer add(Collection collection, Organization organization) throws JAXBException {
        if (collection.add(organization)) {
            Logger.info("Выполнение успешно");
            return new Answer("Организация добавлена");
        } else {
            Logger.error("Беды с выполнением");
            return new Answer("Не удалось добавить организацию");
        }
    }

    public Answer info(Collection collection) {
        Logger.info("Выполнение успешо");
       return new Answer(collection.getInfo());
    }

    public Answer show(Collection collection) {
        Logger.info("Выполнение успешно");
        return collection.show();
    }

    public Answer remove_by_id(Collection collection, int id) {
        Logger.info("Выполнение успешно");
        return collection.remove_by_id(id);
    }

    public Answer update(Collection collection, int id, Organization organization) throws JAXBException {
        Logger.info("Выполнение успешно");
        return collection.replace(id, organization);
    }

    public Answer clear(Collection collection) {
        collection.clear();
        return new Answer("Коллекция пуста");
    }

    public Answer remove_first(Collection collection) {
        Logger.info("Выполнение успешно");
        return collection.remove_first();
    }


    public Answer remove_grater(Collection collection, Organization organization) throws JAXBException {
        Logger.info("Выполнение успешно");
        return new Answer("Объекты удалены");
    }

    public Answer executeScript(File file, CommandManager commandManager, Collection myCollection) {
        StringBuilder answer = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            InputStream inputStream = new FileInputStream(file);
            Factory factory = new Factory();

            factory.update(inputStream);
            String line = "";
            int counter = 0;
            while (!line.equals("exit") && scanner.hasNextLine()) {
                line = scanner.nextLine();
                counter++;
                String[] lines = line.split(" ");
                try {

                    String command = lines[0];

                    if (lines.length > 1) {
                        String arg = lines[1];
                        try {
                            answer.append(commandManager.execute(command, commandManager.getCommandHashMap(), myCollection,
                                    null, arg));
                        } catch (IllegalStateException e) {
                            System.out.println("Вы ввели неправильную команду, попробуйте снова!");
                        } catch (ArgException e) {
                            System.out.println(e.getMessage());
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            answer.append(commandManager.execute(command, commandManager.getCommandHashMap(), myCollection,
                                    null));
                        } catch (IllegalStateException e) {
                            System.out.println("Вы ввели неправильную команду, попробуйте снова!");
                        } catch (ArgException e) {
                            System.out.println(e.getMessage());
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return new Answer("Вы ввели непрвильную команду, попробуйте снова");
                }
            }
        } catch (FileNotFoundException e) {
            return new Answer("Скрипт не найден");
        }
        Logger.info("Выполнение успешно");
        return new Answer(answer + "Скрипт успешно выполнен");
    }


    public Answer count_greater_than_official_address(Collection collection, String arg) {
        try {
            int official_address = Integer.parseInt(arg);
            Logger.info("Выполнение успешно");
            return new Answer("На данный момент мы имеем " + collection.count_greater_than_official_address(official_address)
                    + " организаций, чей " +
                    "адрес больше " + official_address + " букв");
        } catch (NumberFormatException e) {
            Logger.error("Беды с выполнением");
            throw new ArgException();
        }
    }


    public Answer addIfMax(Collection collection, Organization organization) throws JAXBException {
        if (collection.addIfMax(organization)) {
            Logger.info("Выполнение успешно");
            return new Answer("Организация успешно добавлена");
        } else {
            Logger.info("Выполнение успешно");
            return new Answer("Значение организации меньше максимального!");
        }
    }

    public Answer removeGreater(Collection collection, Organization organization) throws JAXBException {
        Logger.info("Выполнение успешно");
        return collection.remove_greater(organization);
    }


    public Answer print_descending(Collection collection) {
        Logger.info("Выполнение успешно");
        return collection.print_descending(collection);

    }


    public Answer filterTurnover(Collection collection, String arg) {
        StringBuilder answer = new StringBuilder();
        for (Organization organization : collection.getCollection()) {
            if (organization.getAnnualTurnover().equals(arg)) {
                answer.append(organization.toString());
            }
        }
        Logger.info("Выполнение успешно");
        return new Answer(answer.toString());
    }

    public void remove(Collection collection, String name) {
        collection.remove(name);
    }
}



