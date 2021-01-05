package br.com.rafaelfaustini.akumanomi;
import br.com.rafaelfaustini.akumanomi.commands.MeraMeraNoMi;
import br.com.rafaelfaustini.akumanomi.utils.CustomConfig;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class AkumaNoMi extends JavaPlugin{
    public CustomConfig messagesConfig;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        String locale = getConfig().getString("locale");
        this.messagesConfig = new CustomConfig(String.format("messages_%s.yml", locale));

        getServer().getPluginManager().registerEvents(new Esper(), this);
        getServer().getPluginManager().registerEvents(new MeraMeraNoMi(), this);
        getCommand("MeraMeraNoMi").setExecutor(new MeraMeraNoMi());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
