package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.entity.Statement;
import de.trx.veve.entity.User;
import de.trx.veve.integration.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (statement == null) {
            //TODO exception handeling
            return;
        }
        Account account = this.accountRepository.findByIban(this.organisationIban);
        account.addStatement(statement);
        account.setBalance(account.getBalance() + statement.getAmount());
        this.accountRepository.save(account);
    }

    public double getOrganisationBalance() {
        Account account = this.accountRepository.findByIban(this.organisationIban);
        return account.getBalance();

    }

    public void getStatementsByOptionalDates() {
    }

    public List<Statement> getStatementsByOptionalDates(Optional<LocalDate> beginn, Optional<LocalDate> end) {
        List<Statement> statements = this.accountRepository.findByIban(this.organisationIban).getStatements();
        if (!beginn.isPresent() || end.isPresent()) {
            return statements;
        }
        return statements.stream()
                .filter((statement) -> !(statement.getCreatedAt().isBefore(ChronoLocalDateTime.from(end.get())) ||
                        statement.getCreatedAt().isAfter(ChronoLocalDateTime.from(beginn.get()))))
                .collect(Collectors.toList());
    }

    void create(Account account) {
        this.accountRepository.save(account);
    }

    List<Account> findAll() {
        return this.accountRepository.findAll();
    }
}
