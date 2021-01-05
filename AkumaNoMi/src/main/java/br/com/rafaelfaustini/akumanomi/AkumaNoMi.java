package br.com.rafaelfaustini.akumanomi;
import br.com.rafaelfaustini.akumanomi.commands.MeraMeraNoMi;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class AkumaNoMi extends JavaPlugin{
    public FileConfiguration messagesConfig;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        String locale = getConfig().getString("locale");
        this.messagesConfig = this.createMessagesConfig(String.format("messages_%s.yml", locale));
        getServer().getPluginManager().registerEvents(new Esper(), this);
        getServer().getPluginManager().registerEvents(new MeraMeraNoMi(), this);
        getCommand("MeraMeraNoMi").setExecutor(new MeraMeraNoMi());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private FileConfiguration createMessagesConfig(String name) {
        File messagesConfigFile;
        messagesConfigFile = new File(getDataFolder(), name);
        if (!messagesConfigFile.exists()) {
            messagesConfigFile.getParentFile().mkdirs();
            saveResource(name, false);
        }

        FileConfiguration messagesConfigTmp = new YamlConfiguration();
        try {
            messagesConfigTmp.load(messagesConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            System.out.println("[AkumaNoMi] Error: "+e);
        }
        return messagesConfigTmp;
    }
}
