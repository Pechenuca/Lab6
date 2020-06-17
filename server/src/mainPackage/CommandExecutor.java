package mainPackage;

import command.Command;
import exception.ArgException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CommandExecutor {

    public void help(HashMap<String, Command> commandHashMap) {
        System.out.println("Справка:");
        for (Map.Entry<String, Command> entry : commandHashMap.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue().getArgs() + ": " + entry.getValue().getDescription());
        }
    }

    public void exit() {
        System.out.println("Таки до новых встреч!");
    }

    public void add(Collection collection) throws JAXBException {
        collection.add();
    }

    public void info(Collection collection) {
        System.out.println(collection.getInfo());
    }

    public void show(Collection collection) {
        collection.show();
    }

    public void remove_by_id(Collection collection, int id) {
        collection.remove_by_id(id);
    }

    public void update(Collection collection, int id) throws JAXBException {
        collection.replace(id);
    }

    public void clear(Collection collection) {
        collection.clear();
    }

    public void remove_first(Collection collection) {
        collection.remove_first();
    }

    public void count_greater_than_official_address() {

    }

    public void remove_grater(Collection collection) throws JAXBException {
        collection.remove_greater();
    }

    public void executeScript(File file, CommandManager mySwitch, Collection myCollection) {
        try {
            Scanner scanner = new Scanner(file);
            String line = "";
            while (!line.equals("exit") && scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] lines = line.split(" ");
                try {
                    String command = lines[0];

                    if (lines.length > 1) {
                        String arg = lines[1];
                        try {
                            mySwitch.execute(command, mySwitch.getCommandHashMap(), myCollection, arg);
                        } catch (IllegalStateException e) {
                            System.out.println("Вы ввели неправильную команду, попробуйте снова!");
                        } catch (ArgException e) {
                            System.out.println(e.getMessage());
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            mySwitch.execute(command, mySwitch.getCommandHashMap(), myCollection);
                        } catch (IllegalStateException e) {
                            System.out.println("Вы ввели неправильную команду, попробуйте снова!");
                        } catch (ArgException e) {
                            System.out.println(e.getMessage());
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Вы ввели непрвильную команду, попробуйте снова");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Скрипт не найден.");
        }
    }


    public void count_greater_than_official_address(Collection collection, String arg) {
        try {
            int official_address = Integer.parseInt(arg);
            System.out.println("На данный момент мы имеем " + collection.count_greater_than_official_address(official_address) + " рабочих чья " +
                    "зарплата выше " + official_address + " рублей.");
        } catch (NumberFormatException e) {
            throw new ArgException();
        }
    }

    public void save(Collection collection) {
        collection.save();
        System.out.println("Коллекция успешно сохранена");
    }

    public void addIfMax(Collection collection) throws JAXBException {
        if (collection.addIfMax()) {
            System.out.println("Организация успешно добавлена");
        } else {
            System.out.println("Значение организации меньше максимального!");
        }
    }

    public void removeGreater(Collection collection) throws JAXBException {
        collection.remove_greater();

    }


    public void print_descending(Collection collection) {
        collection.print_descending();
    }


    public void filterTurnover(Collection collection, String arg) {
        for (Organization organization : collection.getCollection()) {
            if (organization.getAnnualTurnover().equals(arg)) {
                System.out.println(organization.toString());
            }
        }
    }

    public void remove(Collection collection, String name) {
        collection.remove(name);
    }
}



