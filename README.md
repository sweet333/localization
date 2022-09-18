# localization
Minecraft json localization example

```json
{
    "PING_MESSAGE": {
        "ru": "§bСервер §8| §fВаш пинг §a%s §fмс",
        "en": "§bServer §8| §fYour ping is §a%s §fms"
    },
    "LOADING_ITEM_NAME": {
        "ru": "§cЗагрузка...",
        "en": "§cLoading..."
    },
    "LOADING_ITEM_LORE": {
        "ru": "§7Идёт загрузка данных",
        "en": "§7Data is being loaded"
    },
    "LANGUAGE_TITLE":{
        "ru": "Выбор языка",
        "en": "Language selection",
        "ua": "Вибір мови"
    },
    "LANGUAGE_ITEM_LORE": {
        "ru": ["", "§e▶ Нажмите, чтобы сменить язык!"],
        "en": ["", "§e▶ Click to change the language!"],
        "ua": ["", "§e▶ Натисніть, щоб змінити мову!"]
    },
    "TEST_GROUP": {
        "ITEM_NAME": {
            "ru": "§7АХАХАХХААХАХАХА",
            "en": "§7AHAHAHAHAHAHAHA"
        }
    }
}
```

```java
public class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        LocalizationService localizationService = new LocalizationServiceImpl("https://gitlab.com/sweetroyale/middagemc-localization/-/raw/main/lang/%s.json");

        localizationService.download("main");

        String itemNameMessage = localizationService.get(LanguageType.RUSSIAN, "MAIN_TEST_GROUP_ITEM_NAME");
        String pingMessage = localizationService.get(LanguageType.RUSSIAN, "MAIN_PING_MESSAGE", 20);
        
        getLogger().info(itemNameMessage);
        getLogger().info(pingMessage);
    }

}
```
