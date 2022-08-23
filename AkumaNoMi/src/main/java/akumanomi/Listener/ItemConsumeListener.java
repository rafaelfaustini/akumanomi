package akumanomi.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.inject.Inject;

import akumanomi.Entity.Fruit;
import akumanomi.Event.FruitEatEvent;

public class ItemConsumeListener implements Listener  {

    private Fruit fruit;

    @Inject
    public ItemConsumeListener(Fruit fruit) {
        this.fruit = fruit;
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        checkPlayerOnFruitEatEvent(player, event.getItem());
        
    }

    private void checkPlayerOnFruitEatEvent(Player player, ItemStack item) {
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        String fruitKey = fruit.getFruitKey(meta);
        if(fruitKey == null) {
            return;
        }
        int fruitTypeID = fruit.getFruitTypeIfValid(item); 
        boolean isFruit = fruitTypeID > 0;
        if(!isFruit) {
            return;
        }
        Bukkit.getServer().getPluginManager().callEvent(new FruitEatEvent(player, fruitTypeID, fruitKey));
    }
}
