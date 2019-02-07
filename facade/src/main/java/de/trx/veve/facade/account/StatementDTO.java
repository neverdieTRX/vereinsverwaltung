package de.trx.veve.facade.account;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.trx.veve.entity.Statement;

@JsonAutoDetect
class StatementDTO {

    @JsonProperty
    private String createAt;

    @JsonProperty
    private String createdByName;

    @JsonProperty
    private String createdByIBAN;

    @JsonProperty
    private double amount;

    StatementDTO(Statement statement, String createByName, String createdByIBAN) {
        this.createAt = statement.getCreatedAt().toString();
        this.amount = statement.getAmount();
        this.createdByIBAN = createdByIBAN;
        this.createdByName = createByName;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreatedByName() {
        return this.createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByIBAN() {
        return this.createdByIBAN;
    }

    public void setCreatedByIBAN(String createdByIBAN) {
        this.createdByIBAN = createdByIBAN;
    }
}
