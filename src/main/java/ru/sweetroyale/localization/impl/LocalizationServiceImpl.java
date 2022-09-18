package ru.sweetroyale.localization.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import ru.sweetroyale.localization.LanguageType;
import ru.sweetroyale.localization.LocalizationService;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LocalizationServiceImpl implements LocalizationService {

    private final Map<String, LocalizationStorage> storageMap = new ConcurrentHashMap<>();
    private final String repositoryUrl;

    public LocalizationServiceImpl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;

        for (LanguageType languageType : LanguageType.values()) {
            storageMap.put(languageType.getAbbreviatedName().toUpperCase(), new LocalizationStorage(languageType));
        }
    }

    public void download(String fileName) {
        try {
            URL url = new URL(String.format(repositoryUrl, fileName));

            InputStreamReader inputStream = new InputStreamReader(url.openStream());

            parseKeys(JsonParser.parseReader(inputStream).getAsJsonObject().entrySet(), fileName.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String get(LanguageType type, String key, Object... format) {
        String message = getStorage(type).getMessage(key);

        if (message == null) {
            message = getStorage(LanguageType.RUSSIAN).getMessage(key);

            if (message == null) {
                return key;
            }
        }

        return String.format(message, format);
    }

    @Override
    public List<String> getList(LanguageType type, String key, Object... format) {
        List<String> messageList = getStorage(type).getMessageList(key);

        if (messageList.isEmpty()) {
            messageList = getStorage(LanguageType.RUSSIAN).getMessageList(key);

            if (messageList.isEmpty()) {
                return Collections.singletonList(key);
            }
        }

        String string = String.join("±", messageList);
        string = String.format(string, format);
        return Arrays.asList(string.split("±"));
    }

    private LocalizationStorage getStorage(LanguageType languageType) {
        return storageMap.get(languageType.getAbbreviatedName().toUpperCase());
    }

    private void parseKeys(Set<Map.Entry<String, JsonElement>> keys, String fileName) {
        keys.forEach(stringJsonElementEntry -> {
            String keyName = fileName + "_" + stringJsonElementEntry.getKey();

            parseMessage(keyName, stringJsonElementEntry);
        });
    }

    private void parseMessage(String keyName, Map.Entry<String, JsonElement> keyGroup) {
        keyGroup.getValue().getAsJsonObject().entrySet().forEach(key -> {
            if (key.getValue().isJsonObject()) {
                parseMessage(keyName + "_" + key.getKey().toUpperCase(), key);
                return;
            }

            saveKey(keyName, key);
        });
    }

    private static final Type collectionType = new TypeToken<List<String>>() {

    }.getType();

    private void saveKey(String keyName, Map.Entry<String, JsonElement> localeMessage) {
        String languageName = localeMessage.getKey().toUpperCase();
        LocalizationStorage languageStorage = storageMap.get(languageName);

        if (localeMessage.getValue().isJsonArray())
            languageStorage.addMessageList(keyName, new Gson().fromJson(localeMessage.getValue(), collectionType));
        else
            languageStorage.addMessage(keyName, localeMessage.getValue().getAsString());
    }
}
