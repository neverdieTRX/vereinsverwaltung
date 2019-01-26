package de.trx.veve.entity;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;


@Embeddable
public class Statement {

    private LocalDateTime createdAt;
    private long createdBy;
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
