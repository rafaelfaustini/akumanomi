package akumanomi.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.inject.Inject;

import akumanomi.Entity.Esper;

public class PlayerJoinListener implements Listener  {
    private Esper esper;
    
    @Inject
    public PlayerJoinListener(Esper esper) {
        this.esper = esper;
    }


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player player = (Player) event.getPlayer();

        esper.setEsperPermission(player);

    }
}
