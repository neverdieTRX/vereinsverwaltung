package de.trx.veve.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.List;

/**
 * Bankkonto.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Entity
public class Account extends BaseEntity {

    private static final long serialVersionUID = -1267880751292654877L;
    /**
     * Iban
     */
    private String iban;

    /**
     * Kontostand
     */
    private double balance;

    /**
     * Konto zu- und abgänge
     */
    @ElementCollection
    @Embedded
    private List<Statement> statements;

    public List<Statement> getStatements() {
        return this.statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void addStatement(Statement statement) {
        this.statements.add(statement);
    }
}
