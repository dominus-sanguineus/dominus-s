package ru.ogrezem.springkotlinwithvue1.model.message;

import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;

public class DefaultMessage extends ChatNotification {

    private ChatAccount senderAccount;
    private long sendingDateSeconds;

    public DefaultMessage(String text, ChatAccount senderAccount, long sendingDateSeconds) {
        super(text);
        this.senderAccount = senderAccount;
        this.sendingDateSeconds = sendingDateSeconds;
    }

    public DefaultMessage() {

    }

    public ChatAccount getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(ChatAccount senderAccount) {
        this.senderAccount = senderAccount;
    }

    public long getSendingDateSeconds() {
        return sendingDateSeconds;
    }

    public void setSendingDateSeconds(long sendingDateSeconds) {
        this.sendingDateSeconds = sendingDateSeconds;
    }
}
