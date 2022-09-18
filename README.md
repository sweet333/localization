# localization
Minecraft json localization example

```java
public class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        LocalizationService localizationService = new LocalizationServiceImpl("https://gitlab.com/sweetroyale/middagemc-localization/-/raw/main/lang/%s.json");

        localizationService.download("main");

        String itemNameMessage = localizationService.get(LanguageType.RUSSIAN, "MAIN_TEST_GROUP_ITEM_NAME");
        String pingMessage = localizationService.get(LanguageType.RUSSIAN, "MAIN_PING_MESSAGE", 20);
        
        getLogger.info(itemNameMessage);
        getLogger.info(pingMessage);
    }

}
```
