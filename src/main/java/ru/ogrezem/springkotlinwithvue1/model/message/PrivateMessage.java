package ru.ogrezem.springkotlinwithvue1.model.message;

import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;

public class PrivateMessage extends DefaultMessage {

    private String receiverId;

    public PrivateMessage(String text, ChatAccount account, String receiverId, long sendingDateSeconds) {
        super(text, account, sendingDateSeconds);
        this.receiverId = receiverId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
