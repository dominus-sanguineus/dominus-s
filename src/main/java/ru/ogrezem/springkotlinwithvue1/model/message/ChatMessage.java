package ru.ogrezem.springkotlinwithvue1.model.message;

import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;

public class ChatMessage extends DefaultMessage {

    private String chatId;

    public ChatMessage(String text, ChatAccount account, String chatId, long sendingDateSeconds) {
        super(text, account, sendingDateSeconds);
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
