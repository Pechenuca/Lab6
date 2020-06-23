package coreSources;

import exception.FieldException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.time.ZonedDateTime;


@XmlRootElement(name = "coreSources.Organization")
@XmlType(propOrder = {"id", "name", "coordinates", "creationDate", "annualTurnover",
        "fullName", "type", "officialAddress"})
public class Organization implements Comparable<Organization> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null

    public Organization() {

    }

    public Organization(int id, String name, Double x, long y, Coordinates coordinates, ZonedDateTime creationDate, Long annualTurnover, String fullName, OrganizationType type, Address officialAddress) throws JAXBException {
        this.id = id;
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        this.creationDate = ZonedDateTime.now();
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.type = type;
        this.officialAddress = new Address();
    }


    public void convertOrganization(Organization organization, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(organization, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


    @XmlAttribute
    public int getId() {
        if (id < 0) throw new FieldException();
        else return id;
    }

    @XmlAttribute
    public String getName() {
        if (name == null || name.equals("")) throw new FieldException();
        else return name;
    }

    @XmlAttribute
    public Coordinates getCoordinates() {
        if (coordinates == null) throw new FieldException();
        else return coordinates;
    }

    @XmlAttribute
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @XmlAttribute
    public String getAnnualTurnover() {
        if (annualTurnover == 0 || annualTurnover.toString().equals("")) throw new FieldException();
        else return annualTurnover.toString();
    }

    @XmlAttribute
    public String getFullName() {
        if (fullName == null || fullName.equals("")) throw new FieldException();
        else return fullName;
    }

    @XmlAttribute
    public OrganizationType getType() {
        if (type == null) throw new FieldException();
        else return type;
    }

    @XmlAttribute
    public int getOfficialAddress() {
        int officialAddress_in_integer = Integer.parseInt(String.valueOf(officialAddress));
        if (officialAddress_in_integer < 0) throw new FieldException();
        else return officialAddress_in_integer;
    }


    @Override
    public int compareTo(Organization o) {
        return Double.compare(getOfficialAddress(), getOfficialAddress());
    }

    @Override
    public String toString() {
        return "coreSources.Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", annualTurnover=" + annualTurnover +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", officialAddress=" + officialAddress +
                '}';
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setAnnualTurnover(Long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }
}


