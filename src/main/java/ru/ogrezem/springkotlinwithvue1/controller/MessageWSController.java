package ru.ogrezem.springkotlinwithvue1.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;
import ru.ogrezem.springkotlinwithvue1.model.message.DefaultMessage;
import ru.ogrezem.springkotlinwithvue1.service.chat.ChatService;

import java.util.Map;

@Controller
//@CrossOrigin
public class MessageWSController {

    //    private CompositeMessageConverter messageConverter;
    private ChatService chatService;

    public MessageWSController(ChatService chatService) {
//        this.messageConverter = messageConverter;
        this.chatService = chatService;
    }

    @MessageMapping("/general/register")
    void handleRegistrationRequest(ChatAccount account, SimpMessageHeaderAccessor headerAccessor) {
        chatService.requestRegistration(account, sessionId(headerAccessor));
    }

    @MessageMapping("/general/send")
    void handleSendingMessage(DefaultMessage message, SimpMessageHeaderAccessor headerAccessor) {
        chatService.requestMessageSending(message, sessionId(headerAccessor));
    }

    private String sessionId(SimpMessageHeaderAccessor headerAccessor) {

        String sessionId = headerAccessor.getSessionId();
        for (Map.Entry<String, Object> entry : headerAccessor.getMessageHeaders().entrySet())
            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
        System.out.println("Session id header name: " + HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME);
        return sessionId;
//        return headerAccessor.getHeader(HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME).toString();
    }
}
