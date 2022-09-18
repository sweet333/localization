package ru.sweetroyale.localization.impl;

import lombok.Getter;
import ru.sweetroyale.localization.LanguageType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class LocalizationStorage {

    private final LanguageType language;

    public LocalizationStorage(LanguageType language) {
        this.language = language;
    }

    private final Map<String, String> messageMap = new ConcurrentHashMap<>();
    private final Map<String, List<String>> messageListMap = new ConcurrentHashMap<>();

    public void addMessage(String key, String value) {
        messageMap.put(key.toUpperCase(), value);
    }

    public String getMessage(String key) {
        key = key.toUpperCase();

        if (messageMap.containsKey(key)) {
            return messageMap.get(key);
        }

        return null;
    }

    public void addMessageList(String key, List<String> messageList) {
        messageListMap.put(key.toUpperCase(), messageList);
    }

    public List<String> getMessageList(String key) {
        key = key.toUpperCase();

        if (messageListMap.containsKey(key)) {
            return messageListMap.get(key);
        }

        return Collections.emptyList();
    }
}
