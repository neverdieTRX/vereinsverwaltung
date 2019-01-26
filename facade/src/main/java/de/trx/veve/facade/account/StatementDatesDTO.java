package de.trx.veve.facade.account;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatementDatesDTO {

    private String beginn;

    private String end;


    LocalDate getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(this.end, formatter);
    }

    public void setEnd(String end) {
        this.end = end;
    }

    LocalDate getBeginn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(this.beginn, formatter);
    }

    public void setBeginn(String beginn) {
        this.beginn = beginn;
    }
}
