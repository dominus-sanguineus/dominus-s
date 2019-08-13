package ru.ogrezem.springkotlinwithvue1.model.message;

public class ChatNotification {

    private String text;

    public ChatNotification(String text) {
        this.text = text;
    }

    public ChatNotification() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
