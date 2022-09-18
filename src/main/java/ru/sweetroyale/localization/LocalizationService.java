package ru.sweetroyale.localization;

import java.util.List;

public interface LocalizationService {

    /**
     * Скачивание файла из репозитория по имени
     *
     * @param fileName имя файла
     */
    void download(String fileName);

    /**
     * Получения сообщения по ключу
     *
     * @param type   язык
     * @param key    ключ
     * @param format форматирование
     * @return локализированное сообщение, если сообщение не найдено на другом
     * языке, то вернёт сообщение на русском, если нет сообщения на русском,
     * то вернёт имя ключа!
     */
    String get(LanguageType type, String key, Object... format);

    /**
     * Получить список сообщений
     *
     * @param type   язык
     * @param key    ключ
     * @param format форматирование
     * @return список сообщений, если сообщение не найдено на другом
     * языке, то вернёт сообщение на русском, если нет сообщения на русском,
     * то вернёт имя ключа!
     */
    List<String> getList(LanguageType type, String key, Object... format);

}
