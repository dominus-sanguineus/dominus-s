package ru.ogrezem.springkotlinwithvue1.service.notifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.service.notificationMessage.NotificationMessageProvider;

@Service
@Deprecated
public class UsersNotifierServiceImpl implements UsersNotifierService {

    private NotificationMessageProvider notificationMessageProvider;

    public UsersNotifierServiceImpl(@Qualifier("ru") NotificationMessageProvider notificationMessageProvider) {
        this.notificationMessageProvider = notificationMessageProvider;
    }
}
