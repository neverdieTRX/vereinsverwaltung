package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.entity.User;
import de.trx.veve.integration.AccountRepository;
import de.trx.veve.integration.UserRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

@EnableJpaRepositories("de.trx.veve.integration")
@EntityScan(basePackages = "de.trx.veve.entity")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AccountService.class, UserService.class, UserRepository.class, AccountRepository.class})
@EnableAutoConfiguration
@TestPropertySource(properties = {
        "veve.statements.organisation.iban=testIBAN",
        "veve.statements.fee.small=5",
        "veve.statements.fee.big=10",
})
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;



    @BeforeClass
    public static void setUp(){
    }

    @Test
    public void firstTest(){

//        List<User> dbUsers = new ArrayList<>();
        User user = new User();
        user.setIban("testIBAN");
        user.setId(1L);
        user.setName("testNAME");
        user.setEmployment(true);
        user.setBirthDate(LocalDate.now());
//        dbUsers.add(user);
        this.userService.create(user);

        Account account = new Account();
        account.setBalance(0);
        account.setIban("testIBAN");
        this.accountService.create(account);

        accountService.transferMonthly();
        assertEquals(account.getBalance(), 10);
    }
}
