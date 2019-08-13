package ru.ogrezem.springkotlinwithvue1.service.chat.account;

import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;

import java.util.Map;
import java.util.Set;

public interface ChatAccountService {

    boolean registered(ChatAccount account);

    void register(ChatAccount account, String sessionId);

    boolean delete(String sessionId);

    Map<String, ChatAccount> getSessionsIdToAccounts();

    Set<String> getSessionsIds();
}
