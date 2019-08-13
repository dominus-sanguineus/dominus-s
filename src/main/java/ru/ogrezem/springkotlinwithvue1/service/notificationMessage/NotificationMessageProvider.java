package ru.ogrezem.springkotlinwithvue1.service.notificationMessage;

public interface NotificationMessageProvider {

    String getAccountAlreadyExistsMessage();

    String getSuccessfulRegistrationMessage();

    String getRegistrationRequiredToSendMessage();

    String getUserJoinedChatMessage(String userName);
}
