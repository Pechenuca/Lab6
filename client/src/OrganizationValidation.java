import coreSources.Organization;
import exception.FieldException;

public class OrganizationValidation {
    public static boolean ValidateOrganization(Organization organization) {
        try {
            organization.getName();
            organization.getId();
            organization.getType();
            organization.getAnnualTurnover();
            organization.getFullName();

            organization.getOfficialAddress();
            organization.getCoordinates(); //сделать геттер на X и Y
            return true;
        } catch (FieldException | NullPointerException e) {
            return false;
        }
    }

}
