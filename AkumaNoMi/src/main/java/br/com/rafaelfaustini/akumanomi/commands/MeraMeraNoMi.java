package br.com.rafaelfaustini.akumanomi.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class MeraMeraNoMi implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                Player player = (Player) sender;
                if(sender instanceof Player) {
                    ItemStack fruit = new ItemStack(Material.BEETROOT);
                    ItemMeta fruitMeta = fruit.getItemMeta();
                    fruitMeta.setDisplayName(ChatColor.RED + "Mera Mera No Mi");
                    fruitMeta.setLore(Arrays.asList(ChatColor.RED + "Burns as fire"));
                    fruit.setItemMeta(fruitMeta);

                    player.getInventory().addItem(fruit);
                }
                return false;
    }

    private boolean isMeraMera(ItemStack item){
        boolean checkName = "Mera Mera No Mi".equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',ChatColor.stripColor(item.getItemMeta().getDisplayName())));
        boolean checkColor = item.getItemMeta().getDisplayName().length() == (ChatColor.RED + "Mera Mera No Mi").length();
        if(item.getType() == Material.BEETROOT && checkName && checkColor){
            return true;
        }
        return false;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if(isMeraMera(e.getItem())){
            p.setFireTicks(50);
            p.sendMessage(ChatColor.RED+"You feel your stomach burning");
            p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 100);
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));

        }
    }
}
