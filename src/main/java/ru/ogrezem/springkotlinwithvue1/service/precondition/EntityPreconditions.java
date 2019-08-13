package ru.ogrezem.springkotlinwithvue1.service.precondition;

public abstract class EntityPreconditions {

    public void checkIsAbsent(boolean isAbsent) {}

    public void checkFound(boolean isFound) {}

    public void checkDeleted(boolean wasDeleted) {}
}
