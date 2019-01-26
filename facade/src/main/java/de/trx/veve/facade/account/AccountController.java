package de.trx.veve.facade.account;

import de.trx.veve.business.AccountService;
import de.trx.veve.entity.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/account")
@Component
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @CrossOrigin
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public void transferMonthly() {
        this.accountService.transferMonthly();
    }

    @CrossOrigin
    @RequestMapping(value = "/balance/organisation", method = RequestMethod.GET)
    public double getOrganisationBalance() {
        return this.accountService.getOrganisationBalance();
    }

    @CrossOrigin
    @RequestMapping(value = "/statements", method = RequestMethod.GET, consumes = "application/json")
    public List<StatementDTO> findStatements(@RequestBody StatementDatesDTO statementDatesDTO) {
        List<Statement> statementsByOptionalDates = this.accountService.getStatementsByOptionalDates(Optional.of(statementDatesDTO.getBeginn()),
                Optional.of(statementDatesDTO.getEnd()));
        return statementsByOptionalDates.stream()
                .map((statement -> new StatementDTO(statement)))
                .collect(Collectors.toList());
    }


}
