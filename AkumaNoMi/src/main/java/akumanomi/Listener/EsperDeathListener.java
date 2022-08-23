package akumanomi.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;

import com.google.inject.Inject;

import akumanomi.AkumaNoMi;
import akumanomi.Config.ConfigManager;
import akumanomi.Entity.Esper;
import akumanomi.Event.EsperDieEvent;
import akumanomi.Helper.TextHelper;

public class EsperDeathListener implements Listener{

    private Esper esper;
    private AkumaNoMi plugin;
    private ConfigManager configManager;

    
    @Inject
    public EsperDeathListener(Esper esper, AkumaNoMi plugin, ConfigManager configManager) {
        this.esper = esper;
        this.plugin = plugin;
        this.configManager = configManager;
    }



    @EventHandler
    public void OnEsperDeath(EsperDieEvent event){  
        Player player = event.getPlayer();
        esper.removeEsper(player);
        boolean isActionSuccess = !esper.hasErrors();
        if(!isActionSuccess) {
            esper.clearErrors();
            return;
        }
        PermissionAttachment attachment = player.addAttachment(plugin);
        attachment.setPermission("akumanomi.esper", false);
        player.sendMessage(TextHelper.coloredText(configManager.getMessagesConfig().getString("FruitPowerEnd")));
    }
}