package main.coreSources;

public enum OrganizationType {
    COMMERCIAL("Коммерческая"),
    PUBLIC("Открытая"),
    GOVERNMENT("Правительство"),
    TRUST("Трест"),
    PRIVATE_LIMITED_COMPANY("ЗАО");

    private String value;

    OrganizationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
