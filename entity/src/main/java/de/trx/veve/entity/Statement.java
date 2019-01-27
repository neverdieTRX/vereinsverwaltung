package de.trx.veve.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;


/**
 * Konto zu- abgang. (Kontoauszug)
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Embeddable
public class Statement {

    /**
     * Erstellungsdatum
     */
    private LocalDateTime createdAt;

    /**
     * Vereinsmitglieds ID des Überweisers
     */
    private long createdBy;

    /**
     * Geldmenge
     */
    private double amount;

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
