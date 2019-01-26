package de.trx.veve.facade.account;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.trx.veve.entity.Statement;

@JsonAutoDetect
class StatementDTO {

    @JsonProperty
    private String createAt;

    @JsonProperty
    private long createdBy;

    @JsonProperty
    private double amount;

    StatementDTO(Statement statement) {
        this.createAt = statement.getCreatedAt().toString();
        this.createdBy = statement.getCreatedBy();
        this.amount = statement.getAmount();
    }

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

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
