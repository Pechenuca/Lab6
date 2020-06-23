package command;

import coreSources.Answer;
import coreSources.Organization;
import mainPackage.Collection;
import mainPackage.CommandManager;

import javax.xml.bind.JAXBException;
import java.util.HashMap;


public abstract class Command {
    private String description;
    private String args = "";
    private boolean needOrg = false;
    private boolean needArg = false;
    private String typeOfArg = "no";

    public Answer execute(HashMap<String, Command> hashMap, Collection collection, CommandManager commandManager,
                          Organization organization, String... arg) throws JAXBException {
        return null;
    }

    ;


    public String getDescription() {
        return description;
    }

    public boolean isNeedOrganization() {
        return needOrg;
    }

    public void setNeedOrg(boolean needOrg) {
        this.needOrg = needOrg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public boolean isNeedArg() {
        return needArg;
    }

    public void setNeedArg(boolean needArg) {
        this.needArg = needArg;
    }

    public void setTypeOfArg(String typeOfArg) {
        this.typeOfArg = typeOfArg;
    }

    public String getTypeOfArg() {
        return typeOfArg;
    }
}
