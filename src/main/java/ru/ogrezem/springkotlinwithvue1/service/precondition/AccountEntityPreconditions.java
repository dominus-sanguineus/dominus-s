package ru.ogrezem.springkotlinwithvue1.service.precondition;

import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.exception.account.AccountAlreadyExistsException;
import ru.ogrezem.springkotlinwithvue1.exception.account.AccountNotFoundException;

@Service("account")
public class AccountEntityPreconditions extends EntityPreconditions {

    @Override
    public void checkIsAbsent(boolean isAbsent) {
        if (!isAbsent)
            throw new AccountAlreadyExistsException("Такой аккаунт уже существует");
    }

    @Override
    public void checkFound(boolean isFound) {
        if (!isFound)
            throw new AccountNotFoundException("Аккаунт не найден");
    }

    @Override
    public void checkDeleted(boolean wasDeleted) {
        if (!wasDeleted)
            throw new AccountNotFoundException("Нельзя удалить несуществующий аккаунт");
    }
}
