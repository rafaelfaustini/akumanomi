package akumanomi.Config;

import org.bukkit.configuration.file.FileConfiguration;

import com.google.inject.ImplementedBy;

import akumanomi.AkumaNoMi;

@ImplementedBy(CustomConfig.class)
public interface ICustomConfig {
    FileConfiguration getCustomConfig();
    void init(AkumaNoMi plugin, String configName);
}
