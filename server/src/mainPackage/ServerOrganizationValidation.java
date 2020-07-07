package mainPackage;

import coreSources.Organization;
import exception.FieldException;

public class ServerOrganizationValidation {
    public static boolean ValidateOrganization(Organization organization) {
        MyLogger.info("Валидация организации");
        try{
            organization.getName();
            organization.getAnnualTurnover();
            organization.getCoordinates();
            organization.getCreationDate();
            organization.getFullName();
            organization.getId();
            organization.getType();
            organization.getAnnualTurnover();

            MyLogger.info("парам-пам-пам");
            return true;
        } catch (FieldException | NullPointerException e) {
            MyLogger.error("Беды с рабочим");
            return false;
        }
    }
}
