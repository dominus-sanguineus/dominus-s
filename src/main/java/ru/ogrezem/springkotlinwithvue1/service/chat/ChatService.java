package ru.ogrezem.springkotlinwithvue1.service.chat;

import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;
import ru.ogrezem.springkotlinwithvue1.model.message.ChatNotification;
import ru.ogrezem.springkotlinwithvue1.model.message.DefaultMessage;

public interface ChatService {

    void requestRegistration(ChatAccount account, String sessionId);

    void requestMessageSending(DefaultMessage message, String sessionId);

    /**
     * @param message Message to be sent to all connected users
     */
    void broadcastMessage(DefaultMessage message);

    void notifyUser(String sessionId, ChatNotification notification);

    void notifyAll(ChatNotification notification);

    boolean isAccountRegistered(ChatAccount account);


}
