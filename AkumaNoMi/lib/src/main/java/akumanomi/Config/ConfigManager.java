package akumanomi.Config;

import org.bukkit.configuration.file.FileConfiguration;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import akumanomi.AkumaNoMi;

@Singleton
public class ConfigManager {
    private ICustomConfig messagesConfig;
    private AkumaNoMi plugin;

    @Inject
    public ConfigManager(ICustomConfig messagesConfig, AkumaNoMi plugin) {
        this.messagesConfig = messagesConfig;
        this.plugin = plugin;
    }

    public FileConfiguration getMessagesConfig() {
       return messagesConfig.getCustomConfig();
    }

    public void InitConfigs() {
        messagesConfig.init(plugin, "messages.yml");
    } 

}
