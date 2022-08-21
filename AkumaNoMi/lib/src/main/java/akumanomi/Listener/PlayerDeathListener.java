package akumanomi.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import akumanomi.Event.EsperDieEvent;

public class PlayerDeathListener implements Listener  {
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        checkIfPlayerIsEsper(player);

    }

    private void checkIfPlayerIsEsper(Player player) {
        if(!player.hasPermission("akumanomi.esper")) {
            return;
        }

        Bukkit.getServer().getPluginManager().callEvent(new EsperDieEvent(player));
    }
}
