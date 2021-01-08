package br.com.rafaelfaustini.akumanomi;
import br.com.rafaelfaustini.akumanomi.commands.Bounty;
import br.com.rafaelfaustini.akumanomi.commands.MeraMeraNoMi;
import br.com.rafaelfaustini.akumanomi.gui.TopGUI;
import br.com.rafaelfaustini.akumanomi.utils.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class AkumaNoMi extends JavaPlugin{
    public CustomConfig messagesConfig;
    public CustomConfig espers;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        String locale = getConfig().getString("locale");
        this.messagesConfig = new CustomConfig(String.format("messages_%s.yml", locale));
        this.espers = new CustomConfig("espers.yml");

        getServer().getPluginManager().registerEvents(new Esper(), this);
        getServer().getPluginManager().registerEvents(new MeraMeraNoMi(), this);
        getServer().getPluginManager().registerEvents(new Bounty(), this);
        getServer().getPluginManager().registerEvents(new TopGUI(), this);
        getCommand("MeraMeraNoMi").setExecutor(new MeraMeraNoMi());
        getCommand("Bounty").setExecutor(new Bounty());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
