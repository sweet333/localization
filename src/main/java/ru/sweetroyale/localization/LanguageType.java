package ru.sweetroyale.localization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageType {

    RUSSIAN(0, "Русский", "ru", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZlYWZlZjk4MGQ2MTE3ZGFiZTg5ODJhYzRiNDUwOTg4N2UyYzQ2MjFmNmE4ZmU1YzliNzM1YTgzZDc3NWFkIn19fQ=="),
    ENGLISH(1, "English", "en", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTE3MDFmMjE4MzVhODk4YjIwNzU5ZmIzMGE1ODNhMzhiOTk0YWJmNjBkMzkxMmFiNGNlOWYyMzExZTc0ZjcyIn19fQ=="),
    UKRAINE(2, "Українська", "ua", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjhiOWY1MmUzNmFhNWM3Y2FhYTFlN2YyNmVhOTdlMjhmNjM1ZThlYWM5YWVmNzRjZWM5N2Y0NjVmNWE2YjUxIn19fQ==");

    private final int id;
    private final String name, abbreviatedName, texture;

    public static LanguageType fromId(int id) {
        for (LanguageType languageType : values())
            if (languageType.getId() == id)
                return languageType;

        return LanguageType.RUSSIAN;
    }
}
