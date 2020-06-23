package mainPackage;

import coreSources.Answer;
import coreSources.Factory;
import coreSources.Organization;
import exception.FileException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.*;

public class Collection {
    private File file;
    private Set<Organization> collection = new LinkedHashSet<Organization>();

    private ZonedDateTime creationTime;
    private Factory factory = new Factory();
    private String type = getCollection().getClass().getTypeName();

    Collection(File file) {
        FileReader fileReader = new FileReader();
        this.file = file;
        try {
            Vector<Organization> collection1 = fileReader.read(file);
            collection.addAll(collection1);
        } catch (FileException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        setCreationTime(ZonedDateTime.now());
    }


    public boolean add(Organization organization) throws JAXBException {
        int size = getCollection().size();
        getCollection().add(organization);
        return getCollection().size() > size;
    }

    public boolean addIfMax(Organization organization) throws JAXBException {
        if (isMax(organization)) {
            getCollection().add(organization);
            return true;

        } else {
            return false;
        }
    }

    public boolean isMax(Organization organization) {
        boolean i = true;
        for (Organization organization1 : collection) {
            if (organization.compareTo(organization1) <= 0) {
                i = false;
                break;
            }
        }
        return i;
    }

    public Answer show() {
     Answer answer;
    ArrayList<Organization> organizations = new ArrayList<>(Arrays.asList(getCollection().stream().toArray(Organization[]::new)));
     return new Answer("Список организаций: ", organizations);
}

    public Answer remove_by_id(int id) {
        if (!getCollection().isEmpty()) {
            for (Organization organization : collection) {
                if (organization.getId() == id) {
                    collection.remove(organization);
                    return new Answer("Организация удалена");
                }
            }
            return new Answer("Организация с данным id не найдена");
        } else {

            return new Answer("Коллекцуия пуста");
        }

    }

    public Answer clear() {
        collection.clear();
        return new Answer("Коллекция очищена");
    }

    public Answer remove_first() {
        if (!getCollection().isEmpty()) {
            collection.remove(1);
            return new Answer("Оргназация удалена");
        } else {
            return new Answer("коллекция пуста");
        }

    }

    public Answer remove_greater(Organization newOrganization) throws JAXBException {
        if (!getCollection().isEmpty()) {
           Set<Organization> collection1 = new LinkedHashSet<Organization>();

            collection.forEach(organization -> {
                if (newOrganization.compareTo(organization) >= 0) {
                    collection1.add(organization);
                }
            });
            collection = collection1;

        }
        return new Answer("Удалены все организации больше заданного");
    }

    public String getInfo() {
        return "Информация о коллекции \n" + "Дата инициализации коллекции: " + getCreationTime() + "\n Тип коллекции: ";

    }

    public long count_greater_than_official_address(int address1) {

        return collection.stream().filter(organization -> organization.getOfficialAddress() > address1).count();

    }

    public Answer replace(int id, Organization newOrganization) throws JAXBException {
        if (!getCollection().isEmpty()) {


        } else {
            System.out.println("организация с таким id не найдена");
        }

    }


    public Answer print_descending(Collection collection) {
        Collections.sort(collection);
        System.out.println("Элементы колекции в порядке убывания:");
        for (Organization organization : collection) {
            System.out.println(organization);
        }

    }


    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public File getFile() {
        return file;
    }

    public String getType() {
        return type;
    }

    public Set<Organization> getCollection() {
        return collection;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void remove(String name) {
        if (!getCollection().isEmpty()) {

            for (Organization organization : collection) {

                if (organization.getName().equals(name)) {
                    collection.remove(organization);
                    System.out.println("Коллекция с данным именем успешно удалена");
                    return;
                }
            }
            System.out.println("Организация с данным именем не найдена!");
        } else {
            System.out.println("Коллекция пуста");
        }
    }
}



