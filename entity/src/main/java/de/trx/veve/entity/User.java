package de.trx.veve.entity;


import javax.persistence.Entity;
import java.time.LocalDate;


/**
 * Eine Mitglied des Vereins.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Entity(name = "veve_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -9157093360580975672L;
    /**
     * Name
     */
    private String name;

    /**
     * Adresse
     */
    private Address address = new Address();

    /**
     * Geburtsdatum
     */
    private LocalDate birthDate;

    /**
     * Ist der Nutzer arbeitend?
     */
    private boolean employment;

    /**
     * Iban
     */
    private String iban;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isEmployment() {
        return this.employment;
    }

    public void setEmployment(boolean employment) {
        this.employment = employment;
    }


    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
