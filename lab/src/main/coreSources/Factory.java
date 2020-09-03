package main.coreSources;

import main.util.IHandlerInput;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class Factory implements Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null
    private Double x;
    private long y;

    Scanner scanner = new Scanner(System.in);

    public Organization generateOrgByInput(IHandlerInput userInputHandler) throws JAXBException {
        System.out.println("Я вас категорически приветствую! Если ты все же решил использовать мое недо-проект, то для " +
                "начала введи данные о нужной тебе организации: ");
        System.out.println("Я тебя предупреждаю, выбери другой проект\n Введите имя организации");
        setName();
        System.out.println("Введи координаты, надеюсь, что это не те координаты, о которых я подумал :/ " +
                "\n Начнем с X");
        setX();
        System.out.println("А чё по Y?");
        setY();
        System.out.println();
        setFullName();
        setAnnualTurnover();
        setOfficialAddress();
        return new Organization(id, name, x, y, coordinates, creationDate, annualTurnover,
                fullName, type, officialAddress);
    }

    public void update(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public void setName() {
        String line = scanner.nextLine();
        try {
            Double d1 = new Double(line);
            System.out.println("Некорректное название!");
            setName();


        } catch (NumberFormatException e) {
            if (line.length() >= 3 && line.length() <= 80)
                name = line;
            else {
                System.out.println("Слишком короткое название");
                setName();
            }

        }
    }


    public void setX() {
        String line = scanner.nextLine();
        try {
            double parseDouble = Double.parseDouble(line);
            x = parseDouble;
            if (x == null) {
                System.out.println("Опа-чиньчопа, ты попал в ловушку Нулла, поле не должно быть null");
                setX();
            }
        } catch (NumberFormatException e) {
            System.out.println("Ты чет не то пишешь, подумой и напиши нормально");
            setX();
        }
    }

    public void setY() {
        String line = scanner.nextLine();
        try {
            long parseLong = Long.parseLong(line);
            y = parseLong;

        } catch (NumberFormatException e) {
            System.out.println("Чет ты совсем рукожоп, чиселки ввести нормально не можешь(");
            setY();
        }
    }

    public void setFullName() {
        System.out.println("Я не знаю зачем это надо, но введи еще полное имя:");
        String line = scanner.nextLine();
        try {
            if (line.length() >= 5 && line.length() <= 80)
                fullName = line;
            else {
                System.out.println("Я загуглил, нет имени такой длины");
                setFullName();

            }
        } catch (NumberFormatException e) {

            System.out.println("Error");

        }

    }

    public void setAnnualTurnover() {
        System.out.println("Абрам, таки самое время посчитать шекели твоей организации: ");
        String line = scanner.nextLine();
        try {
            long parseLong2 = Long.parseLong(line);
            if (line == null) {
                System.out.println("Абрам, таки не будьте гоем, не смеши мои пейсы - я знаю," +
                        " что у тебя есть какой-никакой годовой оборот");
                setAnnualTurnover();
            }
            if (parseLong2 == 0) {
                System.out.println("Абрам, я начинаю злиться, ты таки уже давно не работаешь " +
                        "в Федеральной налоговой службе - не надо тут с цифрами махинации проводить");
                setAnnualTurnover();
            }
            if (parseLong2 > 0)
                annualTurnover = parseLong2;
        } catch (NumberFormatException e) {
            System.out.println("Абрам, ты самый настойщий поц, выйди и зайди нормально");
            setAnnualTurnover();

        }

    }

    public void setOfficialAddress() {

        System.out.println("Введите адрес");
        String line = scanner.nextLine();
        try {
            if (line == null) {
                System.out.print("Так просто не сломать!");
                setOfficialAddress();
            }
            if (line.length() < 5) {
                System.out.println("Такого адреса не может быть!");
                setOfficialAddress();
            }
        } catch (NullPointerException e) {
            officialAddress.setStreet(line);
        }


    }
    /*
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null
    private Double x;
    private long y;
    (id, name, x, y, coordinates, creationDate, annualTurnover,
                fullName, type, officialAddress);
     */
    public Organization generateFromScript(String[] inputs) {
        try {
            Integer id = (Integer) getValueOf(Integer.class, inputs[0]);
            String name = (String) getValueOf(String.class, inputs[1]);
            Double x = (Double) getValueOf(Double.class, inputs[2]);
            Long y = (Long) getValueOf(Long.class, inputs[3]);
            Coordinates coordinates = new Coordinates(x, y);
            ZonedDateTime creationTime = (ZonedDateTime) getValueOf(ZonedDateTime.class, inputs[4]);
            Long annualTurnover = (Long) getValueOf(Long.class, inputs[5]);
            String fullName = (String) getValueOf(String.class, inputs[6]);
            OrganizationType type = (OrganizationType) getValueOf(OrganizationType.class, inputs[7]);
            Address officialAddress = (Address) getValueOf(Address.class, inputs[8]);



            if (id > 0 && name != null && creationTime != null
                    && type != null && fullName!= null)
                return new Organization(id, name, x, y, coordinates, creationTime, annualTurnover, fullName, type, officialAddress);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | ArrayIndexOutOfBoundsException | NullPointerException | JAXBException ex) {
            return null;
        }
        return null;
    }
    private static Object getValueOf(Class<?> toClass, String s) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (toClass.equals(String.class) || s.equals("")) return s;
        Method method = toClass.getMethod("valueOf", String.class);
        return method.invoke(null, s);
    }
}