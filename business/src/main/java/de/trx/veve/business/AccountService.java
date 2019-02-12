package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.entity.Statement;
import de.trx.veve.entity.User;
import de.trx.veve.integration.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service der sich um die Kontoverwaltung kümmert.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Value("${veve.statements.organisation.iban}")
    private String organisationIban;

    @Value("${veve.statements.fee.small}")
    private double feeSmall;

    @Value("${veve.statements.fee.big}")
    private double feeBig;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    /**
     * Überträgt für jeden Nutzer die Monatsgebühr auf das Vereinskonto.
     */
    public void transferMonthly() {
        List<User> users = this.userService.findAll();
        users.stream().forEach(this::transferFeeOfUser);
    }

    private void transferFeeOfUser(User user) {
        Statement statement = new Statement();
        statement.setCreatedAt(LocalDateTime.now());
        statement.setCreatedBy(user.getId());
        statement.setAmount(user.isEmployment() ? this.feeBig : this.feeSmall);
        this.acceptFee(statement);
    }


    private void acceptFee(Statement statement) {
        Account account = this.accountRepository.findByIban(this.organisationIban);
        account.addStatement(statement);
        account.setBalance(account.getBalance() + statement.getAmount());
        this.accountRepository.save(account);
    }

    /**
     * Gibt den aktuellen Vereinskontostand zurück.
     *
     * @return -
     */
    public double getOrganisationBalance() {
        Account account = this.accountRepository.findByIban(this.organisationIban);
        return account.getBalance();
    }

    /**
     * Gibt die Kontoauszüge des Vereinskontos zurück, falls vorhanden gefiltert nach dem übergebenen Zeitrahmen.
     *
     * @param beginn -
     * @param end    -
     * @return -
     */
    public List<Statement> getStatementsByDates(LocalDateTime beginn, LocalDateTime end) {
        List<Statement> statements = this.accountRepository.findByIban(this.organisationIban).getStatements();
        return statements.stream()
                .filter((statement) -> AccountService.this.filterStatement(beginn, end, statement))
                .collect(Collectors.toList());
    }

    private boolean filterStatement(LocalDateTime beginn, LocalDateTime end, Statement statement) {
        return (statement.getCreatedAt().isBefore(ChronoLocalDateTime.from(end)) ||
                statement.getCreatedAt().toLocalDate().isEqual(end.toLocalDate())) &&
                (statement.getCreatedAt().isAfter(ChronoLocalDateTime.from(beginn)) ||
                        statement.getCreatedAt().toLocalDate().isEqual(beginn.toLocalDate()));
    }

    public List<Statement> getAllStatements() {
        return this.accountRepository.findByIban(this.organisationIban).getStatements();
    }

    /**
     * Erstellt ein Konto.
     *
     * @param account -
     */
    void create(Account account) {
        this.accountRepository.save(account);
    }

    /**
     * Gibt alle Konten zurück.
     *
     * @return -
     */
    List<Account> findAll() {
        return this.accountRepository.findAll();
    }
}
