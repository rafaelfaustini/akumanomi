package akumanomi.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;

import com.google.inject.Inject;

import akumanomi.AkumaNoMi;
import akumanomi.Config.ConfigManager;
import akumanomi.Entity.Esper;
import akumanomi.Event.FruitEatEvent;
import akumanomi.Helper.TextHelper;

public class FruitEatListener implements Listener{

    private Esper esper;
    private AkumaNoMi plugin;
    private ConfigManager configManager;

    @Inject
    public FruitEatListener(Esper esper, AkumaNoMi plugin, ConfigManager configManager) {
        this.esper = esper;
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @EventHandler
    public void OnFruitEat(FruitEatEvent event){  
        Player player = event.getPlayer();
        String fruitKey = event.getFruitKey();
        esper.createEsper(player, fruitKey);
        boolean isActionSuccess = !esper.hasErrors();
        if(!isActionSuccess) {
            esper.clearErrors();
            return;
        }
        PermissionAttachment attachment = player.addAttachment(plugin);
        attachment.setPermission("akumanomi.esper", true);
        event.getPlayer().sendMessage(TextHelper.coloredText(configManager.getMessagesConfig().getString("FruitPowerStart")));
    }
}
