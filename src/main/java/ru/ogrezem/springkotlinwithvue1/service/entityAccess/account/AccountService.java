package ru.ogrezem.springkotlinwithvue1.service.entityAccess.account;

import ru.ogrezem.springkotlinwithvue1.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    void create(Account account);

    List<Account> getAll();

    Optional<Account> findByUsername(String username);

    boolean deleteByUsername(String username);

    Optional<Account> update(String username, Account account);

    boolean isAbsent(Account account);
}
