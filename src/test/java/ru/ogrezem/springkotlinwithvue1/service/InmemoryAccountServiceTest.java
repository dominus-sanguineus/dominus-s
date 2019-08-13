package ru.ogrezem.springkotlinwithvue1.service;

import org.junit.jupiter.api.Test;
import ru.ogrezem.springkotlinwithvue1.model.Account;
import ru.ogrezem.springkotlinwithvue1.service.entityAccess.account.InmemoryAccountService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InmemoryAccountServiceTest {

    @Test
    void deleteByUsername() {
        var accountService = new InmemoryAccountService();
        accountService.deleteByUsername("ogrezem");
        List<Account> accounts = accountService.getAll();
        boolean accountAsString = accounts.get(0).toString()
                .equals("Account{id='1773', username='nigosh', password='nigga322'}");
        assertTrue(accounts.size() == 1 || accountAsString);
    }
}