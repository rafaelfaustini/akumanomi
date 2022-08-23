package akumanomi.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import akumanomi.AkumaNoMi;
import akumanomi.Helper.LoggerHelper;

public class CustomConfig implements ICustomConfig{
        private File customConfigFile;
        private FileConfiguration customConfig;

        public FileConfiguration getCustomConfig() {
            return this.customConfig;
        }
        
        private void loadConfigFile() {
            customConfig = new YamlConfiguration();
            try {
                customConfig.load(customConfigFile);
            } catch (IOException | InvalidConfigurationException e) {
                LoggerHelper.error(e.toString());
            }
        }

        private void createConfigFile(AkumaNoMi plugin, String fileName) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }   

        public void init(AkumaNoMi plugin, String configName) {
            customConfigFile = new File(plugin.getDataFolder(), configName);
            if (!customConfigFile.exists()) {
                createConfigFile(plugin, configName);
             }
             this.loadConfigFile();
        }
}
