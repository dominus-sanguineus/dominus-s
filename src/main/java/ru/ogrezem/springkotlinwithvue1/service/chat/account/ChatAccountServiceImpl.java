package ru.ogrezem.springkotlinwithvue1.service.chat.account;

import org.springframework.stereotype.Service;
import ru.ogrezem.springkotlinwithvue1.model.ChatAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class ChatAccountServiceImpl implements ChatAccountService {

    private HashMap<String, ChatAccount> sessionsIdToAccounts = new HashMap<>();

    @Override
    public boolean registered(ChatAccount account) {
        return sessionsIdToAccounts.entrySet().stream()
                .anyMatch(entry -> entry.getValue().getUsername().equals(account.getUsername()));
    }

    @Override
    public void register(ChatAccount account, String sessionId) {
        sessionsIdToAccounts.put(sessionId, account);
    }

    @Override
    public boolean delete(String sessionId) {
        if (!sessionsIdToAccounts.containsKey(sessionId))
            return false;
        sessionsIdToAccounts.remove(sessionId);
        return true;
    }

    @Override
    public Map<String, ChatAccount> getSessionsIdToAccounts() {
        return sessionsIdToAccounts;
    }

    @Override
    public Set<String> getSessionsIds() {
        return sessionsIdToAccounts.keySet();
    }

    private Optional<ChatAccount> findWithSessionId(String sessionId) {
        return sessionsIdToAccounts.entrySet().stream()
                .filter(entry -> entry.getKey().equals(sessionId))
                .map(Map.Entry::getValue)
                .findAny();
    }
}
