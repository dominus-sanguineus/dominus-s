package ru.ogrezem.springkotlinwithvue1.config.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.ogrezem.springkotlinwithvue1.service.chat.account.ChatAccountService;

import java.security.Principal;

@Component
public class SessionDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    private ChatAccountService chatAccountService;

    SessionDisconnectListener(ChatAccountService chatAccountService) {
        this.chatAccountService = chatAccountService;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        Principal user = event.getUser();
        if (user != null)
            System.out.printf("Disconnect user %s%n", user.getName());
        else
            System.out.printf("Websocket session %s disconnected%n", event.getSessionId());
        boolean hasBeenDeleted = chatAccountService.delete(event.getSessionId());
        if (hasBeenDeleted)
            System.out.println("Session has been successfully deleted");/////////
    }
}
