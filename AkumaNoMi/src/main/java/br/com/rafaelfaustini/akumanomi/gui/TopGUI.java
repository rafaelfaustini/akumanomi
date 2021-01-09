package br.com.rafaelfaustini.akumanomi.gui;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
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
        private String title = "Top Bounties";

        public TopGUI(int size, List<BountyModel> bounties) {
            AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
            title = MessageText(plugin.messagesConfig.getConfig().get("bounty.topWanted").toString());
            inv = Bukkit.createInventory(null, size, title);

            initializeItems(bounties);
        }

        public TopGUI() {
            AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
            title = MessageText(plugin.messagesConfig.getConfig().get("bounty.topWanted").toString());
            inv = Bukkit.createInventory(null, 9, title);
        }
        public void initializeItems(List<BountyModel> bounties) {
            try {
                AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
                int i = 0;
                for(BountyModel bounty : bounties){
                    String specialLore = "";
                    switch(i){
                        case 0:
                            specialLore = MessageText(plugin.messagesConfig.getConfig().get("bounty.firstPlace").toString());
                            inv.addItem(createGuiItem(bounty.getPlayer().getUUID(), bounty.getPlayer().toString(), String.format("β%.2f",bounty.getMoney()), specialLore));
                            i++;
                            continue;
                        case 1:
                        case 2:
                        case 3:
                            specialLore = MessageText(plugin.messagesConfig.getConfig().get("bounty.fourEmperors").toString());
                            inv.addItem(createGuiItem(bounty.getPlayer().getUUID(), bounty.getPlayer().toString(), String.format("β%.2f",bounty.getMoney()), specialLore));
                            i++;
                            continue;
                    }
                    inv.addItem(createGuiItem(bounty.getPlayer().getUUID(), bounty.getPlayer().toString(), String.format("β%.2f",bounty.getMoney())));
                    i++;
                }
            } catch (Exception e){
                Utils.TryException(e);
            }

        }

        protected ItemStack createGuiItem(UUID uuid, final String name, final String... lore) {
            final ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            meta.setDisplayName(name);
            item.setItemMeta(meta);


            meta.setDisplayName(name);

            meta.setLore(Arrays.asList(lore));

            item.setItemMeta(meta);

            return item;
        }

        public void openInventory(final HumanEntity ent) {
            ent.openInventory(inv);
        }

        @EventHandler
        public void onInventoryClick(final InventoryClickEvent e) {

            if (e.getView().getTitle() != title) return;

            e.setCancelled(true);

            final ItemStack clickedItem = e.getCurrentItem();

            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            final Player p = (Player) e.getWhoClicked();
        }

        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getView().getTitle() != title) {
                e.setCancelled(true);
            }
        }

}
