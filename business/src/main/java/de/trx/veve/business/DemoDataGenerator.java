package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.entity.Address;
import de.trx.veve.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Generiert Demodaten bei Applikationsstart.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Component
public class DemoDataGenerator {

    private final UserService userService;

    private final AccountService accountService;

    @Value("${veve.statements.organisation.iban}")
    private String organisationIban;

    @Autowired
    public DemoDataGenerator(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostConstruct
    public void run() {

        if (this.userService.findAll().size() == 0) {
            this.createExampleUser();
        }

        if (this.accountService.findAll().size() == 0) {
            this.createAccount();
        }
    }

    private void createAccount() {
        Account account = new Account();
        account.setBalance(0);
        account.setIban(this.organisationIban);
        account.setStatements(new ArrayList<>());
        this.accountService.create(account);
    }

    private void createExampleUser() {
        User user = new User();
        user.setName("Max Mustermann");
        Address address = new Address();
        address.setStreet("Liebigstraße");
        address.setPostalCode(49074);
        address.setPlace("Osnabrück");
        address.setNumber("31");
        user.setAddress(address);
        user.setIban("DE89 3704 0044 0532 0130 00");
        user.setEmployment(true);
        user.setBirthDate(LocalDate.now());
        this.userService.create(user);
    }

}
