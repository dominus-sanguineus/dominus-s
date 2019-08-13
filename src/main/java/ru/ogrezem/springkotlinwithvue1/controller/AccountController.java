package ru.ogrezem.springkotlinwithvue1.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ogrezem.springkotlinwithvue1.model.Account;
import ru.ogrezem.springkotlinwithvue1.service.precondition.EntityPreconditions;
import ru.ogrezem.springkotlinwithvue1.service.entityAccess.account.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/account")
class AccountController {

    private AccountService accountService;
    private EntityPreconditions preconditions;

    AccountController(
            @Qualifier("inmemoryAccountService") AccountService accountService,
            @Qualifier("account") EntityPreconditions preconditions
    ) {
        this.accountService = accountService;
        this.preconditions = preconditions;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody Account newAccount) {
        preconditions.checkIsAbsent(accountService.isAbsent(newAccount));
        accountService.create(newAccount);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    Account get(@PathVariable String username) {
        Optional<Account> accountSearchingResult = accountService.findByUsername(username);
        preconditions.checkFound(accountSearchingResult.isPresent());
        return accountSearchingResult.get();
    }

    @GetMapping
    @CrossOrigin
    List<Account> getAll() {
        return accountService.getAll();
    }

    @PutMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable String username, @RequestBody Account account) {
        Optional<Account> accountUpdatingResult = accountService.update(username, account);
        preconditions.checkFound(accountUpdatingResult.isPresent());
    }

    @DeleteMapping("/username/{username}")
    void delete(@PathVariable String username) {
        boolean wasDeleted = accountService.deleteByUsername(username);
        preconditions.checkDeleted(wasDeleted);
    }
}
