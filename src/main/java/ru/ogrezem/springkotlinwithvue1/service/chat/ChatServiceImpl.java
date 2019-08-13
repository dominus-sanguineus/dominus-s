package ru.ogrezem.springkotlinwithvue1.service.chat;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;
import ru.ogrezem.springkotlinwithvue1.model.message.ChatNotification;
import ru.ogrezem.springkotlinwithvue1.model.message.DefaultMessage;
import ru.ogrezem.springkotlinwithvue1.service.chat.account.ChatAccountService;
import ru.ogrezem.springkotlinwithvue1.service.notificationMessage.NotificationMessageProvider;

import java.util.Map;
import java.util.Set;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatAccountService accountService;
    private SimpMessageSendingOperations messagingTemplate;
    private NotificationMessageProvider notificationMessageProvider;
    private static final String NOTIFICATION_PATH = "/queue/notification";
    private static final String MAIN_PATH = "/queue/main";

    public ChatServiceImpl(
            ChatAccountService accountService,
            SimpMessageSendingOperations messagingTemplate,
            @Qualifier("ru") NotificationMessageProvider notificationMessageProvider
    ) {
        this.accountService = accountService;
        this.messagingTemplate = messagingTemplate;
        this.notificationMessageProvider = notificationMessageProvider;
    }

    @Override
    public void requestRegistration(ChatAccount account, String sessionId) {
        if (accountService.registered(account)) {
            System.out.printf("<%s> уже зарегистрирован%n", account.getUsername());////
            notifyUser(sessionId, new ChatNotification(notificationMessageProvider.getAccountAlreadyExistsMessage()));
        } else {
            accountService.register(account, sessionId);
            System.out.printf("<%s> успешно зарегистрирован%n", account.getUsername());
            notifyUser(sessionId, new ChatNotification(notificationMessageProvider.getSuccessfulRegistrationMessage()));
            notifyAll(new ChatNotification(notificationMessageProvider.getUserJoinedChatMessage(account.getUsername())));
        }
    }

    @Override
    public void requestMessageSending(DefaultMessage message, String sessionId) {
        if (isAccountRegistered(message.getSenderAccount())) {
            System.out.printf("<%s> зарегистрирован и отправляет сообщение%n", message.getSenderAccount().getUsername());
            broadcastMessage(message);
        } else {
            notifyUser(
                    sessionId,
                    new ChatNotification(notificationMessageProvider.getRegistrationRequiredToSendMessage())
            );
            System.out.printf("<%s> не зарегистрирован, в отправке отказано%n", message.getSenderAccount().getUsername());
        }
    }

    @Override
    public void broadcastMessage(DefaultMessage message) {
        broadcast(message, MAIN_PATH);
    }

    @Override
    public void notifyUser(String sessionId, ChatNotification notification) {
        sendToSpecificUser(notification, sessionId, NOTIFICATION_PATH);
    }

    @Override
    public void notifyAll(ChatNotification notification) {
        broadcast(notification, NOTIFICATION_PATH);
    }

    @Override
    public boolean isAccountRegistered(ChatAccount account) {
        return accountService.registered(account);
    }

    private <T> void broadcast(T object, String destination) {
        Set<String> sessionsIdsSet = accountService.getSessionsIds();
        for (String sessionId : sessionsIdsSet)
            sendToSpecificUser(object, sessionId, destination);
    }

    private <T> void sendToSpecificUser(T object, String sessionId, String destination) {
        messagingTemplate.convertAndSendToUser(sessionId, destination, object, makeHeaders(sessionId));
    }

    private Map<String, Object> makeHeaders(String sessionId) {
        var messageHeaderAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        messageHeaderAccessor.setSessionId(sessionId);
        messageHeaderAccessor.setLeaveMutable(true);
        return messageHeaderAccessor.getMessageHeaders();
    }
}
