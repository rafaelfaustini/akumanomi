package br.com.rafaelfaustini.akumanomi;
import br.com.rafaelfaustini.akumanomi.commands.Bounty;
import br.com.rafaelfaustini.akumanomi.commands.MeraMeraNoMi;
import br.com.rafaelfaustini.akumanomi.controller.AkumaNoMiController;
import br.com.rafaelfaustini.akumanomi.gui.TopGUI;
import br.com.rafaelfaustini.akumanomi.utils.CustomConfig;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class AkumaNoMi extends JavaPlugin{
    public CustomConfig messagesConfig;
    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            getConfig().options().copyDefaults();
            saveDefaultConfig();
            Debug.initialize(getConfig());
            String locale = getConfig().getString("locale");
            this.messagesConfig = new CustomConfig(String.format("messages_%s.yml", locale));
            AkumaNoMiController akumanomi = new AkumaNoMiController();
            akumanomi.init();

            getServer().getPluginManager().registerEvents(new Esper(), this);
            getServer().getPluginManager().registerEvents(new MeraMeraNoMi(), this);
            getServer().getPluginManager().registerEvents(new Bounty(), this);
            getServer().getPluginManager().registerEvents(new TopGUI(), this);
            if(getConfig().getBoolean("wantedPoster")) getServer().getPluginManager().registerEvents(new WantedPoster(), this);
            getCommand("MeraMeraNoMi").setExecutor(new MeraMeraNoMi());
            getCommand("Bounty").setExecutor(new Bounty());
        } catch (Exception e){
            Utils.TryException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
