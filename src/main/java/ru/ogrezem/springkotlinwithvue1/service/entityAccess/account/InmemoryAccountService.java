package ru.ogrezem.springkotlinwithvue1.service.entityAccess.account;

import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("inmemoryAccountService")
public class InmemoryAccountService implements AccountService {

    private ArrayList<Account> accounts = new ArrayList<Account>(List.of(
            new Account("228", "ogrezem", "dawdaw"),
            new Account("1773", "nigosh", "nigga322")
    ));

    @Override
    public void create(Account account) {
        accounts.add(account);
    }

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .findAny();
    }

    @Override
    public boolean deleteByUsername(String username) {
        Optional<Account> accountSearchingResult = findByUsername(username);
        if (!accountSearchingResult.isPresent())
            return false;
        accounts.remove(accountSearchingResult.get());
        return true;
    }

    @Override
    public Optional<Account> update(String username, Account account) {
        Optional<Account> accountSearchingResult = findByUsername(username);
        if (!accountSearchingResult.isPresent())
            return Optional.empty();
        accounts.remove(accountSearchingResult.get());
        create(account);
        return Optional.of(account);
    }

    @Override
    public boolean isAbsent(Account account) {
        return !existsWithUsername(account.getUsername());
    }

    private boolean existsWithUsername(String username) {
        return accounts.stream()
                .anyMatch(account -> account.getUsername().equals(username));
    }
}
