package coreSources;

import java.io.Serializable;

public class CoreCommand implements Serializable, AbstractCommand {
    private Organization organization;
    private String arg;
    private String name;
    private String description;
    private boolean needArg = false;
    private boolean needOrganization = false;
    private String typeOfArg = "";

    public CoreCommand(String name, String arg, Organization organization) {
        this.name = name;
        this.arg = arg;
        this.organization = organization;
    }

    public CoreCommand(String name, String arg) {
        this.name = name;
        this.arg = arg;
    }

    public CoreCommand(String name) {
        this.name = name;
    }

    public CoreCommand(String name, Organization organization) {
        this.name = name;
        this.organization = organization;

    }

    public Organization getOrganization() {
        return organization;

    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNeedArg(boolean needArg) {
        this.needArg = needArg;
    }

    public void setNeedOrganization(boolean needOrganization) {
        this.needOrganization = needOrganization;
    }

    public void setTypeOfArg(String typeOfArg) {
        this.typeOfArg = typeOfArg;
    }

    public String getArg() {
        return arg;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean isNeedArg() {
        return false;
    }

    @Override
    public boolean isNeedOrganization() {
        return false;
    }

    @Override
    public String getTypeOfArg() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "CoreCommand{" +
                "worker=" + organization +
                ", arg='" + arg + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", needArg=" + needArg +
                ", needWorker=" + needOrganization +
                ", typeOfArg='" + typeOfArg + '\'' +
                '}';

    }
}
