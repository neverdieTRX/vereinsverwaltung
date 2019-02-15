package de.trx.veve.business;

import de.trx.veve.entity.Account;
import de.trx.veve.entity.User;
import de.trx.veve.integration.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties = {
        "veve.statements.organisation.iban=testIBAN",
        "veve.statements.fee.small=5",
        "veve.statements.fee.big=10",
})
public class AccountServiceMOCKTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private UserService userService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void firstTest() {

        List<User> dbUsers = new ArrayList<>();
        User user = new User();
        user.setIban("testIBAN");
        user.setId(1L);
        user.setName("testNAME");
        user.setEmployment(true);
        user.setBirthDate(LocalDate.now());
        dbUsers.add(user);
        when(userService.findAll()).thenReturn(dbUsers);

        Account account = new Account();
        account.setBalance(0);
        account.setIban("testIBAN");
        when(this.accountRepository.findByIban(null)).thenReturn(account);

        accountService.transferMonthly();
        assertThat(10.0, is(accountService.getOrganisationBalance()));
    }
}
