package akumanomi.Entity;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.google.inject.Inject;

import akumanomi.AkumaNoMi;
import akumanomi.api.Action.CheckFruitAction;
import akumanomi.api.Action.CreateFruitAction;
import akumanomi.Entity.Enum.FruitTypeEnum;
import net.md_5.bungee.api.ChatColor;

public class Fruit {

    private AkumaNoMi plugin;
    private CreateFruitAction createFruitAction;
    private CheckFruitAction checkFruitAction;

    
    @Inject
    public Fruit(AkumaNoMi plugin, CreateFruitAction createFruitAction, CheckFruitAction checkFruitAction) {
        this.plugin = plugin;
        this.createFruitAction = createFruitAction;
        this.checkFruitAction = checkFruitAction;
    }
    
    private ItemStack createFruitItem(String fruitKey, String fruitName) {
        ItemStack itemStack = new ItemStack(Material.BEETROOT);
        ItemMeta meta = (ItemMeta) itemStack.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "akumanomikey");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, fruitKey);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', fruitName));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public void generateFruit(Player player, FruitTypeEnum fruitType) {
        
        String merameraKey = createFruitAction.CreateFruit(fruitType.getValue());
        ItemStack itemStack = createFruitItem(merameraKey, fruitType.getName());
        player.getInventory().addItem(itemStack);
    }
    public String getFruitKey(ItemMeta meta) {
        PersistentDataContainer container = meta.getPersistentDataContainer();
        NamespacedKey merameraKey = new NamespacedKey(plugin, "akumanomikey");

        if(!container.has(merameraKey , PersistentDataType.STRING)) {
            return null;
        }
        return container.get(merameraKey, PersistentDataType.STRING);
    }

    private int getFruitTypeID(ItemStack item) {
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        String key = getFruitKey(meta);
        if(key == null) {
            return 0;
        }
        int fruitType = checkFruitAction.CheckFruit(key);
        return fruitType;
    }

    public boolean checkFruit(ItemStack item) {
        int fruitType = getFruitTypeID(item);
        if(fruitType == 0) {
            return false;
        }
        return true;
    }
    public int getFruitTypeIfValid(ItemStack item) {
        int fruitType = getFruitTypeID(item);
        return fruitType;
    }
}
