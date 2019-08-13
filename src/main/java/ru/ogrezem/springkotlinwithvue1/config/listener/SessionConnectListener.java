package ru.ogrezem.springkotlinwithvue1.config.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import ru.ogrezem.springkotlinwithvue1.service.chat.ChatService;

@Component
public class SessionConnectListener implements ApplicationListener<SessionConnectEvent> {

    private ChatService chatService;

    SessionConnectListener(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        System.out.printf("Websocket session connected%n");
    }
}
