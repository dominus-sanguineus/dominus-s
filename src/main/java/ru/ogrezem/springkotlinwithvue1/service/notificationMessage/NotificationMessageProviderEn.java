package ru.ogrezem.springkotlinwithvue1.service.notificationMessage;

import org.springframework.stereotype.Service;

@Service("en")
public class NotificationMessageProviderEn implements NotificationMessageProvider {

    private static final String ACCOUNT_ALREADY_EXISTS_ERR = "Account with such username already exists";
    private static final String SUCCESSFUL_REGISTRATION_MSG = "You successfully registered";
    private static final String REGISTRATION_REQUIRED_TO_SEND = "You must register to send message";
    private static final String USER_JOINED_CHAT_PATTERN = "%s joined the chat!";

    @Override
    public String getAccountAlreadyExistsMessage() {
        return ACCOUNT_ALREADY_EXISTS_ERR;
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
