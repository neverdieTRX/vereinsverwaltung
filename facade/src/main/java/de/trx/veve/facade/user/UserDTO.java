package de.trx.veve.facade.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
class UserDTO {

    /**
     * Datenbank Identifizierer
     */
    @JsonProperty
    private Long id;

    /**
     * Name
     */
    @JsonProperty
    private String name;

    /**
     * Geburtsdatum
     */
    @JsonProperty
    private String birthDate;

    /**
     * Ist der Nutzer arbeitend?
     */
    @JsonProperty
    private boolean employment;

    /**
     * Iban
     */
    @JsonProperty
    private String iban;

    @JsonProperty
    private int postalCode;

    @JsonProperty
    private String place;

    @JsonProperty
    private String street;

    @JsonProperty
    private String number;

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getIban() {
        return this.iban;
    }

    void setIban(String iban) {
        this.iban = iban;
    }

    boolean isEmployment() {
        return this.employment;
    }

    void setEmployment(boolean employment) {
        this.employment = employment;
    }


    String getBirthDate() {
        return this.birthDate;
    }

    void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    int getPostalCode() {
        return this.postalCode;
    }

    void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    String getPlace() {
        return this.place;
    }

    void setPlace(String place) {
        this.place = place;
    }

    String getStreet() {
        return this.street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    String getNumber() {
        return this.number;
    }

    void setNumber(String number) {
        this.number = number;
    }

    Long getId() {
        return this.id;
    }

    void setId(Long id) {
        this.id = id;
    }
}
