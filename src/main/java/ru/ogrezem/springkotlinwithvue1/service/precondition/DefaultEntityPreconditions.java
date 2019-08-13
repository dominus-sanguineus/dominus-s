package ru.ogrezem.springkotlinwithvue1.service.precondition;

import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.exception.EntityAlreadyExistsException;
import ru.ogrezem.springkotlinwithvue1.exception.EntityNotFoundException;

@Service("default")
public class DefaultEntityPreconditions extends EntityPreconditions {

    @Override
    public void checkDeleted(boolean wasDeleted) {
        if (!wasDeleted)
            throw new EntityNotFoundException();
    }

    @Override
    public void checkFound(boolean isFound) {
        if (!isFound)
            throw new EntityNotFoundException();
    }

    @Override
    public void checkIsAbsent(boolean isAbsent) {
        if (!isAbsent)
            throw new EntityAlreadyExistsException();
    }
}
