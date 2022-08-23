package akumanomi.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import akumanomi.Event.WaterEnterEvent;

public class WaterEnterListener implements Listener{
    @EventHandler
    public void onWaterEnter(WaterEnterEvent event){  
        checkEsperSideEffect(event);
    }

    private void checkEsperSideEffect(WaterEnterEvent event) {
        Player player = event.getPlayer();
        boolean isEsper = player.hasPermission("akumanomi.esper") && !player.isOp();

        if(!isEsper) {
            return;
        }

        if(event.isPlayerOnTopOfWater() || event.isPlayerAtTheMiddleOfWater()) {
            Vector vector = new Vector(0, -1, 0);
            player.setVelocity(vector.multiply(1));
            return;
        }
        if(event.isPlayerAtTheBottomOfWater() && player.getActivePotionEffects().isEmpty()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 7));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10000, 14));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000, 7));
            return;
        }
    }
}
