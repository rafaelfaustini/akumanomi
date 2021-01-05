package br.com.rafaelfaustini.akumanomi;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Esper implements Listener {

    public Boolean isEsper(Player player){
        return player.hasPermission("akumanomi.Esper");
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = (Player) event.getPlayer();
        if(isEsper(player)) {
            if (player.getLocation().getBlock().getType() == Material.WATER && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WATER) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 7));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10000, 14));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000, 7));

                Vector vector = new Vector(0, -1, 0);
                player.setVelocity(vector.multiply(1));
            }
        }
    }
}
