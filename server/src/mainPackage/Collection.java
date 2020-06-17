package mainPackage;

import coreSources.Factory;
import coreSources.Organization;
import exception.FileException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Vector;

public class Collection {
    private File file;
    private Vector<Organization> vectorOrg = new Vector<>();
    private ZonedDateTime creationTime;
    private Factory factory = new Factory();
    private String type = getCollection().getClass().getTypeName();

    public Vector<Organization> getVector() {
        return vectorOrg;
    }


    Collection(File file) {
        FileReader fileReader = new FileReader();
        this.file = file;
        try {
            Vector<Organization> collection1 = fileReader.read(file);
            vectorOrg.addAll(collection1);
        } catch (FileException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        setCreationTime(ZonedDateTime.now());
    }


    public void add() throws JAXBException {
        Organization organization = factory.createOrganization();
        getVector().add(organization);
    }

    public boolean addIfMax() throws JAXBException {
        Organization organization = factory.createOrganization();
        if (isMax(organization)) {
            getVector().add(organization);
            return true;

        } else {
            return false;
        }
    }

    public boolean isMax(Organization organization) {
        boolean i = true;
        for (Organization organization1 : vectorOrg) {
            if (organization.compareTo(organization1) <= 0) {
                i = false;
                break;
            }
        }
        return i;
    }

    public void show() {
        if (!getVector().isEmpty()) {
            int i = 1;
            for (Organization organization : vectorOrg) {
                System.out.println("Организация " + i);
                System.out.println(organization.toString());
                i++;
            }

        } else {
            System.out.println("Коллекция пуста");
        }
    }

    public void remove_by_id(int id) {
        if (!getVector().isEmpty()) {
            for (Organization organization : vectorOrg) {
                if (organization.getId() == id) {
                    vectorOrg.remove(organization);
                    System.out.println("Организация удалена");
                    return;
                }
            }
            System.out.println("Организация с данным id не найдена");
        } else {

            System.out.println("Коллекция пуста");
        }
    }

    public void clear() {
        vectorOrg.clear();
    }

    public void remove_first() {
        if (!getVector().isEmpty()) {
            vectorOrg.remove(1);
        } else {
            System.out.println("коллекция пуста");
        }
    }

    public void remove_greater() throws JAXBException {
        if (!getVector().isEmpty()) {
            Organization newOrganization = factory.createOrganization();
            Vector<Organization> vector1 = new Vector<>();

            vectorOrg.forEach(organization -> {
                if (newOrganization.compareTo(organization) >= 0) {
                    vector1.add(organization);
                }
            });
            vectorOrg = vector1;
        }
    }

    public String getInfo() {
        return "Информация о коллекции \n" + "Дата инициализации коллекции: " + getCreationTime() + "\n Тип коллекции: ";

    }

    public long count_greater_than_official_address(int address1) {

        return vectorOrg.stream().filter(organization -> organization.getOfficialAddress() > address1).count();

    }

    public void replace(int id) throws JAXBException {
        if (!getCollection().isEmpty()) {
            for (Organization organization : getCollection()) {
                vectorOrg.remove(organization);
                Organization newOrg = factory.createOrganization();
                newOrg.setId(id);
                vectorOrg.add(newOrg);
                System.out.println("Организация создана");
                break;
            }

        } else {
            System.out.println("организация с таким id не найдена");
        }

    }


    public void print_descending() {
        Collections.sort(vectorOrg);
        System.out.println("Элементы колекции в порядке убывания:");
        for (Organization organization : vectorOrg) {
            System.out.println(organization);
        }

    }


    public void setVector(Vector<Organization> vector) {
        this.vectorOrg = vector;
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

    public Vector<Organization> getCollection() {
        return vectorOrg;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void remove(String name) {
        if (!getCollection().isEmpty()) {

            for (Organization organization : vectorOrg) {

                if (organization.getName().equals(name)) {
                    vectorOrg.remove(organization);
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



