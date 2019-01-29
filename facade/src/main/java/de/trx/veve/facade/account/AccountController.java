package de.trx.veve.facade.account;

import de.trx.veve.business.AccountService;
import de.trx.veve.entity.Statement;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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
        List<Statement> statementsByOptionalDates = this.accountService.getStatementsByOptionalDates(this.formatDateString(beginn),
                this.formatDateString(end));
        return statementsByOptionalDates.stream()
                .map((statement -> new StatementDTO(statement)))
                .collect(Collectors.toList());
    }

    /**
     * Formatiert einen String mit dem Pattern dd.MM.yyyy zu einem Localdate um.
     *
     * @param date -
     * @return -
     */
    private static Optional<LocalDate> formatDateString(String date) {
        if (StringUtils.isNullOrEmpty(date)) {
            return Optional.empty();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return Optional.of(LocalDate.parse(date, formatter));
    }


}
