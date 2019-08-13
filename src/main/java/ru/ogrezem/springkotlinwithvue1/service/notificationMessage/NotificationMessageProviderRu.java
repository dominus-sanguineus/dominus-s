package ru.ogrezem.springkotlinwithvue1.service.notificationMessage;

import org.springframework.stereotype.Service;

@Service("ru")
public class NotificationMessageProviderRu implements NotificationMessageProvider {

    private static final String ACCOUNT_ALREADY_EXISTS_MSG = "Аккаунт с таким именем уже существует";
    private static final String SUCCESSFUL_REGISTRATION_MSG = "Регистрация прошла успешно";
    private static final String REGISTRATION_REQUIRED_TO_SEND = "Для отправки сообщений требуется регистрация";
    private static final String USER_JOINED_CHAT_PATTERN = "%s присоединился к чату!";

    @Override
    public String getAccountAlreadyExistsMessage() {
        return ACCOUNT_ALREADY_EXISTS_MSG;
    }

    @Override
    public String getSuccessfulRegistrationMessage() {
        return SUCCESSFUL_REGISTRATION_MSG;
    }

    @Override
    public String getRegistrationRequiredToSendMessage() {
        return REGISTRATION_REQUIRED_TO_SEND;
    }

    @Override
    public String getUserJoinedChatMessage(String userName) {
        return String.format(USER_JOINED_CHAT_PATTERN, userName);
    }
}
