package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.integration.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@DataJpaTest
@EnableJpaRepositories(basePackageClasses = {AccountRepository.class})
@EntityScan(basePackageClasses = {Account.class})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AccountService.class, UserService.class})
@TestPropertySource(properties = {
        "veve.statements.organisation.iban=testIBAN",
        "veve.statements.fee.small=5",
        "veve.statements.fee.big=10",
})
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void myTest() {
        accountService.findAll();
    }

}
