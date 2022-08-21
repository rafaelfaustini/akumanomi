package akumanomi.Command;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import co.aikar.commands.BukkitCommandIssuer;
import co.aikar.commands.ConditionFailedException;

public class CommandValidations {
    public static void validateIfTriggeredByPlayer(BukkitCommandIssuer issuer) {
        if(!issuer.isPlayer()) {
            throw new ConditionFailedException("Command must be executed by a player.");
        }
    }
    public static void validateItemInHand(BukkitCommandIssuer issuer) {
        Player player = issuer.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if(itemInHand.getType().equals(Material.AIR)) {
            throw new ConditionFailedException("Player must be holding an item.");
        }
    }
}
