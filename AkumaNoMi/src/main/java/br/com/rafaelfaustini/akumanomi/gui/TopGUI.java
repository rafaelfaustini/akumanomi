package br.com.rafaelfaustini.akumanomi.gui;

import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static br.com.rafaelfaustini.akumanomi.utils.Utils.MessageText;

public class TopGUI implements Listener {
        private final Inventory inv;

        public TopGUI(int size, List<BountyModel> bounties) {
            // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
            inv = Bukkit.createInventory(null, size, "Top Bounties");

            // Put the items into the inventory
            initializeItems(bounties);
        }

        // You can call this whenever you want to put the items in
        public void initializeItems(List<BountyModel> bounties) {
            for(BountyModel bounty : bounties){
                bounty.toString();
                inv.addItem(createGuiItem(bounty.getPlayer().getUUID(), bounty.getPlayer().toString(), String.format("β%.2f",bounty.getMoney())));
            }
        }

        protected ItemStack createGuiItem(UUID uuid, final String name, final String... lore) {
            final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            meta.setDisplayName(name);
            item.setItemMeta(meta);


            // Set the name of the item
            meta.setDisplayName(name);

            // Set the lore of the item
            meta.setLore(Arrays.asList(lore));

            item.setItemMeta(meta);

            return item;
        }

        // You can open the inventory with this
        public void openInventory(final HumanEntity ent) {
            ent.openInventory(inv);
        }

        // Check for clicks on items
        @EventHandler
        public void onInventoryClick(final InventoryClickEvent e) {
            if (e.getInventory() != inv) return;

            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            // verify current item is not null
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            final Player p = (Player) e.getWhoClicked();

            // Using slots click is a best option for your inventory click's
            p.sendMessage("You clicked at slot " + e.getRawSlot());
        }

        // Cancel dragging in our inventory
        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getInventory() == inv) {
                e.setCancelled(true);
            }
        }

}
