package de.trx.veve.facade.account;

import de.trx.veve.business.AccountService;
import de.trx.veve.business.UserService;
import de.trx.veve.entity.Statement;
import de.trx.veve.entity.User;
import de.trx.veve.facade.exceptions.IllegalArgumentRestException;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Restschnittstelle für Kontofunktionalitäten.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@RestController
@RequestMapping(value = "/api/account")
@Component
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    /**
     * Überweist bei Aufruf die Montasgebühr aller Vereinsmitglieder auf das Vereinskonto.
     */
    @CrossOrigin
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public void transferMonthly() {
        this.accountService.transferMonthly();
    }

    /**
     * Gibt den aktuellen Kontostand des Vereins zurück.
     *
     * @return -
     */
    @CrossOrigin
    @RequestMapping(value = "/balance/organisation", method = RequestMethod.GET, produces = "application/json")
    public BalanceDTO getOrganisationBalance() {
        double organisationBalance = this.accountService.getOrganisationBalance();
        return new BalanceDTO(organisationBalance);
    }

    /**
     * Gibt die Vereinskonto Zu- und Abgänge zurück. Filtert bei vorhandenen Beginn und Enddatum nach dem
     * übergebenen Zeitrahmen.
     *
     * @param beginn -
     * @param end    -
     * @return -
     */
    @CrossOrigin
    @RequestMapping(value = "/statements", method = RequestMethod.GET)
    public List<StatementDTO> findStatements(@RequestParam("beginn") String beginn, @RequestParam("end") String end) {
        List<Statement> statementsByOptionalDates = this.accountService.getStatementsByDates(
                formatDateString(beginn),
                formatDateString(end));

        return this.mapToDTO(statementsByOptionalDates);
    }

    @CrossOrigin
    @RequestMapping(value = "/statements/all", method = RequestMethod.GET)
    public List<StatementDTO> getAllStatements() {
        List<Statement> statementsByOptionalDates = this.accountService.getAllStatements();
        return this.mapToDTO(statementsByOptionalDates);
    }

    private List<StatementDTO> mapToDTO(List<Statement> statementsByOptionalDates) {
        List<StatementDTO> statementDTOS = new ArrayList<>();
        for (Statement statement : statementsByOptionalDates) {
            Optional<User> dbUser = this.userService.findById(statement.getCreatedBy());
            if (dbUser.isPresent()) {
                statementDTOS.add(new StatementDTO(statement, dbUser.get().getName(), dbUser.get().getIban()));
            } else {
                statementDTOS.add(new StatementDTO(statement, "", ""));
            }
        }
        return statementDTOS;
    }

    /**
     * Formatiert einen String mit dem Pattern yyyy-MM-dd zu einem Localdate um.
     *
     * @param date -
     * @return -
     */
    private static LocalDateTime formatDateString(String date) {
        if (StringUtils.isNullOrEmpty(date)) {
            throw new IllegalArgumentException("date must be set");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse;
        try {
            parse = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentRestException("Could not parse date: " + date);
        }
        return parse.atStartOfDay();
    }
}
