package br.com.rafaelfaustini.akumanomi;
import br.com.rafaelfaustini.akumanomi.controller.PlayerController;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class Esper implements Listener {
    private AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);

    public static Boolean isEsper(Player p){
        PlayerController playerControl = new PlayerController();
        PlayerModel player = playerControl.getByUUID(p.getUniqueId());
        return player.isEsper();
    }
    public static Boolean isOceaniteWater(Player p){
       boolean result = p.getLocation().getBlock().getType() == Material.WATER && p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WATER || p.getLocation().getBlock().getRelative(BlockFace.UP).getType() == Material.WATER;
       List<Material> oceanite = Arrays.asList( Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.IRON_BARS);
        for (Material block: oceanite) {
            result = result || Utils.isTouchingBlock(block, p);
        }
        return result;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = (Player) event.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, task -> {
            if (isOceaniteWater(player)) {
                if(isEsper(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 7));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10000, 14));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000, 7));

                    Vector vector = new Vector(0, -3, 0);
                    player.setVelocity(vector.multiply(1));
                }
            }
        }, 7L);
        }
}

