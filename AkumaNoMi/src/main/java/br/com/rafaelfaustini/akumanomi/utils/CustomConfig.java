package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private File file;
    public FileConfiguration fileConfig;

    public CustomConfig(String name){
        createMessagesConfig(name);
    }

    public FileConfiguration getConfig(){
        return fileConfig;
    }

    private void createMessagesConfig(String name) {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AkumaNoMi");
        file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            System.out.println("[AkumaNoMi] Error: "+e);
        }
    }
}
