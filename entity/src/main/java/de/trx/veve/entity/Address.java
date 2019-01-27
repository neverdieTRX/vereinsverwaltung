package de.trx.veve.entity;

import javax.persistence.Embeddable;

/**
 * Eine Wohnadresse.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Embeddable
public class Address {

    /**
     * Postleitzahl
     */
    private int postalCode;

    /**
     * Ort
     */
    private String place;

    /**
     * Straße
     */
    private String street;

    /**
     * Hausnummer
     */
    private String number;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
