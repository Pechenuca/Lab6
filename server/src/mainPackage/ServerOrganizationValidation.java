package mainPackage;

import coreSources.Organization;
import exception.FieldException;

public class ServerOrganizationValidation {
    public static boolean ValidateOrganization(Organization organization) {
        Logger.info("Валидация организации");
        try{
            organization.getName();
            organization.getAnnualTurnover();
            organization.getCoordinates();
            organization.getCreationDate();
            organization.getFullName();
            organization.getId();
            organization.getType();
            organization.getAnnualTurnover();

            Logger.info("парам-пам-пам");
            return true;
        } catch (FieldException | NullPointerException e) {
            Logger.error("Беды с рабочим");
            return false;
        }
    }
}
